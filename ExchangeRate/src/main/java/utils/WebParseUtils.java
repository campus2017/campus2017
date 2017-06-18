package utils;

import bean.RateData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by libo on 2017/6/12.
 *
 * 获取从指定时间开始到结束的所有汇率数据
 * 如果参数给null，则默认查找从今天开始过去30天时间里的数据
 *
 *  如果获取网站信息失败，返回null
 */
public class WebParseUtils {

    private final static String url = "http://www.safe.gov.cn/AppStructured/view/project_RMBQuery.action";

    //解析获取日期、人民币对美元、欧元、港币的汇率
    private static List<RateData> parsePage(Document doc){
        List<RateData> list = new ArrayList<RateData>();
        Elements info = doc.select("tr.first");
        for (Element item : info){
            list.add(new RateData(item.child(0).text(), item.child(1).text(),
                                  item.child(2).text(), item.child(4).text()));

        }

        return list;
    }


    public static List<RateData> getRateData(Date startDate, Date endDate){
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
