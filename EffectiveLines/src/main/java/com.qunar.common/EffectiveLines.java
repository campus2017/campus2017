package com.qunar.common;

import java.io.*;

/**
 * Created by GuoChongyuan on 2017/1/7.
 */
public class EffectiveLines {
    /**
     * 一、统计一个Java文件的有效行数。（作业命名：EffectiveLines）
     * 1、有效不包括空行
     * 2、不考虑代码间有多行注释的情况
     */


    //不包含多行注释的情况
    public static int getSimpleCommentLines(String FilePath) throws IOException {

        File file = new File(FilePath);
        int line = 0;
        if (file.isFile() && file.getName().endsWith(".java")) {//为java 文件时

            //判断编码格式：
            String fileCode = getCode(file);
            BufferedReader br = null;
            try {
                br = new BufferedReader(new InputStreamReader(
                        new FileInputStream(file), fileCode));
            } catch (UnsupportedEncodingException e) {
                System.out.print("UnsupportedEncodingException");
            }
            String s = null;
            while ((s = br.readLine()) != null) {
                s = s.replaceAll("\\s", "");// \\s表示 空格,回车,换行等空白符,
                // 将空白符替换为空字符""

                if ("".equals(s)
                        || s.startsWith("//")
                        || s.startsWith("/*")
                        || s.startsWith("/**")
                        || s.startsWith("*")
                        ) {//过滤掉注释
                } else {
                    line++;
                }
            }
            br.close();//关闭读入流
        } else if (file.isDirectory()) {// 当file 为目录时，递归遍历
            line = -1;//如果输入的是文件夹，返回-1
        }
        return line;
    }

    //包含多行注释的扩展程序
    public static int getMultiLineCommentLines(String FilePath) throws IOException {

        File file = new File(FilePath);
        int line = 0;
        if (file.isFile() && file.getName().endsWith(".java")) {//为java 文件时

            //判断编码格式：
            String fileCode = getCode(file);
            BufferedReader br = null;
            try {
                br = new BufferedReader(new InputStreamReader(
                        new FileInputStream(file), fileCode));
            } catch (UnsupportedEncodingException e) {
                System.out.print("UnsupportedEncodingException");
            }
            String s = null;
            while ((s = br.readLine()) != null) {
                s = s.replaceAll("\\s", "");// \\s表示 空格,回车,换行等空白符,
                // 将空白符替换为空字符""

                if ("".equals(s)
                        || s.startsWith("//")) {//过滤掉注释
                }else if (s.startsWith("/*")
                        ||s.startsWith("/**")){
                    while((s = br.readLine()) != null && !(s.endsWith("*/"))){

                    }
                } else {
                    line++;
                }
            }
            br.close();//关闭读入流
        } else if (file.isDirectory()) {// 当file 为目录时，递归遍历
            line = -1;//如果输入的是文件夹，返回-1
        }
        return line;
    }

    private static String getCode (File file) {
        //因为常见的Java文件的编码格式为gbk或者utf-8编码，这里仅对这两种编码进行判断
        //更为详细的编码判断可以使用开源项目cpdetector，网址是：http://cpdetector.sourceforge.net/
        InputStream in= null;
        String FileCode = null;
        try {
            in = new FileInputStream(file);
            byte[] b = new byte[3];
            try {
                in.read(b);
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (b[0] == -17 && b[1] == -69 && b[2] == -65)//判断是否为utf-8编码
                FileCode =  "utf-8";
            else
                FileCode =  "gbk";
        } catch (FileNotFoundException e) {
            System.out.print("FileNotFound");
        }
         return FileCode;
    }
}


