package com.qunar.fresh.exercise1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by wupei on 2017/6/30.
 */
public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        // reader
        String fileName = "src\\main\\java\\com\\qunar\\fresh\\exercise1\\Main.java";
        BufferedReader br = null;
        try {
            FileReader fr = new FileReader(fileName);
            br = new BufferedReader(fr);
            String str = br.readLine();
            int line = 0;
            while (str != null) {
                if (str.trim().length() != 0 && !str.trim().startsWith("//")) {
                    line++;
                }
                str = br.readLine();
            }
            System.out.println(line);
        } catch (java.io.IOException e) {
            LOGGER.error("读文件异常, file:{}", fileName, e);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                LOGGER.error("关闭文件异常, file:{}", fileName, e);
            }
        }
    }
}
