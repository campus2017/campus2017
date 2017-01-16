package com.company;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // 读入文件名
        Scanner scanner = new Scanner(System.in);
        String filename = scanner.next();// filename = "src/com/company/test.java";

        // 判断是否 java 文件
        while (!filename.endsWith(".java")) {
            System.out.println(filename + "does not a java file. ");
            filename = scanner.next();
        }

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
            String line;
            int effectiveLines = 0;
            while ((line = in.readLine()) != null) {
                line = line.trim(); // 去除两端空格
                if (line.isEmpty() || line.startsWith("//")) {
                    // 空行注释行不算
                    continue;
                }
                else {
                    ++effectiveLines;
                }
            }
            in.close();

            System.out.println(filename + " has "+effectiveLines+" effective line(s).");
        }
        catch (IOException e) {
            System.out.println("Could not read file " + filename);
        }
    }
}
