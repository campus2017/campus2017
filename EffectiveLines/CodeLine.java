package com.cn.edu.java;
import java.io.*;
import java.util.Calendar;

/**
 * 统计一个Java文件的有效行数
 * 有效不包括空行
 * 不考虑代码有多行注释的情况
 * Created by RandySarah on 2017/1/13.
 */
public class CodeLine {
    public static int effectLine = 0; //有效行数  静态对象的数据在全局是唯一的，一改都改
    public static int whiteLine = 0; //空白行数
    public static int AnnotationLine = 0; //注释行数


    public static void main(String[] args){
        File file = new File("F:\\IDEAcode");
        //File file = new File("F:\\IDEAcode\\16_11_28\\src\\com\\cn\\edu\\java");
        if(file.exists()){
            System.out.println("该目录存在");
            JudgeFile(file);//找到Java文件 目录递归，文件判断后缀是否为Java
        }
        System.out.println("有效行数："+effectLine);
        System.out.println("空白行数："+whiteLine);
        System.out.println("注释行数："+AnnotationLine);
        System.out.println("总行数："+(effectLine+whiteLine+AnnotationLine));
    }
    private  static  void JudgeFile(File file){
        if(file.isDirectory()){//目录
            File[] files = file.listFiles();
            for(int i=0;i<files.length;i++){
                JudgeFile(files[i]);
            }
        }
        if(file.isFile()){//文件
            //找到后缀是Java的文件
            if(file.getName().matches(".*\\.java")){
                CalLine(file);
            }
        }
    }
    public static void CalLine(File file){
        BufferedReader br = null;
        boolean annotation = false;
        int _effectLine = 0; //有效行数  静态对象的数据在全局是唯一的，一改都改
        int _whiteLine = 0; //空白行数
        int _AnnotationLine = 0; //注释
        try{
            br = new BufferedReader(new FileReader(file));
            String line = "";
            while((line = br.readLine())!=null){
                line = line.trim();//去掉字符串两端的空格
                if(line.matches("^[//s&&[^//n]]*$")){//空行
                    whiteLine ++;
                    _whiteLine++;
                }else if(line.startsWith("/*")&&!line.endsWith("*/")){
                    //以“/*”开头的注释行
                    AnnotationLine++;
                    _AnnotationLine++;
                    annotation = true;
                }else if(annotation == true && !line.endsWith("*/")){
                    //多行注释中的一行
                    AnnotationLine++;
                    _AnnotationLine++;
                }else if(annotation == true && line.endsWith("*/")){
                    //多行注释的结尾
                    AnnotationLine++;
                    _AnnotationLine++;
                    annotation = false;
                }else if(line.startsWith("//")){
                    //单行注释行
                    AnnotationLine++;
                    _AnnotationLine++;
                }else{
                    //正常代码
                    effectLine++;
                    _effectLine++;
                }
            }
            System.out.println(file.getName()+":有效行数"+_effectLine+",空白行数"+_whiteLine+",注释行数"
                                +_AnnotationLine+",总行数"+(_effectLine+_whiteLine+_AnnotationLine));
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(br != null){
                try{
                    br.close();
                    br = null;
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}