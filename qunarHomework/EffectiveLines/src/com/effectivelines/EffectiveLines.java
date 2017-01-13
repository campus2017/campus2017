package com.effectivelines;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 统计一个Java文件的有效行数。有效行不包括空行。不考虑代码见有多行注释的情况
 * @author liuyang[QA]
 * 功能
 * 1.统计指定文件路径下的代码的有效行数
 * 2.可以识别空行；
 * 3.可识别以// 或 /* 开头的单行注释
 * */

public class EffectiveLines {
    public static void main(String []args){
        File f = new File("F:\\IntelliJIDEA\\FirstSample\\src\\FirstSample\\EffectiveLines.java");
        BufferedReader br ;
        int i;
        try{
            br = new BufferedReader(new FileReader(f));
            String tempStr;
            int effectiveLine=0,totalLine=0;
            while((tempStr=br.readLine())!=null){
                i=0;
                if(tempStr.length()!=0) {
                    while (tempStr.charAt(i) == ' ')
                        i++;
                    if (tempStr.charAt(i) != '/')
                        effectiveLine++;
                }
                totalLine++;
            }
            System.out.println("代码总行数为："+totalLine);
            System.out.println("代码有效行数为："+effectiveLine);
            br.close();
        }catch(IOException e) {
            System.out.println("读取失败！可能的原因如下："+'\n'+"1.指定的的文件路径不存在！2.指定的文件不是有效文件！");
//            e.printStackTrace();
        }
        }
    }

