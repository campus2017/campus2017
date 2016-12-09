package com.youthlin.qunar.fresh;

import com.google.common.collect.Ordering;
import com.google.common.collect.TreeBasedTable;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

/**
 * Created by youthlin.chen on 2016-11-10 010.
 * 二、分析从今天开始过去 30 天时间里，中国人民银行公布的人民币汇率中间价，得到人民币对美元、欧元、
 * 港币的汇率，形成 excel 文件输出。汇率数据找相关的数据源，自己爬数据分析。（作业命名：ExchangeRate）
 */
public class ExchangeRate {
    private static final Logger log = LoggerFactory.getLogger(ExchangeRate.class);   // slf4j 日志
    private static final String EU = "欧元";
    private static final String HK = "港币";
    private static final String US = "美元";
    private static String defaultUrl = "http://www.safe.gov.cn/AppStructured/view/project_RMBQuery.action";
    private DateTime end = DateTime.now();
    private DateTime start = end.minusDays(50);//周末没有数据诶
    private DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
    private TreeBasedTable<DateTime, String, BigDecimal> rateTable = TreeBasedTable
            .create(Ordering.<DateTime>natural().reverse(), Ordering.<String>natural());

    public static void main(String[] args) {
        ExchangeRate exchangeRate = new ExchangeRate();
        try {
            exchangeRate.loadFromUrl();
        } catch (IOException e) {
            log.warn("Can not get data, please check your network.", e);
            System.exit(1);
        }
        PrintWriter out = new PrintWriter(System.out);
        File output = new File("output.csv");
        boolean created;
        try {
            created = output.createNewFile();
            if (created) {
                log.debug("Created output files.{}", output.getAbsolutePath());
            }
            out = new PrintWriter(output);
        } catch (IOException e) {
            log.debug("Can not create or read file.", e);
        }
        exchangeRate.write(out);
        log.debug("Result has been save to file: {}", output.getAbsolutePath());
    }

    private void loadFromUrl() throws IOException {
        String url = defaultUrl;
        Document document = Jsoup.connect(url)
                .data("projectBean.startDate", start.toString(formatter))
                .data("projectBean.endDate", end.toString(formatter))
                .data("queryYN", "true")
                .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36/YouthLin")
                .referrer("http://www.safe.gov.cn/AppStructured/view/project_RMBQuery.action")
                .post();
        log.trace("start = {},end = {}", start.toString(formatter), end.toString(formatter));
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("output.html"));
        out.write(document.toString());
        out.close();

        Element infoTable = document.getElementById("InfoTable");//table
        Element tbody = infoTable.child(0);//tbody
        Elements trs = tbody.children();//tr
        trs.remove(0);//第一行是文字表头
        log.trace("{} items.", trs.size());

        String rateStr;     // value String
        BigDecimal v;       // cell value
        String dateStr;     // date String
        DateTime date;      // date
        int count = 0;
        for (Element tr : trs) {
            if (++count > 30) {
                break;
            }

            dateStr = tr.child(0).html();
            dateStr = dateStr.substring(0, dateStr.lastIndexOf("&")).trim();
            date = formatter.parseDateTime(dateStr);

            rateStr = tr.child(1).html();
            rateStr = rateStr.substring(0, rateStr.lastIndexOf("&")).trim();
            v = new BigDecimal(rateStr);
            rateTable.put(date, US, v);

            rateStr = tr.child(2).html();
            rateStr = rateStr.substring(0, rateStr.lastIndexOf("&")).trim();
            v = new BigDecimal(rateStr);
            rateTable.put(date, EU, v);

            rateStr = tr.child(4).html();
            rateStr = rateStr.substring(0, rateStr.lastIndexOf("&")).trim();
            v = new BigDecimal(rateStr);
            rateTable.put(date, HK, v);
        }
    }

    private void write(PrintWriter out) {
        StringBuilder sb = new StringBuilder();
        sb.append("日期,").append(EU).append(",").append(HK).append(",").append(US).append("\r\n");
        SortedSet<DateTime> rows = rateTable.rowKeySet();
        SortedMap<String, BigDecimal> rowValue;
        Set<String> countries;
        for (DateTime date : rows) {
            rowValue = rateTable.row(date);
            countries = rowValue.keySet();
            sb.append(date.toString(formatter));
            for (String country : countries) {
                sb.append(",").append(rateTable.get(date, country));
            }
            sb.append("\r\n");
        }
        out.write(sb.toString());
        out.flush();
    }
}
