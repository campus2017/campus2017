package com.qunar.tsai;

import java.io.*;
import java.util.*;

/**
 * Created by joeTsai on 2017/6/17.
 * 三、根据指定项目目录下（可以认为是 java 源文件目录）中，统计被 import 最多的类，前十个是什么。
 * 处理思路：
 * 按照给出的目录进行递归的查询，如果是文件，则获取文件的字符流，从而统计import类；
 * 如果是文件，递归进行处理；
 */

public class CountMostImport {
    //采用HashMap的效果并不好，需要再次通过导入List后进行排序，待优化；
    private static Map<String, Integer> mImportCountHM = new HashMap<>();

    private static ResourceBundle bundle = ResourceBundle.getBundle("tip");

    public static void main(String[] args) {

        String tipStr = bundle.getString("tip");
        String errStr = bundle.getString("errortip");
        System.out.println(tipStr);
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.next();
        System.out.println("filePath: " + filePath);
        File file = new File(filePath);
        if (!checkFileExist(file)) {
            System.out.println(errStr);
            System.exit(1);
        }
        countTop10Import(file);
        printTop(mImportCountHM, 10);
    }

    private static void printTop(Map<String, Integer> map, int sum) {
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(map.entrySet());
        entryList.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        int count = 0;
        for (Map.Entry<String, Integer> entry : entryList) {
            System.out.println(entry.getKey() + " : " + mImportCountHM.get(entry.getKey()));
            count++;
            if (count == sum) break;
        }

    }


    private static void countTop10Import(File file) {
        if (!file.exists()) return;
        File[] files = file.listFiles(new FileFilter() { //防止java源目录中的其他文件干扰统计；
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory() || pathname.getName().endsWith(".java");
            }
        });
        if (files == null) return;
        for (File f : files) {
            if (f.isDirectory()) {
//                System.out.println("Directory: " + f);
                countTop10Import(f);
            } else {
//                System.out.println("File: " + f);
                count(f);
            }
        }
    }

    private static void count(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String str = null;
            while ((str = reader.readLine()) != null) {
                String string = str.trim();
                //优化处理，一般认为java的import部分在源文件的开头，一旦开始正式代码，则可认为import部分结束；
                //前提：java的import部分在源文件的开头
                if (string.startsWith("public class")) {
                    break;
                }
                if (string.startsWith("import")) {
                    String clzStr = string.substring(string.indexOf("import") + 6, string.lastIndexOf(";")).trim();
                    //优化：对以*结尾的import，由于不属于类，因此不予统计；
                    if (clzStr.endsWith("*")) break;
                    if (mImportCountHM.containsKey(clzStr)) {
                        mImportCountHM.put(clzStr, mImportCountHM.get(clzStr) + 1);
                    } else {
                        mImportCountHM.put(clzStr, 1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean checkFileExist(File file) {
        File[] files = file.listFiles();
        return file.exists() && file.isDirectory() && files != null && files.length != 0;
    }
}