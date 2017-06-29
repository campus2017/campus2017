package com.effectivelines;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by canda on 6/28/17.
 */
public class EffectiveLines {


    public static void main(String[] args) throws  FileNotFoundException{
        System.out.println("input the java file name:");
        Scanner scan = new Scanner(System.in);
        String fileName = scan.nextLine();
        //the number of effective lines
        int num = 0;
        File javaFile = new File(fileName);
        //System.out.println();
        num=countEffectiveLines(javaFile);
        System.out.println("the num of effective lines is:"+ num);

    }

    public static int countEffectiveLines(File javaFile) throws FileNotFoundException{
        int num=0;
        if(javaFile == null || !javaFile.exists()){
            throw new FileNotFoundException("file can not be found!");
        }
        BufferedReader read = new BufferedReader(new InputStreamReader(new FileInputStream(javaFile)));
        Pattern effectiveLinePattern = Pattern.compile("(?!import|package).+;\\s*(((//)|(/\\*+)).*)*",Pattern.MULTILINE+Pattern.DOTALL);
        try {
            String everyLine = null;
            while((everyLine = read.readLine())!=null){
                if (effectiveLinePattern.matcher(everyLine).find()){
                    num++;
                }
            }
        } catch (IOException e){
            throw new RuntimeException("read file failed.");
        } finally {
            try {
                read.close();
            } catch (IOException e) {
                throw new RuntimeException("close bufferedreader failed.");
            }
        }
        return num;
    }
}
