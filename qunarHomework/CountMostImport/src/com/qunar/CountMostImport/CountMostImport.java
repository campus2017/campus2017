package com.qunar.CountMostImport;

import java.io.IOException;

/**
 * 根据指定项目目录下（可以认为是java源文件目录）中，统计被import最多的类，前十个是什么
 * @author liuyang[QA]
 */

public class CountMostImport {
    public static void main(String []args) {
        try {
            /*
            * 下面的文件路径，可以是任何包含.java文件的本地路径
            * 如果指定的路径下不包含.java文件，会给出提示
            * */
            FindJavaFiles.listDir("F:\\IntelliJIDEA");
            if (FindJavaFiles.i==0){
                System.out.println("指定的目录下没有.java文件！");
            }
            else{
                System.out.println("指定的目录下一共有 "+FindJavaFiles.i+" 个.java文件");
                CountImport.Output();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
