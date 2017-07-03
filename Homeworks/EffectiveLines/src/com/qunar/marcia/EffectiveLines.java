package com.qunar.marcia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Marcia on 2017/6/23.
 * 一、统计一个Java文件的有效行数。（作业命名：EffectiveLines）
 * 1、有效不包括空行
 * 2、不考虑代码见有多行注释的情况
 */
public class EffectiveLines {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入要统计的Java文件名的正确路径：");
        String fileName = scan.nextLine();
        File file=new File(fileName);
        if (!file.exists()) {
            System.out.println("文件不存在");
            System.exit(1);
        }
        if(!fileName.endsWith(".java")){
            System.out.println("不是JAVA文件");
            System.exit(1);
        }
        int effectiveLineCount = countEffectiveLine(fileName);
        System.out.println("JAVA文件：" + fileName + " 的有效行数为：" + effectiveLineCount);
    }
    public static int countEffectiveLine(String fileName) {
        int effectiveLineNum = 0;
        try {
            BufferedReader buffered_reader = new BufferedReader(new FileReader(new File(fileName)));
            String currentLine = null;
            while ((currentLine = buffered_reader.readLine()) != null) {
                if (isEffectiveLine(currentLine)) {
                    effectiveLineNum ++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return effectiveLineNum;
    }

    private static boolean isEffectiveLine(String line) {
        return !(("").equals(line) || line.startsWith("//") || line.startsWith("/*"));
    }

}
