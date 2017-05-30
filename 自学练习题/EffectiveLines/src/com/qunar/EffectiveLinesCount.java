package com.qunar;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by Wang yu quan on 2017/5/29.
 * Count lines of source java file
 */
public class EffectiveLinesCount {
    // 记录注释行数
    static long annotationLine = 0;

    // 记录空白行数
    static long blankLine = 0;

    // 记录有效代码的行数
    static long codeLine = 0;

    // 代码总行数
    static long totalLine = 0;

    public static void main(String[] args) throws FileNotFoundException  {
        System.out.println("请输入要统计代码量的java文件：");
        Scanner in = new Scanner(System.in);
        String filePath = in.nextLine();

        File file = new File(filePath);
        // 根据用户输入的文件名执行代码量统计
        codeStat(file);

        System.out.println("－－－－－－－－－－统计结果－－－－－－－－－");
        System.out.println("文件" + file + "统计数据如下：");
        System.out.println("文件有效行数：" + (totalLine - blankLine));
        System.out.println("代码行数：" + codeLine);
        System.out.println("注释行数：" + annotationLine);
//        System.out.println("空白行数：" + blankLine);
        long otherLine = totalLine - (codeLine + annotationLine + blankLine);
        System.out.println("其它行数：" + otherLine);

    }

    /**
     *
     * @param file Target file which to be counted
     * @throws FileNotFoundException File not exist
     */
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

        // 定义匹配每一行的正则匹配器
        Pattern annotationLinePattern = Pattern.compile("((//)|(/\\*+)|((^\\s)*\\*)|((^\\s)*\\*+/))+",
                Pattern.MULTILINE + Pattern.DOTALL);    // 注释匹配器(匹配单行、多行、文档注释)

        Pattern blankLinePattern = Pattern.compile("^\\s*$");   // 空白行匹配器（匹配回车、tab键、空格）

        Pattern codeLinePattern = Pattern.compile("(?!import|package).+;\\s*(((//)|(/\\*+)).*)*",
                Pattern.MULTILINE + Pattern.DOTALL); // 代码行匹配器（以分号结束为一行有效语句,但不包括import和package语句）

        // 遍历文件中的每一行，并根据正则匹配的结果记录每一行匹配的结果
        String line = null;
        try {
            while((line = bufr.readLine()) != null) {
                if (annotationLinePattern.matcher(line).find()) {
                    annotationLine ++;
                }

                if (blankLinePattern.matcher(line).find()) {
                    blankLine ++;
                }

                if (codeLinePattern.matcher(line).matches()) {
                    codeLine ++;
                }

                totalLine ++;
            }

        } catch (IOException e) {
            throw new RuntimeException("读取文件失败！" + e);
        } finally {
            try {
                bufr.close();   // 关闭文件输入流并释放系统资源
            } catch (IOException e) {
                throw new RuntimeException("关闭文件输入流失败！");
            }
        }
    }
}
