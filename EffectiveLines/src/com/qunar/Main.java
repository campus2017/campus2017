package com.qunar;

import java.io.*;

/**
 * Qunar2017 Homework 1
 * 统计一个Java文件的有效行数。（作业命名：EffectiveLines）
 *  1> 有效不包括空行
 *  2> 不考虑代码见有多行注释的情况
 *
 * Created by WanlongMa on 2016/12/21.
 * 测试源文件：text-1.txt
 *
 * 注：本例程对“有效行”的定义：除去空行、注释行外的所有代码行。
 *
 */
public class Main extends BaseObject {

    /**
     * 测试方法
     * @param args
     */
    public static void main(String[] args) {
        String filePath = "text-1.txt";
        File file = new File(filePath);
        try{
            int res = statEffectiveLines(file);
            System.out.println("Effective Lines Count in file " + filePath + " is: " + res);
        }catch(IOException e){
           e.printStackTrace();
        }
    }

    /**
     * 统计方法
     * @param file Java源文件
     * @return
     */
    public static int statEffectiveLines(File file) throws IOException {

        int lineCounter = 0;

        if(file == null || !file.exists()){
            throwError(BaseObject.ERROR_CODE_FILE_NOR_FOUND,"Source File Not Found!");
            return lineCounter;
        }

        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader reader = new BufferedReader(isr);

        String line = reader.readLine();
        while(line != null){
            if(isEffectiveLine(line)){
                ++lineCounter;
            }
            line = reader.readLine();
        }

        fis.close();
        isr.close();
        reader.close();

        return lineCounter;

    }

    /**
     * 判断一行代码是否为有效行
     * @param line
     * @return
     */
    public static boolean isEffectiveLine(String line){
        return line != null
                && !line.trim().isEmpty()
                && !line.trim().startsWith("//")
                && !line.trim().startsWith("/*")
                && !line.trim().startsWith("*");
    }

}
