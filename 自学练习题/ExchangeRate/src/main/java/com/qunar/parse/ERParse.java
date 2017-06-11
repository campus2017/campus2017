package com.qunar.parse;

import com.qunar.model.ERModel;
import com.qunar.util.HttpRequest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * 解析HTML页面，并将汇率数据存放到集合中
 * Created by bmi-xiaoyu on 2017/6/8.
 */
public class ERParse {

    /**
     * 获取页面中的数据并存入集合data中
     * @param url 待解析页面的地址
     * @param parameters 发送Post请求时所需参数
     * @return 存储有汇率数据对象的数组链表
     */
    public static List<ERModel> erParse(String url, String parameters) {
        List<ERModel> data = new ArrayList<ERModel>();

        String sr= HttpRequest.sendPost(url,parameters);
        Document doc = Jsoup.parse(sr);
        Elements div = doc.getElementsByClass("table");
//        System.out.println(div.text());
        String[] arr = div.text().split(" ");

        ERModel model = null;
        for (int i = 9; i < arr.length - 9; i++) {
            model = new ERModel(arr[i], arr[i+1], arr[i+2], arr[i+3], arr[i+4], arr[i+5], arr[i+5]);
            data.add(model);
            i = i + 5;
        }

        return data;
    }
}
