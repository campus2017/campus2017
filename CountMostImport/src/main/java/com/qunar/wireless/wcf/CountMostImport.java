package com.qunar.wireless.wcf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by wcf on 2016-12-30.
 */
public class CountMostImport {

    // 查找指定路径下的所有文件
    public static List<File> findFiles(String path){
        List<File> files = new ArrayList<File>();

        // 路径不存在,返回空列表
        File folder = new File(path);
        if(!folder.exists()){
            return files;
        }

        // 路径指定了一个文件,返回只含这个文件的列表
        if (folder.isFile()){
            files.add(folder);
            return files;
        }

        // 路径指定了一个目录,层次遍历这个目录
        Queue<File> que = new LinkedList<File>();
        que.add(folder);
        while(!que.isEmpty()){
            File head = que.poll();
            File[] subFiles = head.listFiles();
            for(File f:subFiles){
                if(f.isDirectory()){
                    que.add(f);
                } else if(f.isFile()){
                    files.add(f);
                }
            }
        }

        return files;
    }

    // 查找指定路径下的java文件
    public static List<File> findJavaFiles(String path){
        List<File> allFiles = findFiles(path);
        List<File> javaFiles = new ArrayList<File>();
        for(File f:allFiles){
            if(f.getName().endsWith(".java")){
                javaFiles.add(f);
            }
        }
        return javaFiles;
    }

    // 从一行中提取 import 的类, 如果不含 import 语句, 返回的 list 中没有值
    public static List<String> findImportClassInLine(String line){
        List<String> r = new ArrayList<String>();

        line = line.trim();
        // 不是以 import 开头的行, 忽略
        if(line.length()<=6 || !line.substring(0,6).equals("import")){
            return r;
        }

        // 以 import 开头的行, 测试是否一行有多个 import
        String[] sentences = line.split(";");
        for(String sentence : sentences){
            sentence = sentence.trim();
            if(sentence.length()>6 && sentence.substring(0,6).equals("import")){
                sentence = sentence.substring(6).trim();
                r.add(sentence);
            }
        }

        return r;
    }

    // 统计一个文件中 import 类的数量
    public static Map<String,Integer> countImportClassInFile(File file) throws IOException {
        Map<String,Integer> r = new HashMap<String, Integer>();

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        while (line!=null){
            List<String> classNames = findImportClassInLine(line);
            for(String name:classNames){
                if(r.get(name)!=null){
                    r.put(name,r.get(name)+1);
                }
                else {
                    r.put(name,1);
                }
            }

            line = br.readLine();
        }

        return r;
    }

    // 合并统计到的类
    public static Map<String,Integer> mergeMap(Map<String,Integer> mp1,Map<String,Integer> mp2){
        Map<String,Integer> r = new HashMap<String, Integer>();

        for(String s: mp1.keySet()){
            r.put(s,mp1.get(s));
        }
        for(String s:mp2.keySet()){
            if(r.get(s)!=null){
                r.put(s,r.get(s)+mp2.get(s));
            }
            else{
                r.put(s,mp2.get(s));
            }
        }

        return r;
    }

    // 统计一个目录下的 import 类
    public static Map<String,Integer> countImportClassInPath(String path){
        Map<String,Integer> r = new HashMap<String, Integer>();
        List<File> javaFiles = findJavaFiles(path);

        for(File file:javaFiles){
//            System.out.println("正在处理文件:"+file.getPath());
            Map<String,Integer> mp = null;
            try {
                mp = countImportClassInFile(file);
            } catch (IOException e) {
                System.out.println("打开文件时发生错误:"+file.getPath());
            }
            r = mergeMap(r,mp);
        }

        return r;
    }

    public static List<Map.Entry<String, Integer>> topK(List<Map.Entry<String,Integer>> list, int k, Comparator cmp){
        PriorityQueue<Map.Entry<String,Integer>> que = new PriorityQueue<Map.Entry<String,Integer>>(k,cmp);
        for(Map.Entry<String,Integer> o:list){
            que.add(o);
        }
        List<Map.Entry<String, Integer>> r = new ArrayList<Map.Entry<String, Integer>>();
        while(que.size()>0 && k>0){
            r.add(que.poll());
            --k;
        }
        return r;
    }

    public static List<Map.Entry<String,Integer>> topKImportClass(String path,int k){

        Map<String,Integer> map = countImportClassInPath(path);
        List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String, Integer>>();
        Iterator<? extends Map.Entry<?, ?>> entries = map.entrySet().iterator();
        while (entries.hasNext()){
            Map.Entry<?,?> entry = entries.next();
            list.add((Map.Entry<String, Integer>) entry);

//            System.out.println(entry.getKey()+":"+entry.getValue());
        }

        List<Map.Entry<String,Integer>> r = topK(list, k, new Comparator() {
            public int compare(Object o1, Object o2) {
                Map.Entry<String, Integer> left = (Map.Entry<String, Integer>) o1;
                Map.Entry<String, Integer> right = (Map.Entry<String, Integer>) o2;
                if(left.getValue()<right.getValue()){
                    return 1;
                }
                else if (left.getValue()>right.getValue()){
                    return -1;
                }
                else {
                    return 0;
                }
            }
        });
        return r;
    }
}
