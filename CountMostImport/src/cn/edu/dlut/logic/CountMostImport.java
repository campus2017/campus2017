package cn.edu.dlut.logic;

import java.io.*;
import java.util.*;

/**
 * Created by Logic on 2017/2/14.
 * 统计被 import 最多的类，前十个是什么
 */
public class CountMostImport {
    private static Map<String, Integer> importMap = new HashMap<String, Integer>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        File file = new File(fileName);
        if (file.exists()) {
            traverseDirectory(file);
        } else {
            System.out.println("File is not exist!");
        }
        printTopTen();
        scanner.close();
    }

    /**
     * 遍历当前目录下的每一个目录
     * @param file
     */
    public static void traverseDirectory(File file) {
        //如果当前文件是目录
        if (file.isDirectory()) {
            File[] fileList = file.listFiles();
            for (File f : fileList) {
                traverseDirectory(f);
            }
        } else {
            countMostImport(file);
        }
    }

    /**
     * 将一个文件中的import类加入到hashMap中
     * @param file
     */
    public static void countMostImport(File file) {
        if (file.getName().endsWith(".java")) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    line = line.trim();
                    if (line.startsWith("import") && line.startsWith("*;")) {
                        line = line.substring(6).trim();
                        Integer count = importMap.get(line);
                        if (count == null) {
                            importMap.put(line,1);
                        } else {
                            importMap.put(line, count + 1);
                        }
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
     * 输出前十个被import的类
     */
    public static void printTopTen() {
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(importMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        Map<String, Integer> hashMap = new HashMap<String, Integer>();
        for (int i = 0; i< list.size(); i++) {
            hashMap.put(list.get(i).getKey(), list.get(i).getValue());
        }

        int printNumber = 10;
        if (hashMap.size() < 10) {
            printNumber = list.size();
        }

        Iterator it = hashMap.entrySet().iterator();
        Integer value = new Integer(0);
        for (int i = 0; i < printNumber; i++) {
            Map.Entry entry = (Map.Entry)it.next();
            value = (Integer) entry.getValue();
            System.out.print(entry.getKey() + " : " + entry.getValue());
        }
        //如果第十个和后面几个并列的话，也需要把后面几个输出
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            if (value == (Integer)entry.getValue()) {
                System.out.print(entry.getKey() + " : " + entry.getValue());
            } else {
                break;
            }
        }
    }
}
