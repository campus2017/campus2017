package com.lfz;
import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        int count = 0;
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String str=null;
        while ((str=reader.readLine())!= null) {
            str = str.trim();
            if (str.equals(""))
                continue;
            else if (str.startsWith("//"))
                continue;
            else if (str.startsWith("/*")) {
                if (str.endsWith("*/"))
                    continue;
                else{
                    while (!((str = reader.readLine()).endsWith("*/")));
                }
            }
            else
                count++;
        }
        System.out.println(count);
    }
}

