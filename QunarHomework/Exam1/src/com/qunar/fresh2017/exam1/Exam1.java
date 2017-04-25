package com.qunar.fresh2017.exam1;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 现有一个文件unorderedmsg.txt，内容是一段被打乱的聊天记录。
 * 请按时间恢复聊天记录顺序，时间一样时按人名排序，结果输出到orderedmsg.txt。
 * 统计每个人的说话次数，人名和次数之间使用4个空格隔开，输出到count.txt中。
 * 输出的文件都放置在classpath下。
 * Created by Abby on 2017/4/25.
 */
public class Exam1 {

    SortedMap<String, String> orderMap = new TreeMap<String, String>();  //恢复聊天记录顺序，TreeMap自动按字典序排序
    HashMap<String, Integer> countMap = new HashMap<String, Integer>();  //记录每个人说话次数

    public void outputPro(String p1, String p2, String p3)
    {
        order_count(p1);
        outputOrder(p2);
        outputCount(p3);

    }

    public void order_count(String p1)
    {
        try {
            FileReader fr = new FileReader(p1);
            BufferedReader br = new BufferedReader(fr);
            String tmp = "";
            while((tmp = br.readLine())!=null)
            {
                String[] str = tmp.split("    ");
                String time = str[1];
                String name = str[0];
                String tn = time.concat(name);
                if(orderMap.get(tn)==null)       //将时间和姓名放入TreeMap，自动排序
                    orderMap.put(tn, tmp);
                if(countMap.get(name)==null)     //统计姓名说话次数，放到HahMap
                    countMap.put(name, 1);
                if(countMap.get(name)!=null)
                {
                    int value = countMap.get(name);
                    countMap.put(name, value+1);
                }
            }
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {

            System.out.print("文件unorderedmsg.txt未找到！");
            e.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public void outputOrder(String p2)      //将排好序的聊天记录输出到文件orderedmsg.txt
    {
        try{
            FileWriter fw = new FileWriter(p2);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            Iterator<String> iter = orderMap.keySet().iterator();
            while(iter.hasNext())
            {
                String key = iter.next();
                pw.println(orderMap.get(key));
            }

            pw.close();
            bw.close();
            fw.close();
        } catch (Exception e) {
            System.out.print("输出到orderedmsg.txt出错！");
            e.printStackTrace();
        }

    }

    public void outputCount(String p3)         //将统计好姓名及说话次数的内容输出到文件count.txt
    {
        try{
            FileWriter fw2 = new FileWriter(p3);
            BufferedWriter bw2 = new BufferedWriter(fw2);
            PrintWriter pw2 = new PrintWriter(bw2);

            Iterator<String> iter = countMap.keySet().iterator();
            while(iter.hasNext())
            {
                String key = iter.next();
                pw2.println(key + "    " + countMap.get(key));
            }

            pw2.close();
            bw2.close();
            fw2.close();

        } catch (Exception e) {
            System.out.print("输出到count.txt出错！");
            e.printStackTrace();
        }

    }

    public static void main(String[] args)
    {
        Exam1 ex = new Exam1();

        String url = Exam1.class.getResource("/").getFile();  //classpath路径
        String path1 = url + "unorderedmsg.txt";
        String path2 = url + "orderedmsg.txt";
        String path3 = url + "count.txt";

        ex.outputPro(path1, path2, path3);

        System.out.print("结果计算完成！");
    }
}
