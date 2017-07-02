package com.qunar.kefa;

import com.google.common.collect.*;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kefa.zhang on 2017/6/13.
 */
public class CountMostImport {
    private Multimap<String, Integer> map = HashMultimap.create();
    private static final Pattern staticp =
            Pattern.compile("(?:import\\s+static\\s+)((?:[a-zA-Z]+?\\.)+)(?:.)"); // 一次编译，到处使用
    private static final Pattern p = Pattern.compile("(?:import\\s+)((?:[a-zA-Z]+?\\.)+[a-zA-Z]+);");

    private File input() {
        System.out.println("Please input path :");
        Scanner sc = new Scanner(System.in);
        String path = sc.nextLine();
        File file = new File(path);
        if (!file.isFile() && !file.isDirectory()) {
            System.out.println("Wrong path");
            return null;
        }
        sc.close();
        return file;
    }

    public List<File> getAllFiles(File filePath) {
        ArrayList<File> files = Lists.newArrayList();
        dfs(filePath, files);
        return files;
    }

    private void dfs(File filePath, ArrayList<File> filesList) {
        if (filePath.isFile()) {
            filesList.add(filePath);
            return;
        }
        if (filePath.isDirectory()) {
            File[] listFiles = filePath.listFiles();
            for (File listFile : listFiles) {
                dfs(listFile, filesList);
            }
        }
        return;
    }

    private void parseFiles(List<File> allFiles) {
            parseSerially(allFiles);
    }

    public List<String> parseSerially(List<File> allFiles) {
        for (File file : allFiles) {
            try (BufferedReader br = new BufferedReader(new FileReader(file));) {
                String line = br.readLine();
                while (line != null) {
                    line = line.trim();
                    if (line.startsWith("import")) {
                        prepareToPutMap(line);
                    } else if (line.startsWith("public class") || line.startsWith("class")) {
                        break;

                    }
                    line = br.readLine();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    private void prepareToPutMap(String line) {
        Matcher staticm = staticp.matcher(line);
        Matcher m = p.matcher(line);
        while (staticm.find()) {
            String add = staticm.group(1);
            putMap(add.substring(0, add.length() - 1));
        }
        while (m.find()) {
            String add = m.group(1);
            putMap(add);
        }

    }

    private void putMap(String str) {
        Collection<Integer> values = this.map.get(str);
        Iterator<Integer> iterator = values.iterator();
        if (iterator.hasNext()) {
            Integer count = iterator.next();
            this.map.removeAll(str);
            this.map.put(str, count + 1);
        } else {
            this.map.put(str, 1);
        }

    }

    /**
     * 开始计数
     */
    public void doCount() {
        File input = input();
        while (input == null) {
            input = input;
        }
        List<File> allFiles = getAllFiles(input);
        parseFiles(allFiles);
        topImport(10);
    }

    private void topImport(int topn) {
        TreeMultimap<Integer, String> treeMultimap = TreeMultimap.create();
        Multimaps.invertFrom(this.map, treeMultimap);
        List<Integer> topNumbers = Ordering.natural().greatestOf(treeMultimap.keySet(), topn);
        int index = 0;
        for (Integer topNumber : topNumbers) {
            Set<String> strings = treeMultimap.get(topNumber);
            for (String string : strings) {
                System.out.println(string);
                index++;
                if(index >=topn)
                    return;
            }
        }
    }

    public static void main(String[] args) {
        CountMostImport cm = new CountMostImport();
        cm.doCount();
    }


}
