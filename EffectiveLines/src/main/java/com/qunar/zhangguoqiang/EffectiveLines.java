package com.qunar.zhangguoqiang;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
/**
 *统计一个Java文件的有效行数。 1、有效不包括空行 2、不考虑代码见有多行注释的情况
 */
public class EffectiveLines {
    private String Filename;
    private int Linenum;
    public EffectiveLines(String name)
    {
        Filename=name;
        try {
            Linenum=CountEffectiveLines(name);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public String getinfo()
    {
        return "文件名："+Filename+"有效行数："+Linenum+"\n";
    }
    private int CountEffectiveLines(String filename) throws IOException {
        int count=0;
        String LineString=null;
        BufferedReader br=new BufferedReader(new FileReader(filename));
        while((LineString=br.readLine())!=null)
        {
            if(!LineString.trim().isEmpty()&&!LineString.matches("^(/\\*.*?\\*/)|(//.*)$"))
                count++;

        }
        br.close();
        return count;
    }
    public static void main(String[] args)
    {
        String Filename;
        File file;
        Scanner scanner=new Scanner(System.in);
        System.out.println("输入文件路径");
        //System.out.println(System.getProperty("user.dir"));
        Filename=scanner.nextLine();
        file=new File(Filename);
        if(!file.exists())
        {
            System.out.println("路径错误\n");
            return;
        }
        EffectiveLines EL=new EffectiveLines(Filename);
        System.out.println(EL.getinfo());
    }

}