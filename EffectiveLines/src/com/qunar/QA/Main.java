package com.qunar.QA;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;

/**
 * @Author Nicole
 * @Time  2017/7/1
 * @Description   统计一个Java文件的有效行数。1,有效不包括空行;2,不考虑代码见有多行注释的情况.
 * 可用test文件进行测试
 */

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("请输入java文件路径：");
        Scanner in = new Scanner(System.in);
        String filePath = in.nextLine();
        File file = new File(filePath);

        // 根据用户输入的文件名获取有效代码行数
        GetEffectiveLines gel=new GetEffectiveLines();
        int effectiveLines=gel.getEffectiveLines(file);
        System.out.println("有效行数：" +  effectiveLines);

    }

}
