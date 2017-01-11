package com.qunar.campus2017.countMostImport;

import java.io.*;
import java.util.*;

/**
 * Created by chunming.xcm on 2017/1/11.
 */
public class CountMostImport {
    private static final String regex = "^(import)[^\\*]*";
    private List<File> fileList = new ArrayList<File>();
    private Map<String, Integer> classMap = new HashMap<String, Integer>();

    /**
     * 获取文件列表
     * @param directory
     */
    public void travDirectory(File directory) {
        if(!directory.exists()) {
            throw new IllegalArgumentException(directory + "不存在!");
        }
        else if(!directory.isDirectory()) {
            fileList.add(directory);
        }
        else {
            File[] files = directory.listFiles();
            if(files != null) {
                for(File file : files) {
                    travDirectory(file);
                }
            }
        }
    }

    /**
     * 获取被import类列表及其数量
     */
    public void travFile() {
        if(fileList != null) {
            try {
                for(File file : fileList) {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    try {
                        String temp = bufferedReader.readLine();
                        while(temp != null) {
                            if(temp.matches(regex)) {
                                String[] temp1 = temp.split(" ");
                                String[] temp2 = temp1[1].split(";");
                                String className = temp2[0];
                                if(classMap.containsKey(className)) {
                                    classMap.put(className, classMap.get(className) + 1);
                                }
                                else {
                                    classMap.put(className, 1);
                                }
                            }
                            temp = bufferedReader.readLine();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch(FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 降序排序
     * @return 降序数组
     */
    public List<Map.Entry<String, Integer>> sort() {
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(classMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if(o1.getValue() < o2.getValue())
                    return 1;
                else if(o1.getValue() > o2.getValue())
                    return -1;
                else
                    return 0;
            }
        });
        return list;
    }
}
