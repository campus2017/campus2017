package com.qunar.fresh2017.exam1;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

/**
 * Created by tang_yi on 2017/4/18.
 */
public class Exam1 {

    private static List<ChatLog> chatLog = new LinkedList<ChatLog>();
    private static Multiset<String> chatCount = HashMultiset.create();
    private static String classPath = "/Users/tang_yi/Desktop/tang_yi/projects/campus2017/PlacementTest/target/classes/com/qunar/fresh2017/exam1/";

    //时间转换成时间戳
    private long stringToTimeStamp(String dateStr){
        if (dateStr == null){
            return 0;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long date = 0;
        try {
            date = format.parse(dateStr).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    //生成聊天记录链表
    public void computeChatLogList() {
        File unorderedmsg = new File(classPath + "unorderedmsg.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(unorderedmsg))) {
            String line = null;
            while ((line = br.readLine()) != null){
                long date = 0;
                String name = null;
                String rexDateEx = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}";
                String rexNameEx = "\\u3010.+\\u3011.+\\(\\d+\\)";
                Pattern datePattern = Pattern.compile(rexDateEx);
                Pattern namePattern = Pattern.compile(rexNameEx);
                Matcher dateMatcher = datePattern.matcher(line);
                Matcher nameMatcher = namePattern.matcher(line);
                while (dateMatcher.find()) {
                    String dateStr = dateMatcher.group(0);
                    date = stringToTimeStamp(dateStr);
                    System.out.print(date);
                }
                while (nameMatcher.find()){
                    name = nameMatcher.group(0);
                    System.out.println(name);
                }

                if (name != null) {
                    chatCount.add(name);
                }
                chatLog.add(new ChatLog(date, name, line));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void main(String [] args) {
        System.out.println("ddd");
        Exam1 exam1 = new Exam1();
        exam1.computeChatLogList();
        Collections.sort(chatLog, new ChatLogComparator());
        File orderedmsg = new File(classPath + "orderedmsg.txt");
        File count = new File(classPath + "count.txt");
        //写入排序后的聊天记录
        if (!orderedmsg.exists()){
            try {
                orderedmsg.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(orderedmsg))){
            for (ChatLog ele:chatLog){
                bw.write(ele.getLog() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //写入没人说话次数
        if (!count.exists()){
            try {
                count.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try ( BufferedWriter bw = new BufferedWriter(new FileWriter(count))){
            for (String name:chatCount.elementSet()){
                bw.write(name +"    " + chatCount.count(name) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
