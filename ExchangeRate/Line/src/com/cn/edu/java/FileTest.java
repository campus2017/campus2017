package com.cn.edu.java;

import java.io.IOException;
import java.io.File;
/**
 * Created by ASUS on 2017/1/14.
 * 判断文件夹下的文件属性
 */
public class FileTest {

    public static void main(String[] args) throws IOException {
        File file = new File("E:\\1.txt");
        if(!file.exists()){
            System.out.println("文件不存在同时创建该文件");
            file.createNewFile();
        }
        if(file.isFile()){
            System.out.println("file是文件");
            System.out.println(file.getPath());
        }
        String name = file.getName();
        String path1 = file.getPath();
        long size = file.length();
        System.out.println("filename："+name);
        System.out.println("path: "+path1);
        System.out.println("size: "+size);
        File dir = new File("E:\\TestFile");
        if(!dir.exists()){
            System.out.println("目录不存在同时创建该目录");
            dir.mkdirs();
        }

        File file1 = new File("E:");
        File[] files = file1.listFiles();
        for (File f : files){
            if(f.isFile()){
                System.out.println(f.getName()+"是文件！");
            }else if(f.isDirectory()){
                System.out.println(f.getPath());
            }
        }
    }
}
