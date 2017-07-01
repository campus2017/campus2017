package com.qunar.fresh2017.exam1;

import java.io.*;
import java.util.*;

/**
 * Created by Administrator on 2017/6/30.
 */
public class Exam1 {
    Map<String,Integer> count = new HashMap<String,Integer>();  //统计每个人说话的次数
    SortedMap<String,String> storage = new TreeMap<String,String>();  //排序

    public void process(String path) throws Exception/*排序*/ {
        try {
            File f = new File(path);
            if(!f.exists())
                System.out.println("文件不存在");
            else {
                FileInputStream fis = new FileInputStream(f);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis,"gbk"));
                String tmp = "";
                while ((tmp = br.readLine()) != null) {
                    String[] s = null;
                    s = tmp.split("    "); //四个空格
                    String time = s[1];      /*提取时间*/
                    String name = s[0];      /*提取包含名字的第一个字段*/
                    String tn = time.concat(name);/*字符串连接*/
                    if (storage.get(tn) == null) {
                        storage.put(tn, tmp); /*自动排序*/
                    }
                    if (count.get(name) == null) {
                        count.put(name, 1); /*自动排序*/
                    } else {
                        int value = count.get(name);
                        count.put(name, value + 1);
                    }
                }
                br.close();
                fis.close();
            }
        } catch (Exception e) {
            System.out.print("排序出错！");
             e.printStackTrace();
        }
    }

    public void output1(String path) throws Exception {  /*结果输出到orderedmsg.txt*/
        try {

            FileWriter fw = new FileWriter(path);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            Iterator<String> iter = storage.keySet().iterator();
            while (iter.hasNext()) {
                String key = iter.next();
                pw.println(storage.get(key));
            }
            pw.close();
            bw.close();
            fw.close();
        } catch (Exception e) {
            System.out.print("输出到orderedmsg出错！");
            e.printStackTrace();
        }
    }

    public void output2(String path) throws Exception {/*结果输出到count.txt*/
        try {
            FileWriter fw2 = new FileWriter(path);
            BufferedWriter bw2 = new BufferedWriter(fw2);
            PrintWriter pw2 = new PrintWriter(bw2);
            Iterator<String> iter2 = count.keySet().iterator();
            while (iter2.hasNext()) {
                String key = iter2.next();
                pw2.println(key + "    " + count.get(key));
            }
            pw2.close();
            bw2.close();
            fw2.close();
        } catch (Exception e) {
            System.out.print("输出到count出错！");
            e.printStackTrace();
        }
    }
}
