package com.tranferwill;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by WCF on 2016/11/28.
 */
public class EffectiveLines {
    // 构造函数
    public EffectiveLines(String filename){
        file = new File(filename);
    }
    // 是不是有效的java代码行
    private boolean isValidLine(String line){
        line = line.trim();
        if(line.isEmpty()){
            return false;
        }
        if(line.length()<2){
            return true;
        }
        if(line.substring(0,2).equals("//")){
            return false;
        }
        return true;
    }
    // 文件中有多少有效代码行
    public int getLinesNum(){
        int num = 0;
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line=reader.readLine())!=null){
                if(isValidLine(line)) {
                    ++num;
                }
            }
        }catch(IOException e) {
            e.printStackTrace();
        }finally {
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
        }
        return  num;
    }

    private File file=null;
}

// 50