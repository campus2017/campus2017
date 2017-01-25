package com.qunar.ex_rate.commom;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by gcy0904 on 2017/1/20.
 */
public class getHtmlPage {
    public static String getHtml(String url_str){
        String charset = "utf-8";
        //输入流到字符串转化
        String htm_str = null;
        //创建代理并创建访问方式
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url_str);
        //获得响应和响应内容
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
        } catch (IOException e) {
            System.out.print(e);
        }
        HttpEntity entity = response.getEntity();
        //按照流方式进行输入
        InputStream htm_in = null;

        if(entity != null){
            System.out.println(entity.getContentLength());
            try {
                htm_in = entity.getContent();
            } catch (IOException e) {
                System.out.print(e);
            }

            try {
                htm_str = InputStream2String(htm_in,charset);
            } catch (IOException e) {
                System.out.print(e);
            }
        }
        //返回字符串
        return htm_str;
    }

    /**
     * Method: InputStream2String
     * Description: make InputStream to String
     * @param in_st
     * inputstream which need to be converted
     * @param charset
     * encoder of value
     * @throws IOException
     * if an error occurred
     */
    private static String InputStream2String(InputStream in_st,String charset) throws IOException{
        BufferedReader buff = new BufferedReader(new InputStreamReader(in_st, charset));
        StringBuffer res = new StringBuffer();
        String line = "";
        while((line = buff.readLine()) != null){
            res.append(line);
        }
        return res.toString();
    }
}
