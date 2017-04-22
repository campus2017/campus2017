package com.qunar.homework.juanpingl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by juanpingl on 17/2/12.
 * 一、统计一个 Java 文件的有效行数。（作业命名：EffectiveLines）
 * 1、有效不包括空行
 * 2、不考虑代码见有多行注释的情况
 */
public class EffectiveLines {

    /**
     *
     * @param line
     * @return
     */
    public boolean isEffectiveLine(String line)
    {
        line = line.trim();
        if (line == null || "".equals(line))
        {
            return false;
        }
        if (line.startsWith("//") || (line.startsWith("/*") && line.endsWith("*/")))
        {
            return false;
        }
        return true;
    }

    /**
     *
     * @param filename
     * @return
     */
    public int getEffectiveLines(String filename)
    {
        if (filename == null)
        {
            return -1;
        }
        File file = new File(filename);
        BufferedReader reader = null;
        int count=0;

        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString=(reader.readLine()))!=null)
            {
                if (isEffectiveLine(tempString))
                {
                    count++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String filePath = "/Users/harvey/Downloads/homework/src/main/java/com/qunar/homework/juanpingl/EffectiveLines.java";
        EffectiveLines cvl = new EffectiveLines();
        int count = cvl.getEffectiveLines(filePath);
        System.out.print(count);
    }
}