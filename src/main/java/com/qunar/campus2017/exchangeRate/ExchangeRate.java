package com.qunar.campus2017.exchangeRate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by chunming.xcm on 2017/1/10.
 */
public class ExchangeRate {
    public List<ExchangeRateBean> exchange(String url) {
        List<ExchangeRateBean> list = new ArrayList<ExchangeRateBean>();
        try {
            Document document = Jsoup.connect(url).get();
            Elements table = document.select(".table-responsive");
            List<Element> tr = table.select("tr");
            for(int i = 1; i < tr.size(); i++) {
                String date;
                String value;
                List<Element> td = tr.get(i).select("td");
                date = td.get(0).text();
                value = td.get(1).text();
                list.add(new ExchangeRateBean(date, value));
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
