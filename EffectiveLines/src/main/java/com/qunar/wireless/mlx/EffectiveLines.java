package com.qunar.wireless.mlx;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by mlx on 2016-12-14.
 */
public class EffectiveLines {
    // 构造函数
    public EffectiveLines(String filename){
        file = new File(filename);
    }
    // 是不是有效的java代码行
    private boolean isValidLine(String line) {
        line = line.trim();
        if(0==line.length()) {
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
    public int getLinesNum() throws IOException {
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
        }catch (IOException e) {
            throw e;
        }finally {
            if (reader!=null){
                reader.close();
            }
        }
        return  num;
    }

    private File file=null;
}
// 47