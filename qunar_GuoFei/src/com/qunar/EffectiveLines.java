package com.qunar;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Administrator on 2017/6/27 0027.
 */
public class EffectiveLines {
    /**
     * @param args
     */
    static int cntCode=0, cntNode=0;
    static boolean flagNode = false;
    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Desktop\\java1.java"));//读取待统计的文件
            String line=null;
            while((line = br.readLine()) != null)
                pattern(line);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("注释行： " + cntNode);
        System.out.println("代码行： " + cntCode);
    }

    private static void pattern(String line) {//统计文件中的注释行和代码行
        if(line.trim().startsWith("//")){//去除没一行首尾的空格，然后以//开头的行是注释行
            ++cntNode;
        } else if((line.trim().startsWith("/*"))){//去除没一行首尾的空格，然后以/*开头的行是注释行
            ++cntNode;
        }
        else if(line.equals(""))//若是空代码行，不作任何处理
            return;
        else
            ++cntCode;//剩余的行是代码行
    }

}
