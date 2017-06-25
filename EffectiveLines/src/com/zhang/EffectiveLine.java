package com.zhang;

/**
 * Created by zhangpei on 2017/6/15.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class EffectiveLine {

    public static void main(String[] args) {

        int result=countEffectiveLines("d:\\countLineTest\\EffectiveLines.java");
        System.out.println(result);
    }

    private static int countEffectiveLines(String path) {

        int fileLine=0;
        File f=new File(path);
        try {
            FileReader s = new FileReader(f);
            BufferedReader br=new BufferedReader(s);
            String str=null;
            while((str=br.readLine())!=null){
                if(!str.trim().equals(""))//排除空白行
                    fileLine++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileLine;
    }

}
