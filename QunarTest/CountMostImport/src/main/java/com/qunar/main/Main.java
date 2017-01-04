package com.qunar.main;

import com.qunar.service.CountImportService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 程序入口
 * Created by 张竣伟 on 2017/1/3.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("请输入文件路径:");
        Scanner scanner = new Scanner(System.in);
        //存储目标文件路径
        String filePath = scanner.next();
        //调用计数服务
        HashMap<String, Integer> importTop10 = new CountImportService(filePath).CountImportTop10();

        //结果输出
        for (Map.Entry item : importTop10.entrySet()) {
            String key = (String) item.getKey();
            int value = (Integer) item.getValue();
            System.out.println(key + ":" + value);
        }

    }
}
