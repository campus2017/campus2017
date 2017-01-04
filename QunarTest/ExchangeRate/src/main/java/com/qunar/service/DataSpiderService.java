package com.qunar.service;

import com.qunar.meta.ExchangeRate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 汇率数据网络爬虫服务
 * Created by 张竣伟 on 2017/1/3.
 */
public class DataSpiderService {
    private static String[] NameArray = {"美元", "欧元", "港币"};
    private static int[] pjNameArray = {1316, 1326, 1315};
    private static String URL = "http://srh.bankofchina.com/search/whpj/search.jsp";

    public Map<String, List<ExchangeRate>> getExchangeRate() {
        Map<String, List<ExchangeRate>> exchangeRates = new HashMap<String, List<ExchangeRate>>();

        for (int i = 0; i < NameArray.length; i++) {
            exchangeRates.put(NameArray[i], new ArrayList<ExchangeRate>());
        }

        String date = null;

        //遍历从今天开始的30天
        for (int i = 0; i < 30; i++) {
            //构造日期字符串
            long time = new Date().getTime() - (long) 1000 * 60 * 60 * 24 * i;
            Date day = new Date(time);
            date = new SimpleDateFormat("yyyy-MM-dd").format(day);

            //构造请求URL
            String dataUrl = URL + "?erectDate=" + date + "&nothing=" + date + "&pjname=";

            //枚举币种
            for (int j = 0; j < pjNameArray.length; j++) {
                exchangeRates.get(NameArray[j]).add(getExchangeRateByURL(dataUrl + pjNameArray[j], date));
            }
        }

        return exchangeRates;
    }


    //从网页获取数据封装成ExchangeRate对象
    private ExchangeRate getExchangeRateByURL(String url, String date) {
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setDate(date);
        try {
            //请求网页数据，解析目标数据
            Document doc = Jsoup.connect(url).get();
            Elements row = doc.select("tr");
            Element data = row.get(2);
            exchangeRate.setName(data.select("td").get(0).text());
            exchangeRate.setRate(Double.parseDouble(data.select("td").get(5).text()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return exchangeRate;
    }

}
