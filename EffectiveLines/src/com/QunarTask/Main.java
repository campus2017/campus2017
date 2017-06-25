package com.QunarTask;

import java.io.*;
import java.util.Scanner;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class Main {

    // 记录有效代码的行数
    static long effectiveLines = 0;

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("请输入要统计代码量的java文件路径：");
        Scanner in = new Scanner(System.in);
        String filePath = in.nextLine();

        File file = new File(filePath);
        // 根据用户输入的文件名执行代码量统计
        codeStat(file);
        System.out.println("－－－－－－－－－－统计结果－－－－－－－－－");
        System.out.println("有效代码行数：" + effectiveLines);
        System.out.println("－－－－－－－－－－－－－－－－－－－－－－－");

    }

    private static void codeStat(File file) throws FileNotFoundException {
        if (file == null || !file.exists())
            throw new FileNotFoundException(file + "，文件不存在！");


        BufferedReader bufr = null;
        try {
            // 将指定路径的文件与字符流绑定
            bufr = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(file + "，文件不存在！" + e);
        }

        // 遍历文件中的每一行，对其进行判定是否为有效代码行
        String line = null;
        try {
            while((line = bufr.readLine()) != null) {
                if(isEffectiveLine(line))
                    effectiveLines++;
            }

        } catch (IOException e) {
            throw new RuntimeException("读取文件失败！\n" + e);
        } finally {
            try {
                bufr.close();   // 关闭文件输入流并释放系统资源
            } catch (IOException e) {
                throw new RuntimeException("关闭文件输入流失败！");
            }
        }

    }

    private static boolean isEffectiveLine(String line) {
        boolean FLAG=TRUE;
        line = line.trim();
        //将空行和注释行标记为非有效代码行
        if(line.equals("")||line.startsWith("//")||(line.startsWith("/*")&&line.endsWith("/*")))
            FLAG=FALSE;
        return FLAG;
    }

}

