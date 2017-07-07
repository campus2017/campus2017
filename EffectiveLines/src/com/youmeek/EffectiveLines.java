package com.youmeek.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by thinkpad on 2017/7/1.
 */
public class EffectiveLines {
    static int cntCode=0, cntNode=0;
    static boolean flagNode = false;
    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("E:\\homework\\CountMostImport\\src\\com\\youmeek\\CountMostImport.java"));//读取待统计的文件，假设文件中没有空行
            String line=null;
            while((line = br.readLine()) != null)
                CountLines(line);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("注释行数为： " + cntNode);
        System.out.println("有效代码行数为： " + cntCode);
    }


    private static void CountLines(String line) {
                          //注释行数cntNode：遇到注释行，cntNode就加1
                          //代码行数cntCode：若不是注释行或空行，cntCode值加1
                          //只考虑//注释和/*单行注释情况
        if(line.trim().startsWith("//")){
            ++cntNode;
        } else if((line.trim().startsWith("/*"))){
            ++cntNode;
        }
        else if(line.equals(""))
            return;
        else
            ++cntCode;
    }

}
//运行结果
/*
注释行数为： 5
有效代码行数为： 64
 */