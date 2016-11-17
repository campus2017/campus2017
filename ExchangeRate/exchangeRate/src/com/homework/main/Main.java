package com.homework.main;

import com.homework.excel.ExcelHelper;
import com.homework.tools.InfoDownloader;

import java.util.Properties;


/**
 * Created by dell on 2016/11/17.
 */
public class Main {
    private static String url;
    private static String savedName;
    static {
        Properties prop=new Properties();
        try {
            prop.load(Object.class.getResourceAsStream("/AddrConf.properties"));
            url=prop.getProperty("url");
            savedName=prop.getProperty("savedName");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        //通过post请求下载到excel表单
        if(InfoDownloader.downloadExcelFile(url,savedName)){
            System.out.println("getInfo success!");
            //删掉不需要的列
            ExcelHelper.revmoveColumns(savedName);
            System.out.println("revmove Success!");
        }else {
            System.out.println("Info fetch faild!");
        }

    }
}
