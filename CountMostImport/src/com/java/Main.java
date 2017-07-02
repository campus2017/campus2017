package com.java;

import java.util.List;
import java.util.Scanner;

/**
 * Created by apple on 17/7/2.
 */
public class Main {
    public static void main(String []args)
    {
       Scanner input=new Scanner(System.in);
        System.out.println("路径名称：");
        String path= input.nextLine();
        fileOperate fo=new fileOperate(path);
        List<String> result=fo.getTop10();
        int i=0;
        for(String str:result)
        {
            i++;
            System.out.println(i+": "+str);
        }
    }
}
