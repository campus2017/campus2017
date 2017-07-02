package com.java;

/**
 * Created by apple on 17/7/1.
 */
public class Main {
    public  static  void main(String []args)
    {
        getExchangeRate g=new getExchangeRate();
        if(FileOperate.WriteFile(g.getAllExchangeRate()))
        {
            System.out.println("成功生成汇率文件，在result文件夹下");
        }
        else
        {
            System.out.println("未成功生成汇率文件");
        }

    }
}
