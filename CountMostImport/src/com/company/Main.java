package com.company;

import java.util.ArrayList;
import java.util.Scanner;
import FileManager.FileManager;
import FileManager.ClassEntity;
import javaPackageParser.Parser;

public class Main {

    public static void main(String[] args) {
        System.out.println("请输入文件夹路径");

        Scanner in = new Scanner(System.in);

        String dir = in.nextLine();

        while (!FileManager.IsLegalDir(dir)){
            System.out.println("请输入正确的文件夹路径");
            dir = in.nextLine();
        }

        Parser javaPackageParser = new Parser(dir);

        //extract import class
        javaPackageParser.Extract();

        //count top 10
        ArrayList<ClassEntity> ret = javaPackageParser.CountTopTen();

        if(ret == null || ret.size() == 0){
            System.out.println("未找到引用的java类");
            return ;
        }

        //show result
        int len = ret.size();

        for(int i=0;i<len;i++){
            System.out.println((i+1) + "：" + ret.get(i).className + "(" + ret.get(i).count +" 次)");
        }
    }
}
