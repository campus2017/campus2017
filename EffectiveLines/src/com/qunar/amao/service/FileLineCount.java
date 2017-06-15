package com.qunar.amao.service;

import java.io.*;

/**
 * Created by FGT on 2017/5/5.
 */
public class FileLineCount extends IOException {
    /**
     * 文件行数统计
     * @param path 文件路径
     * @return 统计的行数
     */
    public static int fileLineCount(String path){
        int codeLines = 0;//代码行
        int blankLines = 0;//空白行
        int commentLines = 0;//注释行
        int count= 0 ;//有效行数

        //根据路径查找文件
        File file = new File(path);

        //校验文件是否存在
        if(file==null || !file.exists()){
            return -1;
        }

        //校验文件后缀是否为.java
        if( !file.getName().endsWith(".java")){
            return -1;
        }

        //打开文件，读入数据流
        BufferedReader br = null;
        boolean flag = false;//多行注释行标志位
        try {
            br = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = br.readLine()) != null) {
                line = line.trim(); // 除去注释前的空格
                if (line.matches("^[ ]*$")) { // 匹配空行
                    blankLines++;
                } else if (line.startsWith("//")) {
                    commentLines++;
                } else if (line.startsWith("/*") && !line.endsWith("*/")) {
                    commentLines++;
                    flag = true;
                } else if (line.startsWith("/*") && line.endsWith("*/")) {
                    commentLines++;
                } else if (flag == true) {
                    commentLines++;
                    if (line.endsWith("*/")) {
                        flag = false;
                    }
                } else {
                    codeLines++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                    br = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        count = codeLines;//只统计有效代码行数，不包括注释行和空行

        return count;
    }
}
