package com.qunar.fresh2017.exam1;

import java.io.*;
import java.util.*;
/Users/muhongfen/tmp/exam/src/com/qunar/fresh2017/exam1/Exam1.java
/**
 * Created by muhongfen on 17/4/14.
 */
public class Exam1{

    public static void main(String args[]) {
        List<String> lines = new ArrayList<String>();
        try {
            File file = new File("file" + File.separator + "unorderedmsg.txt");
            BufferedReader bufread;
            String read;
            bufread = new BufferedReader(new FileReader(file));
            while ((read = bufread.readLine()) != null) {
                lines.add(read);
                System.out.println(read);
            }
            bufread.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        orderTime(lines);

    }

    //按时间和姓名排序
    public static void orderTime(List<String> lines){
        List<Talk> talks = new ArrayList<Talk>();
        for(String line :lines)
        {
            talks.add(new Talk(line.split("】")[1].split("-")[0],line.split("    ")[1],line));
        }
        Collections.sort(talks);
        try {

            File file = new File("file" + File.separator + "orderedmsg.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for(Talk talk : talks){
                bw.write(talk.getContext()+"\r\n");
            }
            bw.flush();
            bw.close();
            countTalk(talks);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //统计说话次数
    public static void countTalk(List<Talk> talks){
       Map map = new HashMap<String,Integer>();
       int count;
        for(Talk talk:talks){
            String name = talk.getName();
            if(map.containsKey(name)){
                count = (Integer)map.get(name);
                map.put(name,++count);
            }
            else{
                map.put(name,1);
            }
        }
        Iterator it = map.keySet().iterator();
        String temp;
        try {

            File file = new File("file" + File.separator + "count.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            while(it.hasNext()){
                temp =(String)it.next();
                bw.write(temp+"    "+map.get(temp)+"\r\n");

            }
            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
