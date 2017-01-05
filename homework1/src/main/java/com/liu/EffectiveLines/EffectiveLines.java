package com.liu.EffectiveLines;

import java.io.*;

/**
 * Created by liudan on 2017/1/1.
 * 统计代码有效行数，不考虑多行注释
 */
public class EffectiveLines {
    static int countCode = 0;  //代码行数
    static int countComment = 0;//单行注释行数
    static int countSpace = 0;//空行数

    public static void main(String[] args) {
        File file = new File("E:\\test\\test1.java");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null)
                pattern(line);
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("有效代码行： " + countCode);
    }

    private static void pattern(String line) {

        String regxComment = "\\s*//.*";
        String regxSpace = "\\s*";
        if (line.matches(regxSpace))
            ++countSpace;
        else if (line.matches(regxComment))
            ++countComment;
        else ++countCode;
    }
}
