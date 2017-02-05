package com.cn.edu.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ASUS on 2017/1/22.
 * 抓取百度图标
 */
public class Spider {

    static String SendGet(String url){
        String result = "";//定义字符串用来存储网页的内容
        BufferedReader in = null;//缓冲字符输入流
        try {
            URL realUrl = new URL(url);//将string转成url对象
            URLConnection connection = realUrl.openConnection();//初始化一个链接到url
            connection.connect();//开始实际的链接
            //初始化BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            //用来临时存储抓取到的每一行的数据
            String line;
            while ((line = in.readLine()) != null) {
                result += line;//遍历抓取到的每一行并将其存储到result里
            }
        }catch (Exception e){
            System.out.println("GET请求出现异常"+e);
            e.printStackTrace();
        }finally {
            try{
                if(in != null){
                    in.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        return result;
    }
    static String RegexString(String targetStr,String patternStr){
        //定义一个样式模板，此中使用正则表达式，括号中是要抓的内容
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(targetStr);
        if(matcher.find()){
            return matcher.group();
        }
        return "";
    }

    public static void main(String[] args) throws IOException {
        String url = "http://www.baidu.com";//定义即将访问的链接
        String result = SendGet(url);
        String imgSrc = RegexString(result,"src=.+\\.png");
        System.out.println(imgSrc);
    }
}
