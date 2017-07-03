package com.raw;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class JsoupDeal{
    String url = null;
    String nation = null;
    ArrayList<NationRate> oneMonth = new ArrayList<NationRate>();

    public JsoupDeal(String nation, String url){
        this.nation = nation;
        this.url = url;
    }
    public ArrayList<NationRate> getOneMonth() {
        try {
            Document document = Jsoup.connect(url).get();
            Element table = document.getElementsByClass("table").get(0);
            Elements tr = table.getElementsByTag("tr");
            for (int i = 1; i < tr.size(); i++) {
                Elements td = tr.get(i).getElementsByTag("td");

                NationRate nationRate = new NationRate();
                nationRate.setDate(td.get(0).text());
                nationRate.setNation(nation);
                nationRate.setRate(td.get(1).text());
                oneMonth.add(nationRate);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return oneMonth;
    }
}
