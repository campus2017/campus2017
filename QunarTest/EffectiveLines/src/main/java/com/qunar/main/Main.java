package com.qunar.main;

import com.qunar.service.EffectiveLinesService;

import java.util.Scanner;

/**
 *
 * Created by 张竣伟 on 2017/1/3.
 */
public class Main {

    public static  void main(String args[]){
        System.out.println("请输入文件路径:");
        Scanner scanner = new Scanner(System.in);
        //存储目标文件路径
        String filePath = scanner.next();
        //调用计数服务
        int count = new EffectiveLinesService(filePath).getEffectiveLinesCount();
        System.out.println(count);
    }
}
