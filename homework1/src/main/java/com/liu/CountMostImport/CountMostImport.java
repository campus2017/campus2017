package com.liu.CountMostImport;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liudan on 2017/1/4.
 * 根据指定项目目录下（可以认为是 java 源文件目录）中，统计被 import 最多的类，前十个是什么
 */
public class CountMostImport {

    public static String s="";
    public static List<String> importData = new ArrayList<String>();
    public static Map<String,Integer> topImport=new HashMap<String,Integer>();
    public static Map<String,Integer> top10=new LinkedHashMap<String, Integer>();

    public static void main(String[] args) {
        File file=new File("E:\\test\\conference");//获取java根目录路径
        try {
            importData=listDirectory(file);//获取根目录下所有文件中所有的import类
            top10=countTop10Import(importData);//获取import类最多的前十个
        } catch (IOException e) {
            e.printStackTrace();
        }
        Set<Map.Entry<String,Integer>> entrySet1=top10.entrySet();
        for(Map.Entry<String,Integer> est:entrySet1){
            System.out.println(est.getKey());//输出前十个import类
        }
    }

    /*
     *@author: liudan
     *@description  列出给定目录下所有文件,返回文件包含的import类
     *@param  File file
     *@return  List<String>
     */
    public static List<String>  listDirectory(File dir)throws IOException {
        if (!dir.exists()) {
            throw new IllegalArgumentException("路径" + dir + " not exist");
        }
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException(dir + " is not Directory");
        }

        //如果要遍历子目录下的内容就需要构造File对象做递归操作，
        File[] files = dir.listFiles();//返回子目录（文件）的抽象
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isDirectory()) {
                    listDirectory(file);
                } else {
                    countImport(file);//获取各文件import类
                }
            }

        }
        return importData;
    }

    /*
     *@author: liudan
     *@description  利用正则表达式，统计各文件import类
     *@param File file
     *@return List<String>
     */
    public static List<String> countImport(File file){

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null)
                if (line.contains("import")){
                    s= regexFind("import (.+);", line);//利用正则表达式h获取import类
                    importData.add(s);//将import类放到List中
                }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return importData;
    }

    /*
     *@author: liudan
     *@description  对所有的import类使用hashmap计数，得到引用数量的前十
     *@param  List<String>
     *@return  Map<String,Integer>
     */
    public static Map<String,Integer> countTop10Import(List<String> importData){
        for (String s:importData) {
            if (topImport.get(s)!=null){
                topImport.put(s,topImport.get(s)+1);//对所有import计数,得到Map,key为import类，value为引用次数
            }else{
                topImport.put(s,1);
            }
        }
        int i=0;
        topImport=sortByValues(topImport);//按照计数对Map降序排列
        Set<Map.Entry<String,Integer>> entrySet=topImport.entrySet();
        for(Map.Entry<String,Integer> est:entrySet){
            if (i<10){
                top10.put(est.getKey(),est.getValue());//得到前十的import类
                i++;
            }else {
                break;
            }
        }
        return top10;
    }

    /*
     *@author: liudan
     *@description 对Map排序，按照value值降序排列，得到LinkedHashMap
     *@param Map<String, Integer>
     *@return  Map<String, Integer>
     */
    private static Map<String, Integer> sortByValues(Map<String, Integer> topImport) {

        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(topImport.entrySet());
        //根据制定的Comparator排序，通过 Entry 进行排序，虽然也是比较的 value，但是 List 允许重复的
        Collections.sort(list,new NewComparator());

        //按照插入排序，LinkedHashMap
        Map<String, Integer> sortedImport = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String,Integer> e : list) {
            sortedImport.put(e.getKey(), e.getValue());
        }
        return sortedImport;

    }

    /*
     *@author: liudan
     *@description 正则表达式匹配方法
     *@param
     *@return
     */
    public static String regexFind(String pattern, String text){
        Matcher matcher = Pattern.compile(pattern).matcher(text);
        if(matcher.find()&&matcher.groupCount()>0){
            return matcher.group(1);
        }else{
            return text ;
        }
    }
}
