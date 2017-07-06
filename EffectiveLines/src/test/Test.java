package com.qunar.logic;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Logic on 2017/7/4.
 */
public class EffectiveLines {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.nextLine();
        int effectiveLines = countEffectiveLines(filePath);
        scanner.close();
    }

    /**
     * 计算一个文件中有效代码行数
     * @param filePath
     * @return 返回有效行数
     */
    public static int countEffectiveLines(String filePath) {
        File file = new File(filePath);
        int sumLines = 0;
        String tempLine = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            while ((tempLine = bufferedReader.readLine()) != null) {
                if (isEffective(tempLine)) {
                    sumLines ++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sumLines;
    }

    /**
     * 判断一行代码是否为有效代码（非注释代码）
     * @param tempLine
     * @return
     */
    private static boolean isEffective(String tempLine) {
        if (tempLine.startsWith("//") || (tempLine.startsWith("/*") && tempLine.endsWith("*/"))) {
            return false;
        } else {
            return true;
        }
    }
}
//C:\Users\Logic\Dropbox\IntelijProject\EffectiveLines\src\test\Test.java