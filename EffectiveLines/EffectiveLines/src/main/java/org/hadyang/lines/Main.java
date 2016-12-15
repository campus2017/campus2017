package org.hadyang.lines;

import com.google.common.base.Strings;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
    public static final String REGEX = "^//";

    public static void main(String[] args) {
        System.out.println("请输入文件路径：");

        Scanner scanner = new Scanner(System.in);
        String path = scanner.next();

        File inFile = new File(path);
        if (!inFile.exists()) {
            System.out.println("文件不存在！");
            return;
        }

        int sum = 0;

        Pattern pattern = Pattern.compile(REGEX);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inFile));
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                
                if (Strings.isNullOrEmpty(line)) {
                    continue;
                }

                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    continue;
                }

                sum++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(String.format("该文件共有%d行", sum));
    }
}
