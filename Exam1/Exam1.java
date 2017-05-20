package Exam1;

/**
 * Created by 曹 on 2017/5/15.
 */
/**
 *现有一个文件unorderedmsg.txt，内容是一段被打乱的聊天记录。
 *请按时间恢复聊天记录顺序，时间一样时按人名排序，结果输出到orderedmsg.txt。
 *并统计每个人的说话次数，人名和次数之间使用4个空格隔开，输出到count.txt中。
 *输出的文件都放置在classpath下。
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Exam1 {
    Map<String, Integer> map = new HashMap<String, Integer>();/* 存放名字和次数 */
    SortedMap<String, String> map2 = new TreeMap<String, String>();/* TreeMap自动进行字典序排序 */

    public static void main(String[] args) throws Exception {
        Exam1 om = new Exam1();
        String url = Exam1.class.getResource("/").getFile();
        url = URLDecoder.decode(url,"utf-8");
        System.out.println(URLDecoder.decode(url,"utf-8"));
        String path1 = url + "unorderedmsg.txt";
        String path2 = url + "orderedmsg.txt";
        String path3 = url + "count.txt";

        om.oputOderedmsg(path1, path2);/* 排序并输出到文件orderedmsg.txt */
        om.oputcount(path1, path3);/* 统计并输出到count.txt */
    }

    public void oputOderedmsg(String path1, String path2) throws Exception {
		/* 输出到orderedmsg.txt */
        File f = new File(path1);
        searchFile(f, "排序");
        output(path2);
    }

    public void oputcount(String path1, String path3) throws Exception {
		/* 输出到count.txt */
        File f = new File(path1);
        searchFile(f, "统计");
        output2(path3);
    }

    public void searchFile(File f, String s) throws Exception {
		/* 查找文件 */
        if (f.isFile()) {
            if (s.equals("排序"))
                order(f);
            else if (s.equals("统计"))
                count(f);
        } else if (f.isDirectory()) {
            File[] files = f.listFiles();
            if (files != null) {
                for (File file : files) {
                    searchFile(file, s);
                }
            }
        }
    }

    public void order(File f) throws Exception/* 排序 */{
        try {
            FileInputStream fis = new FileInputStream(f);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis,
                    "GBK"));
            String tmp = "";
            while ((tmp = br.readLine()) != null) {
                String[] s = null;
                s = tmp.split("    ");
                String time = s[1];/* 提取时间 */
                String name = s[0];/* 提取名字 */
                String tn = time.concat(name);/* 字符串连接 */
                if (map2.get(tn) == null) {
                    map2.put(tn, tmp);
                }
            }
            br.close();
            fis.close();
        } catch (Exception e) {
            System.out.print("排序出错！");
            e.printStackTrace();
        }
    }

    public void count(File f) throws Exception {/* 统计 */
        {
            try {
                FileInputStream fis = new FileInputStream(f);
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        fis, "GBK"));
                String tmp = "";

                while ((tmp = br.readLine()) != null) {
                    String[] s = null;
                    s = tmp.split("    ");
                    String name = s[0];/* 提取名字 */
                    if (map.get(name) == null) {
                        map.put(name, 1);
                    } else {
                        int value = map.get(name);
                        map.put(name, value + 1);

                    }
                }

                br.close();
                fis.close();
            } catch (Exception e) {
                System.out.print("统计出错！");
                e.printStackTrace();
            }
        }
    }

    public void output(String path2) throws Exception {/* 结果输出到orderedmsg.txt */
        try {

            FileWriter fw = new FileWriter(path2);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            Iterator<String> iter = map2.keySet().iterator();
            while (iter.hasNext()) {
                String key = iter.next();
                pw.println(map2.get(key));
            }
            System.out.println("___");
            pw.close();
            bw.close();
            fw.close();
        } catch (Exception e) {
            System.out.print("输出到orderedmsg出错！");
            e.printStackTrace();
        }
    }

    public void output2(String path3) throws Exception {/* 结果输出到count.txt */
        try {
            FileWriter fw2 = new FileWriter(path3);
            BufferedWriter bw2 = new BufferedWriter(fw2);
            PrintWriter pw2 = new PrintWriter(bw2);
            Iterator<String> iter2 = map.keySet().iterator();
            while (iter2.hasNext()) {
                String key = iter2.next();
                pw2.println(key + "    " + map.get(key));
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
