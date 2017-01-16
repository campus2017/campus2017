package com.company;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by admin on 2016/12/24.
 */
public class CountImport {
    private HashMap<String, Integer> map;
    private Pattern pattern;

    public CountImport() {
        pattern = Pattern.compile("import (.*?);");
        map = new HashMap<String, Integer>();
    }

    public void add(String filename) {
        // 读文件，发现有 import 语句，记录下包名
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filename));
            for (String line; (line = reader.readLine()) != null; ) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    String className = matcher.group(1);
                    if (map.containsKey(className)) {
                        Integer count = map.get(className);
                        map.remove(className);
                        map.put(className, count+1);
                    } else {
                        map.put(className, 1);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Can't find file: " + filename);
        } catch (IOException e) {
            System.out.println("Failed reading file: " + filename);
        } finally {
            if (reader != null)
            {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<ClassCount> getList(int num) {
        PriorityQueue<ClassCount> queue = new PriorityQueue<>(new Comparator<ClassCount>() {
            @Override
            public int compare(ClassCount o1, ClassCount o2) {
                if (o1.count > o2.count) {
                    return 1;
                } else if (o1.count < o2.count) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        for ( Map.Entry<String, Integer> entry : map.entrySet()
             ) {
            queue.add(new ClassCount(entry.getKey(), entry.getValue()));
            // 保证成员不超过10个
            if (queue.size() > num) {
                queue.poll();
            }
        }

        List<ClassCount> list = new LinkedList<ClassCount>();
        while (!queue.isEmpty()) {
            list.add(0, queue.poll());
        }
        return list;
    }
}
