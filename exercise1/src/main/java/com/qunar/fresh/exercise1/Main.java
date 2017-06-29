package com.qunar.fresh.exercise1;

import java.io.*;

/**
 * Created by wupei on 2017/6/30.
 */
public class Main {
    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            FileReader fr = new FileReader("src\\main\\java\\com\\qunar\\fresh\\exercise1\\Main.java");
            br = new BufferedReader(fr);
            String str = br.readLine();
            int line = 0;
            while (str != null) {
                if (str.trim().length() != 0) {
                    line++;
                }
                str = br.readLine();
            }
            System.out.println(line);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
