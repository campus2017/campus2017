package cn.edu.dlut.logic;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Logic on 2017/2/14.
 * 统计一个Java文件的有效行数
 * 有效行不包括空行
 * 不考虑代码有多行注释的情况
 */
public class EffectiveLines {
    /**
     * Main 方法
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        File file = new File(fileName);
        if (file.exists()) {
            countEffectiveLines(file);
        } else {
            System.out.println("The java file is not exist!");
        }
        scanner.close();
    }

    /**
     * 统计有效行数
     * @param file
     */
    public static void countEffectiveLines(File file) {
        int count = 0;
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line = null;
            while((line = bufferedReader.readLine()) != null) {
                line = line.trim();
                if(!(line.matches("^[//s&&[^//n]]*$")) && !(line.startsWith("/*") && line.endsWith("*/")) && !(line.startsWith("//"))){
                    count++;
                }
            }
            System.out.println(file.getName() + "有效行数：" + count);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
