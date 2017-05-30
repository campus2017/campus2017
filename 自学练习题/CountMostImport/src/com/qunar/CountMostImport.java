package com.qunar;

import java.io.*;
import java.util.*;

/**
 * Created by bmi-xiaoyu on 2017/5/29.
 * 在给定的源文件或目录中，统计被 import 最多的前10个类
 */
public class CountMostImport {
    //统计每个类的出现的次数
    private static HashMap<String, Integer> countMap = new HashMap<>();

    static int fileCount = 0;

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("请输入要统计的java文件或java目录的路径：");
        Scanner in = new Scanner(System.in);
        String filePath = in.nextLine();

        long startTime = System.currentTimeMillis();
        File file = new File(filePath);
        // 根据用户输入的文件名和目录执行import类统计
        importStat(file);

        System.out.println("-------被import最多的10个类：--------");
        printTopK();

        long endTime = System.currentTimeMillis();
        System.out.println("耗时： " + (endTime-startTime) + "ms");
    }

    /**
     * 打印出countMap中，值前10大的对象
     */
    public static void printTopK() {
        Map<String, Integer> top10 = new TreeMap<>();
        int size;

        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            size = top10.size();
            //top10 元素数小于10，直接添加进去
            if (size < 10) {
                top10.put(entry.getKey(), entry.getValue());
            } else {
                if (entry.getValue() > getMinTopKValue(top10).getValue()) {
                    top10.remove(getMinTopKValue(top10).getKey());
                    top10.put(entry.getKey(), entry.getValue());
                }
            }
        }

        //将 TreeMap 转换成list
        List<Map.Entry<String , Integer>> top10List = new ArrayList<Map.Entry<String, Integer>>(top10.entrySet());
        Collections.sort(top10List, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        //打印
        for(int i = 0; i < top10List.size(); i++) {
            System.out.println("Top " + (i + 1) + ": " + top10List.get(i).getKey() + " " + top10List.get(i).getValue());
        }
    }

    /**
     * 在topK中获取最小值
     * @param topK 存储待统计的对象
     * @return topK中Value 值最小的对象
     */
    public static Map.Entry<String, Integer> getMinTopKValue(Map<String, Integer> topK) {
        //将 TreeMap 转换成list
        List<Map.Entry<String , Integer>> list = new ArrayList<Map.Entry<String, Integer>>(topK.entrySet());
        //排序
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });
        return list.get(0);
    }

    /**
     * 统计给定文件中，被import 的类及其次数
     * @param file 待统计的java源文件
     * @throws FileNotFoundException 未找到给定文件
     */
    private static void importStat(File file) throws FileNotFoundException {
        if (file == null || !file.exists())
            throw new FileNotFoundException(file + "，文件不存在！");

        fileCount++;   // 文件数累加

        if (file.isDirectory()) {
            File[] files = file.listFiles(new FileFilter() {

                @Override
                public boolean accept(File pathname) {
                    return pathname.getName().endsWith(".java") || pathname.isDirectory();
                }
            });

            for (File target : files) {
                importStat(target);
            }
        } else {
            BufferedReader bufr = null;
            try {
                // 将指定路径的文件与字符流绑定
                bufr = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            } catch (FileNotFoundException e) {
                throw new FileNotFoundException(file + "，文件不存在！" + e);
            }

            // 遍历文件中的每一行，并根据正则匹配的结果记录每一行匹配的结果
            String line = null;
            try {
                while ((line = bufr.readLine()) != null) {
                    if (line.startsWith("import")) {
                        line = line.replaceAll("\\s*", "");
                        line = line.substring(6);
                        line = line.substring(0, line.length() - 1);
                        Integer value = countMap.get(line);
                        if (value == null) {
                            countMap.put(line, 1);
                        } else {
                            countMap.put(line, value + 1);
                        }
                    } else {
                        continue;
                    }
                }

            } catch (IOException e) {
                throw new RuntimeException("读取文件失败！" + e);
            } finally {
                try {
                    bufr.close();   // 关闭文件输入流并释放系统资源
                } catch (IOException e) {
                    throw new RuntimeException("关闭文件输入流失败！");
                }
            }
        }
    }

}


