package com.campus.homework;

import java.util.Scanner;

/**
 * Created by Administrator on 2017/7/1.
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("输入文件路径: (测试路径：test)");
        String fp = sc.nextLine();
        CountMostImport cmi = new CountMostImport();
        cmi.printTenMostImport(fp);
    }
}
