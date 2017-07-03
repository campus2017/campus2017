package com.qunar.tsai;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Created by JoeTsai on 2017/6/17.
 * 一、统计一个 Java 文件的有效行数。 （作业命名：EffectiveLines）
 * 1、有效不包括空行
 * 2、不考虑代码见有多行注释的情况
 */

public class CountEffectiveLines {

    private static ResourceBundle bundle = ResourceBundle.getBundle("tip");

    public static void main(String[] args) {
        String tipStr = bundle.getString("tip");
        String errStr = bundle.getString("error");
        String noFileStr = bundle.getString("nofile");
        System.out.println(tipStr);
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.next();
        if (!filePath.endsWith("java")) {
            System.out.println(errStr);
            System.exit(1);
        }
        File file = new File(filePath);
        if(!file.exists()){
            System.out.println(noFileStr);
            System.exit(1);
        }
        countEffectiveLines(filePath);
    }

    private static void countEffectiveLines(String filePath) {
        String output = bundle.getString("output");
        int count = 0;
        File file = new File(filePath);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String strLine = null;
            while ((strLine = reader.readLine()) != null) {
                String str = strLine.trim();
                if (!str.startsWith("//") && !"".equals(str) && !str.startsWith("/*")) {
                    System.out.println(str);
                    count++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(output + count);
    }
}
