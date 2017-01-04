package com.qunar.service;

import java.io.*;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * 统计Import的类的数量
 * Created by 张竣伟 on 2017/1/3.
 */
public class CountImportService {

    private String dirName;

    HashMap<String, Integer> importClassRecords;

    public CountImportService(String dir) {
        this.dirName = dir;
        importClassRecords = new HashMap<String, Integer>();
    }


    /**
     * 统计引用最多的10个类
     *
     * @return
     */
    public HashMap<String, Integer> CountImportTop10() {
        this.process(new File(this.dirName));
        HashMap<String, Integer> importTop10 = new HashMap<String, Integer>();


        //取前10，不满10个则按顺序取出
        for (int i = 0; i < Math.min(10, this.importClassRecords.size()); i++) {
            int max = Integer.MIN_VALUE;
            String className = null;
            for (Entry item : this.importClassRecords.entrySet()) {
                String key = (String) item.getKey();
                int value = (Integer) item.getValue();
                if (value > max && !importTop10.containsKey(key)) {
                    max = value;
                    className = key;
                }
            }
            importTop10.put(className, this.importClassRecords.get(className));
        }
        return importTop10;
    }


    /**
     * 统计文件
     *
     * @param file
     */
    public void processFile(File file) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.startsWith("import")) {
                    String className = line.substring(6, line.length() - 1).trim();
                    Integer value = importClassRecords.get(className);
                    if (value == null) {
                        importClassRecords.put(className, 1);
                    } else {
                        importClassRecords.put(className, value + 1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 递归处理目录，如果为java文件进行统计
     *
     * @param file
     */
    public void process(File file) {
        if (!file.isDirectory()) {
            processFile(file);
        } else {
            File[] files = file.listFiles();
            for (File tmpFile : files) {
                process(tmpFile);
            }
        }
    }


}
