package com.main;

import com.main.bean.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class netObtain {
	private final static String url = "http://www.safe.gov.cn/AppStructured/view/project_RMBQuery.action";

    //解析获取日期、人民币对美元、欧元、港币的汇率
    private static List<data> parsePage(Document doc){
        List<data> list = new ArrayList<data>();
        Elements info = doc.select("tr.first");
        for (Element item : info){
            list.add(new data(item.child(0).text(), item.child(1).text(),
                                  item.child(2).text(), item.child(4).text()));

        }

        return list;
    }


    public static List<data> getRateData(Date startDate, Date endDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String start = null;                                 //设置开始时间，默认是30天之前
        if (startDate == null){
            Calendar calendar = new GregorianCalendar();
            calendar.add(Calendar.DATE, -30);
            start = sdf.format(calendar.getTime());
        }
        else{
            start = sdf.format(startDate);
        }
        String end = endDate == null ? sdf.format(new Date()) : sdf.format(startDate);      //设置结束时间，默认是当天查询时间

        Document doc = null;
        try {
            doc = Jsoup.connect(url)
                       .data("projectBean.startDate", start)
                       .data("projectBean.endDate", end)
                       .post();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        if (doc == null){
            return null;
        }

        return parsePage(doc);
    }
}
