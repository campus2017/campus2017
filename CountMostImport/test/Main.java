package com.company;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        // 输入根目录
        Scanner scanner = new Scanner(System.in);
        String dir = scanner.next();

        // 打开目录，获取文件列表
        File files = new File(dir);
        String[] list = files.list(new FilenameFilter() {
            private Pattern pattern = Pattern.compile(".*\\.java");
            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }
        });

        // 逐个文件统计
        CountImport countImport = new CountImport();
        for (String filename : list) {
            countImport.add(filename);
        }
        // 输出结果
        for (ClassCount classCount : countImport.getList(10)) {
            System.out.println(classCount.classname + "  " + classCount.count);
        }
    }
}
