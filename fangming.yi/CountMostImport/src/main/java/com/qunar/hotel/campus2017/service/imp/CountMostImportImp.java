package com.qunar.hotel.campus2017.service.imp;

import com.qunar.hotel.campus2017.service.CountMostImport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by fangming.yi on 2017/1/15.
 */
public class CountMostImportImp implements CountMostImport {
    private List<File> fileList = new ArrayList<File>();    //存储路径下的所有文件
    private Map<String, Integer> map = new HashMap<String, Integer>();    //存储import的类的数量

    //获得路径下的所有java文件，存放进fileList中
    public void getFileFromPath(String path) {
        File file = new File(path);
        File[] files = file.listFiles();
        if (files == null) {
            System.out.println("目录不存在！");
            System.exit(-1);
        }
        for (int i = 0; i < files.length; i++) {
            //如果是目录，则递归获取文件
            if (files[i].isDirectory()) {
                getFileFromPath(files[i].getAbsolutePath());
            }
            //匹配java文件,添加到filelist中
            else if (files[i].getName().matches(".*\\.java")) {
                fileList.add(files[i]);
            }
        }
    }

    //计算文件中import的类数量,存放进map
    public void getClassNum() {
        for (int i = 0; i < fileList.size(); i++) {
            String line;
            try {
                BufferedReader br = new BufferedReader(new FileReader(fileList.get(i)));
                line = br.readLine();
                while (line != null) {
                    line = line.trim();
                    //如果行以public或class开始，则后面不再包含import,跳出循环，读下一个文件
                    if (line.startsWith("public") || line.startsWith("class")) {
                        break;
                    }
                    if (line.startsWith("import")) {
                        String name = line.substring(6, line.length() - 1).trim();
                        if (map.get(name) == null) {
                            map.put(name, 1);
                        } else {
                            int val = map.get(name);
                            map.put(name, ++val);
                        }
                    }
                    line = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //计算并输出import最多的n个类
    public void getMaxClass(int firstNum) {
        List<Map.Entry<String, Integer>> list_entry = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(list_entry, new Comparator<Map.Entry<String, Integer>>() {

            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o1.getValue() > o2.getValue()) {
                    return 1;
                } else if (o1.getValue() < o2.getValue()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        int size = list_entry.size();
        //如果类的数量小于n,则全部取出，否则取前n个
        int count = size > firstNum ? firstNum : size;
        System.out.println("统计结果如下：");
        for (int i = 0; i < count; i++) {
            Map.Entry<String, Integer> entry = list_entry.get(size - 1 - i);
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
