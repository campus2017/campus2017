package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        //file manager
        JavaFileManager fileManager = new JavaFileManager();
        String outMsg = "";
        boolean outFlag = false;
        Scanner sc = new Scanner(System.in);

        while(true){
            //path validation
            outMsg = fileManager.CheckPath();

            //quit
            if(outMsg.equals("0")){
                return ;
            }

            //file analysis
            fileManager.AnalysisFile();

            //close
            fileManager.Close();

            System.out.println("文件处理结束,您是否继续（Y/N）?");

            while(true){
                String ret = sc.next();

                if(ret.toLowerCase().equals("n")){
                    outFlag = true;
                    break;
                }
                else if(ret.toLowerCase().equals("y")){
                    break;
                }
                else{
                    System.out.println("输入错误,请输入字母Y或者N");
                }
            }

            if(outFlag){
                break;
            }
        }

        sc.close();
        return ;
    }
}
