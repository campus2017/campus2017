package shen;

import java.io.*;

//要求1：不考虑空行
//要求2：不考虑多行注释的情况
//该作业以跟目录下的demo.java为校验对象。
public class Main {

    public static void main(String[] args) {
        //读取.java文件
        Main main=new Main();
        String string="demo.java";
        System.out.println("The effective lines is "+main.count(string));
    }

    public int count(String string) {
        int count=0;
        try {
            File file=new File(string);
            BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
            String temp="";
            temp=bufferedReader.readLine();
            while (temp!=null) {
                String s=temp.trim();
                temp=bufferedReader.readLine();
                if (s.length()>0) {
                    //去除“//”开头的行
                    if (s.length()>=2&&s.substring(0, 2).equals("//")) {
                        continue;
                    }
                    count++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }
}
