package com.qunar.fresh2017.exam1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;


/**
 * Created by tang_yi on 2017/4/18.
 */
public class Exam1 {
    public HashMap<Long, HashMap> chatMap= new HashMap<Long, HashMap>();

    public long stringToTimeStamp(String dateStr){
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
    public void computeChatMap(){
        File unorderedmsg = new File("/Users/tang_yi/Desktop/tang_yi/projects/campus2017/PlacementTest/target/classes/com/qunar/fresh2017/exam1/unorderedmsg.txt");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(unorderedmsg));
            String line = null;
            while ((line = br.readLine()) != null){
                long date;
                String name = null;
                String rexDateEx = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}";
                String rexNameEx = "\\u3010.+\\u3011.+\\(\\d+\\)";
                Pattern datePattern = Pattern.compile(rexDateEx);
                Pattern namePattern = Pattern.compile(rexNameEx);
                Matcher dateMatcher = datePattern.matcher(line);
                Matcher nameMatcher = namePattern.matcher(line);
                while (dateMatcher.find()){
                    String dateStr = dateMatcher.group(0);
                    date = stringToTimeStamp(dateStr);
                    System.out.print(date);
                }
                while (nameMatcher.find()){
                    name = nameMatcher.group(0);
                    System.out.println(name);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String [] args){
        Exam1 exam1 = new Exam1();
        exam1.computeChatMap();
        System.out.println("hello");
    }

}
