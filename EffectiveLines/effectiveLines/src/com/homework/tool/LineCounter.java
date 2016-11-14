package com.homework.tool;

import jdk.internal.util.xml.impl.Input;

import java.io.*;

/**
 * Created by dell on 2016/11/14.
 */
public class LineCounter {
    public static int countEffectiveLine(File file) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String tempString = null;
        int line = 0;
        // 按照行读入
        while ((tempString = reader.readLine()) != null) {
            //不包括空行，//开头的注释，/*开头注释，不考虑多行，但是有一行的情况
            if(!tempString.trim().isEmpty() && !tempString.trim().startsWith("//") &&
                    !tempString.trim().startsWith("/*")){
                // debug
                line++;
                System.out.println("line " + line + ": " + tempString);
            }
        }
        reader.close();
        return line;
    }
}
