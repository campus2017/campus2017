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
            Pattern.compile("(?:import\\s+static\\s+)((?:[a-zA-Z]+?\\.)+)(?:.)"); // 常量，不需要多次实例化
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

    /**
     * 深度优先遍历文件夹
     * @param filePath
     * @param filesList
     */
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

    /**
     * 若文件过多，可考虑多线程并发执行，此处需求不大，所以未实现
     * @param allFiles
     * @return
     */
    private void parseFiles(List<File> allFiles) {
            parseSerially(allFiles);
    }

    public List<String> parseSerially(List<File> allFiles) {
        for (File file : allFiles) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
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
            String add = staticm.group(1); // 分组
            putMap(add.substring(0, add.length() - 1));
        }
        while (m.find()) {
            String add = m.group(1);
            putMap(add);
        }

    }

    /**
     * 以 import的类=出现的数量 的形式存在Multimap中
     * @param str
     */
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
     * 计数逻辑：获取文件夹下所有文件-> 解析文件，获得类及导入的次数->获取前n个类
     */
    public void doCount() {
        File input = input();// 循环读取，直到读取到合法路径
        while (input == null) {
            input = input();
        }
        List<File> allFiles = getAllFiles(input);// 获取文件
        parseFiles(allFiles);//解析文件，获得类及导入的次数
        topImport(10);//获取前n个类
    }

    /**
     * 取前n个
     * @param topn
     */
    private void topImport(int topn) {
        TreeMultimap<Integer, String> treeMultimap = TreeMultimap.create();
        Multimaps.invertFrom(this.map, treeMultimap);// Key Value 倒置，Key 相同时，value为Set（一对多），不会丢失数据。使用TreeMultiMap，key有序，便于后续的排序
        List<Integer> topNumbers = Ordering.natural().greatestOf(treeMultimap.keySet(), topn);// 按照 Key 排序
        int index = 0;
        for (Integer topNumber : topNumbers) {// 打印前n个class，当出现个数相同时，按照字典序排序
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
