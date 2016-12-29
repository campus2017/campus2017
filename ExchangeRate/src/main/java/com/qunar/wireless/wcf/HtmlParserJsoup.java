package com.qunar.wireless.wcf;

import com.qunar.wireless.wcf.IHtmlParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by wcf on 2016-12-23.
 */
public class HtmlParserJsoup implements IHtmlParser {

    // 提取每种货币对应的详细汇率地址
    public Map<String, String> getDataUrl(String html) {
        Map<String, String> r = new HashMap<String, String>();

        Document doc = Jsoup.parse(html,baseUrl);
        Elements table = doc.getElementsByAttributeValue("data-role","table");
        Elements dollarRows = table.select("tbody > tr");
        for(Element ele : dollarRows){

            String dollar = ele.select("td").first().text().trim();
            dollar = dollar.substring(dollar.length()-3,dollar.length());
            String url = ele.select("a[href]").attr("href");
            r.put(dollar,baseUrl+url);
//            System.out.println("==================");
//            System.out.println(dollar+"-->"+baseUrl+url);
        }

        return r;
    }

    public Map<String, String> getRate(String html) {
        // 使用 TreeMap 以获得排序的日期
        Map<String, String> r = new TreeMap<String, String>();

        Document doc = Jsoup.parse(html,baseUrl);
        Elements table = doc.select("table.table");
        Elements rateRows = table.select("tbody > tr");
        for(Element row : rateRows){
            Elements elements = row.select("td");
            String date = elements.get(0).text();
            String rate = elements.get(1).text();
            r.put(date,rate);
//            System.out.println("==================");
//            System.out.println(date+":"+rate);
        }

        return r;
    }

    public HtmlParserJsoup(String siteUrl){
        baseUrl = siteUrl;
    }

    String baseUrl = null;


}