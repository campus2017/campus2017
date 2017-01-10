package com.qunar;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by NULL on 2017/1/7.
 */
public class Test {

    public static void main(String[] args){

        String url = "http://www.pbc.gov.cn/zhengcehuobisi/125207/125217/125925/17105/index2.html";
        Document doc = null;

        Map<String, String> header = new HashMap<>();
        header.put("Host", "http://info.bet007.com");
        header.put("User-Agent", "  Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/5.0");
        header.put("Accept", "  text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        header.put("Accept-Language", "zh-cn,zh;q=0.5");
        header.put("Accept-Charset", "  GB2312,utf-8;q=0.7,*;q=0.7");
        header.put("Connection", "keep-alive");
        Connection connect = Jsoup.connect(url).timeout(1000 * 100).data(header);

        try{
            Connection.Response response = connect.execute();
            connect.cookies(response.cookies());
            doc = response.parse();
        }catch(IOException e){
            e.printStackTrace();
        }

        System.out.println(doc.toString());

    }

}
