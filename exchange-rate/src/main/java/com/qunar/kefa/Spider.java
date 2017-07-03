package com.qunar.kefa;

import com.google.common.collect.Maps;
import com.qunar.er.vo.Line;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by kefa.zhang on 2017/6/15.
 */
public class Spider {
    private static final long ONE_DAY = 24 * 60 * 60 * 1000L;

    private Document doc = null;
    private CurrencyEnum currency;
    public Spider(CurrencyEnum currency, Date start, int duration) {
        this.currency = currency;

        try {
            doc = Jsoup.connect(currency.getUrl() + getParam(start, duration)).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 爬取数据
     * @return
     */
    public Map.Entry<CurrencyEnum, Map<Date, Line>> getData(){
        Map<Date,Line> map = Maps.newTreeMap();
        Elements trs = doc.select(".table tr");
        for (Element tr : trs) {
            Elements tds = tr.select("td");
            if(tds.size()==6){
                Date date = getDate(tds.get(0).text());
                double rate = 0;
                String str = tds.get(5).text();
                if(str != null && str.length() > 0){
                    str = str.replaceAll("\\u00A0", "").trim();
                    rate = Double.parseDouble(str);
                    putMap(map,date,rate);
                }
            }
        }
        return Maps.immutableEntry(currency,map);
    }

    private void putMap(Map<Date, Line> map, Date date, double rate) {
        Line line = new Line();
        line.setDate(date);
        if(this.currency == CurrencyEnum.USD){
            line.setToUS(rate);
        } else if(this.currency == CurrencyEnum.EUR){
            line.setToEU(rate);
        } else {
            line.setToHK(rate);
        }
        map.put(date,line);

    }

    /**
     * 拼接时间查询的参数
     * @param start
     * @param duration
     * @return
     */
    private String getParam(Date start, int duration) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String to = sdf.format(start);
        String from = sdf.format(new Date(start.getTime() - (duration-1) * ONE_DAY));
        return "?datefrom="+from+"&dateto="+to;
    }

    private Date getDate(String d){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  date;
    }
}
