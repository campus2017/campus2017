package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main{
    public static void main(String[] args) {
        if(args.length == 0){
            System.out.println("Usage: java Main [filepath]");
            System.exit(1);
        }
        String filePath=args[0];

        EffectiveLines cl = new EffectiveLines();
        int sumLines = cl.getValidJavaLines(filePath);
        System.out.println(sumLines);
    }
}

class EffectiveLines {
    private boolean isAnnotation(String line){
        if(line==null||"".equals(line)){
            return false;
        }
        return line.startsWith("//")||(line.startsWith("/*")&&line.endsWith("*/"));
    }

    public int getValidJavaLines(String filename){
        if(filename==null){
            return 0;
        }
        FileReader fr = null;
        BufferedReader reader = null;
        int count =0;
        try {
            fr = new FileReader(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        reader = new BufferedReader(fr);
        String line = null;
        try {
            while((line = reader.readLine())!=null){
                line = line.trim();
                if(!line.isEmpty()&&!isAnnotation(line)){
                    count++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                reader.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return count;
    }
}
