package com.qunar.hotel.campus2017;

import java.io.*;
import java.util.Scanner;

/**
 * Created by fangming.yi on 2016/12/7.
 */
public class EffectiveLines {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String path = in.nextLine();
            File file = new File(path);
            if (!file.exists()) {
                System.out.println("该路径的文件不存在,请重新输入路径");
                continue;
            } else {
                int num = 0;
                try {
                    num = getEffectiveLines(file);
                    System.out.println("文件有效行数为：" + num);
                } catch (IOException e) {
                    System.out.println("文件读取失败");
                    e.printStackTrace();
                }

            }
        }
    }

    public static int getEffectiveLines(File javaFile) throws IOException {
        int lineNum = 0;
        BufferedReader reader = new BufferedReader(new FileReader(javaFile));
        String line;
        while ((line = reader.readLine()) != null) {
            line.trim();
            if (line.equals("") || line.startsWith("//") || (line.startsWith("/*") && line.endsWith("*/"))) {
                continue;
            } else {
                lineNum++;
            }
        }
        return lineNum;
    }
}
