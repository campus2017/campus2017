package com.youmeek.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by thinkpad on 2017/7/1.
 */
public class CountMostImport{
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("E:\\homework\\CountMostImport\\src\\com\\youmeek\\ImportedClass.java"));//假设文件中每一行都是导入语句
            String line = null;
            while ((line = br.readLine()) != null) {
                if (map.containsKey(line)) {
                    Integer j = map.get(line);
                    map.put(line, j + 1);
                } else {
                    map.put(line, 1);

                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //将treemap中的元素按照Key的大小降序排序
        List al=Top10Import(map);
        for(Object s:al){
           String str=(String)s;
           System.out.println(str);
        }
    }


    public static List<String> Top10Import(HashMap<String,Integer> importMap){
                                 //排序，并返回类个数
       List<Map.Entry<String,Integer>>	list=new ArrayList<>(importMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });
        List<String> result=new ArrayList<>();
        int i=0;
        for(Map.Entry<String,Integer> map:list){
            if(i<10) {
                result.add(map.getKey());
            }else
                break;
            ++i;
        }
        return result;
    }
}


//运行结果
/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Date;
import java.io.InputStreamReader;
import java.lang.System;
import java.io.BufferedWriter;
*/