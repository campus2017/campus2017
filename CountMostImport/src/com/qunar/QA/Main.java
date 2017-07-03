package com.qunar.QA;

import java.util.Scanner;

/**
 * @Author Nicole
 * @Time 2017/7/2.
 * @Description 根据指定项目目录下（可以认为是java源文件目录）中，统计被import最多的类，前十个是什么。
 * 可用文件夹test测试
 */

public class Main {

    public static void main(String[] args){
        System.out.println("请输入目录路径：");
        Scanner in = new Scanner(System.in);
        String dirPath = in.nextLine();

        CountImportClasses cic=new CountImportClasses(dirPath);
        GetMostImport gmi=new GetMostImport();
        gmi.getMostImport(cic.importClassRecords);


    }

}
