package com.company;

import java.io.*;

public class Main {

    public static void main(String[] args)throws Exception{
	// write your code here
        String srcString="/Users/SDD/WorkSpace_java/EffectiveLines/file/Test.java";
        BufferedReader bufferedReader=new BufferedReader(new FileReader(srcString));
        String str=null;
        int count=0;
        while((str=bufferedReader.readLine())!=null){

            String str2=str.trim();//去掉首尾空格
            if(str2.startsWith("//")||str2.isEmpty()) {//一开始就是注释或者去掉首尾空格后为空,则属于非有效行
                //System.out.print(String.valueOf(count) + str2);
                count--;
            }
            count++;
        }
        bufferedReader.close();
        System.out.println("有效行数是:"+count+"行");
    }

}
