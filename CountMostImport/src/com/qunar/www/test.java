package com.qunar.www;

/**
 * Created by ZC on 2017/7/8.
 */
public class test {
    public static void main(String[] args) {
        String content ="abbscdcdc/*****/sfsdfs/87897/**5454/455*/";
        //String substring = s.substring(0, 0);
        System.out.println(content.replaceAll("/\\*(.*?)\\*/",""));
    }
}
