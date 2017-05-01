package com.qunar.fresh2017.exam1;

import java.io.*;
import java.util.*;

/**
 * Created by harvey on 17/4/28.
 */
public class Exam1 {

    /*排序*/
    SortedMap<String, String> orderMap = new TreeMap<String, String>();
    /*统计*/
    Map<String,Integer> countMap = new HashMap<String, Integer>();


    /*根据排序或者统计处理文件*/
    public void dealFile(File file, String str){
        if (file.isFile()){
            if (str.equals("排序")){
                //排序函数
                orderedmsg(file);
            } else if (str.equals("统计")){
                //统计
                countmsg(file);
            }
        }   else if (file.isDirectory()){
            File[] files = file.listFiles();
            if (files != null){
                for (File f:files){
                    dealFile(f, str);
                }
            }

        }
    }

    public void orderedmsg(File file){
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String tmp = "";
            while ((tmp = br.readLine()) != null ){
                String[] s = null;
                s = tmp.split("    ");
                String name = s[0];
                String time = s[1];
                String tn = time.concat(name);
                if (orderMap.get(tn) == null){
                    orderMap.put(tn, tmp);
                }
            }
            br.close();
            fis.close();
        } catch (IOException e) {
            System.out.print("排序出错！");
            e.printStackTrace();
        }

    }

    public void countmsg(File f){
        try {
        FileInputStream fis = new FileInputStream(f);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String tmp = "";


            while ((tmp = br.readLine()) != null){
                String[] s = null;
                s = tmp.split("    ");
                String name = s[0];
                if (countMap.get(name) == null){
                    countMap.put(name, 1);
                } else {
                    int value = countMap.get(name);
                    countMap.put(name, value+1);
                }
            }
            br.close();
            fis.close();
        } catch (IOException e) {
            System.out.print("统计出错！");
            e.printStackTrace();
        }
    }

    public void orderOutput(String filePath){

        try {
            FileWriter fw = new FileWriter(filePath);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            Iterator<String> iterator = orderMap.keySet().iterator();
            while (iterator.hasNext()){
                String key = iterator.next();
                pw.println(orderMap.get(key));
            }
            pw.close();
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.print("输出到orderedmsg出错！");
            e.printStackTrace();
        }
    }

    public void countOutput(String filePath) {
        try {
            FileWriter fw = new FileWriter(filePath);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            Iterator<String> iter2 = countMap.keySet().iterator();
            while (iter2.hasNext()) {
                String key = iter2.next();
                pw.println(key + "    " + countMap.get(key));
            }
            pw.close();
            bw.close();
            fw.close();
        } catch (Exception e) {
            System.out.print("输出到count出错！");
            e.printStackTrace();
        }
    }

    public void oputOderedmsg(String filePath, String oputFilePath) {
        File f = new File(filePath);
        dealFile(f, "排序");
        orderOutput(oputFilePath);
    }

    public void oputcount(String filePath, String oputFilePath) {
        File f = new File(filePath);
        dealFile(f, "统计");
        countOutput(oputFilePath);
    }

    public static void main(String[] args) {
        Exam1 ob = new Exam1();
        String url = Exam1.class.getResource("/").getFile();
        String filePath1 = url + "unorderedmsg.txt";
        String filePath2 = url + "orderedmsg.txt";
        String filePath3 = url + "count.txt";

        ob.oputOderedmsg(filePath1, filePath2);
        ob.oputcount(filePath1, filePath3);
    }
}


























