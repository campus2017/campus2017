package com.qunar.logic;

import java.io.*;
import java.util.*;

/**
 * Created by Logic on 2017/7/5.
 */
public class Main {
    static Map<String, Integer> map = new HashMap<String, Integer>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
		Syetem.out.println("Input the file path:");
        String filePath = scanner.nextLine();
        getMostImport(filePath);
        printTopTen(map);
        scanner.close();
    }

    /**
     * 统计每个类被import的次数
     * @param filePath
     */
    public static void getMostImport(String filePath) {
        File file = new File(filePath);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File tempFile:
                 files) {
                getMostImport(tempFile.getAbsolutePath());
            }
        } else {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file));) {
                String tempLine = null;
                while ((tempLine = bufferedReader.readLine()) != null) {
                    if (tempLine.trim().startsWith("import")) {
                        storeClassName(tempLine);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将一行代码处理，获得类的名称
     * @param tempLine
     * @return
     */
    private static void storeClassName(String tempLine) {
        String[] strings = tempLine.split("import");
        for (int i = 1; i < strings.length; i++) {
            String className = strings[i].substring(0, strings[i].length() - 1).trim();
            if (map.containsKey(className)) {
                Integer count = map.get(className);
                map.put(className, count+1);
            } else {
                map.put(className, 1);
            }
        }
    }

    /**
     * 输出被import最多的10个类
     * @param map
     */
    public static void printTopTen(Map map) {
        List<Map.Entry<String, Integer>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return -o1.getValue().compareTo(o2.getValue());
            }
        });

        int sum = 10;
        if (sum > list.size()) {
            sum = list.size();
        }
        for (int i = 0; i < sum; i++) {
            Map.Entry<String, Integer> entry = list.get(i);
            System.out.println("ClassName:" + entry.getKey() + ",----Count:" + entry.getValue());
        }
        while (sum < list.size() && list.get(sum).getValue() == list.get(sum + 1).getValue()) {
            System.out.println("ClassName:" + list.get(sum + 1).getKey() + ",----Count:" + list.get(sum + 1).getValue());
            sum++;
        }
    }
}
