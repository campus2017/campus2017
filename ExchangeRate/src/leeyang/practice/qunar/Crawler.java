package leeyang.practice.qunar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;

/**
 * 爬取 "http://www.safe.gov.cn/AppStructured/view/project_RMBQuery.action"的html内容
 * 从页面中提取当前日期之前n天的人民币对美元。欧元和港币的汇率中间价信息
 * 爬取数据写入DataRate自定义类中，包括日期，人民币对以上三种货币的中间价
 * 返回List<DataRate>
 */

public class Crawler {
    private static  final String url = "http://www.safe.gov.cn/AppStructured/view/project_RMBQuery.action";
    private static DateTime end;
    private static DateTime start;
    private static DateTimeFormatter formatTime = DateTimeFormat.forPattern("yyyy-MM-dd");

    public Crawler (int days) {
        this.end = DateTime.now();
        this.start = end.minusDays(days);
    }

    public List<DataRate>  getFormateData()
            throws IOException {
        Document document = Jsoup.connect(url)
                    .data("projectBean.startDate", start.toString(formatTime))
                    .data("projectBean.endDate", end.toString(formatTime))
                    .data("queryYN", "true")
                    .referrer(url)
                    .post();
        Elements htmlTable = document.select(".list tr");

        htmlTable.remove(0);
        //格式化提取信息
        int indexOfDate = 0;
        int indexOfUsa = 1;
        int indexOfEu = 2;
        int indexOfHk = 4;
        String dateStr;
        float usaPrice;
        float euPrice;
        float hkPrice;

        List<DataRate> listPrice= new ArrayList<DataRate>();

        for (Element tr: htmlTable) {
            dateStr = tr.child(indexOfDate).html();
            String usaPriceStr = tr.child(indexOfUsa).html();
            String euPriceStr = tr.child(indexOfEu).html();
            String hkPriceStr = tr.child(indexOfHk).html();

            dateStr = dateStr.substring(0, dateStr.lastIndexOf("&")).trim();
            usaPriceStr = usaPriceStr.substring(0, usaPriceStr.lastIndexOf("&")).trim();
            euPriceStr = euPriceStr.substring(0, euPriceStr.lastIndexOf("&")).trim();
            hkPriceStr = hkPriceStr.substring(0, hkPriceStr.lastIndexOf("&")).trim();

            usaPrice = new Float(usaPriceStr) / 100;
            euPrice = new Float(euPriceStr) / 100;
            hkPrice = new Float(hkPriceStr) / 100;

            DataRate dataRate = new DataRate(dateStr, usaPrice, euPrice, hkPrice);
            listPrice.add(dataRate);
        }
        return listPrice;
    }
}
