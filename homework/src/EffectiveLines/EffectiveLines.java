package EffectiveLines;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class EffectiveLines {

    /**
     * 一、统计一个 Java 文件的有效行数。（作业命名：EffectiveLines）
     1、有效不包括空行
     2、不考虑代码见有多行注释的情况
     */

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("c:/Child.java")));
            int count = 0;
            String line;
            while ((line = reader.readLine()) != null){
                if(line.startsWith("//") || "".equals(line.trim())){
                    continue;
                }
                count++;
            }
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
