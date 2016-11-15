package org.hadyang.impor;

import com.google.common.base.Strings;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountMostImport {
    private final ExecutorService executor;
    private final Queue<File> queue;
    private List<Future<Boolean>> futures;

    {
        executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        queue = new LinkedList<>();
        futures = new LinkedList<>();
    }

    public List<CountResult> count(File in) {
        queue.add(in);

        while (!queue.isEmpty()) {
            File file;
            if ((file = queue.poll()).isDirectory()) {
                queue.addAll(Arrays.asList(file.listFiles()));
            } else {
                String fileName = file.getName();
                String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);

                if (prefix.equals("java"))
                    futures.add(executor.submit(new ParseTask(file)));
            }
        }

        executor.shutdown();
        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        List<CountResult> result = new ArrayList<>(ParseTask.map.size());
        Iterator<String> iterator = ParseTask.map.keySet().iterator();
        while (iterator.hasNext()) {
            String name = iterator.next();
            CountResult temp = new CountResult(name, ParseTask.map.get(name));
            result.add(temp);
        }

        return result;
    }

    private static class ParseTask implements Callable<Boolean> {
        static final Pattern import_pattern = Pattern.compile("^import");
        static final Pattern static_pattern = Pattern.compile("^import\\s+static");

        //开始使用的是ConcurrentHashMap，在后面的测试发现其数据一致性有问题，改用HashMap并加锁的实现
        static final HashMap<String, Integer> map = new HashMap<>();
        File file;

        ParseTask(File file) {
            this.file = file;
        }

        @Override
        public Boolean call() throws Exception {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (Strings.isNullOrEmpty(line)) {
                        continue;
                    }

                    //静态导入导入的是静态方法，不需要计算
                    Matcher static_matcher = static_pattern.matcher(line);
                    if (static_matcher.find()) {
                        continue;
                    }

                    Matcher import_matcher = import_pattern.matcher(line);
                    if (import_matcher.find()) {
                        line = line.substring(7, line.length() - 1);
                        line = line.trim();

                        synchronized (map) {
                            if (map.containsKey(line)) {
                                map.put(line, map.get(line) + 1);
                            } else {
                                map.put(line, 1);
                            }
                        }
                    }
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return false;
        }
    }
}
