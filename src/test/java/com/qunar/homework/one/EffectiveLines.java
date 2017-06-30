package com.qunar.homework.one;

import java.io.*;

/**
 * Created by deep on 2017/6/12.
 */
public class EffectiveLines {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("请输入路径！");
        }
        try {
            for (String filePath : args) {
                LineNumberReader reader = new LineNumberReader(new InputStreamReader(new FileInputStream(filePath)));
                int result = EffectiveLines.CountLines(reader);
                System.out.printf("文件 %s 的有效行数是 %d \n", filePath, result);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static int CountLines(LineNumberReader reader) {
        int result = 0;
        try {
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                String trimLine = line.trim();
                if (trimLine.equals("") || trimLine.startsWith("/") || trimLine.startsWith("*")) {
                    continue;
                }
                result++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}