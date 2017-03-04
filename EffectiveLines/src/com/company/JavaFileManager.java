package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Administrator on 2017/2/12.
 */
public class JavaFileManager {
    private File m_file = null;

    public JavaFileManager(){

    }

    public String CheckPath(){
        Scanner sc = new Scanner(System.in);

        //file path
        System.out.println("请输入java文件路径：");

        String ret = this.CheckSuffix(sc);

        if(ret.equals("0")){
            return "0";
        }

        if(this.CheckExist(sc,ret).equals("0")){
            return "0";
        }

        return "";
    }

    public void AnalysisFile(){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(m_file));
            String temp = null;
            int line = 0,totalLine = 0;

            while ((temp = reader.readLine()) != null) {
                if(IsEffectiveLines(temp))
                {
                    line++;
                }
                totalLine++;
            }
            reader.close();
            System.out.println("有效代码行数：" + line + "行,总行数：" + totalLine + "行");
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件读取出错");
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    public void Close(){
        if(m_file != null){
            m_file = null;
        }
    }

    private String CheckSuffix(Scanner sc){
        String filePath = sc.nextLine();
        boolean outFlag = false;

        while(filePath == null || !filePath.trim().endsWith("java")){
            System.out.println("请输入java类型的文件,输入0退出程序");
            filePath = sc.nextLine();

            if(filePath.equals("0")){
                outFlag = true;
                break;
            }
        }

        if(outFlag){
            return "0";
        }
        else{
            return filePath;
        }
    }

    private String CheckExist(Scanner sc,String filePath){
        m_file = new File(filePath);
        boolean outFlag = false;

        while(m_file == null || !m_file.exists()){
            System.out.println("文件不存在,请重新输入文件路径,输入0退出程序");
            filePath = sc.nextLine();

            if(m_file!=null){
                m_file = null;//release the handle
            }

            if(filePath.equals("0")){
                outFlag = true;
                break;
            }

            m_file = new File(filePath);
        }

        if(outFlag){
            return "0";
        }
        else{
            return "";
        }
    }

    private boolean IsEffectiveLines(String line){
        //empty
        if(line == null){
            return false;
        }
        else{
            line = line.trim();
            if(line.length() == 0 || line.startsWith("//")){
                return false;
            }
        }

        return true;
    }
}
