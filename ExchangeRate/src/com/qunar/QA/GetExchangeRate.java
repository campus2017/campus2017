package com.qunar.QA;

/**
 * @author  Nicole
 * @Time  2017/7/2
 * @Description 在相关数据源的网页上获取汇率数据
 */

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class GetExchangeRate {

    public static String[][] getExchangeRate( Document doc) {

        Elements elements1 = doc.select("table").select("tbody").select("tr");
        int length = elements1.size();                //获取中国人民银行过去30天公布的人民币汇率的记录条数
        String[][] data = new String[2][length];      // 二维字符串数组用来存储日期和汇率

        // 存储日期
        for (int i = 0; i < length; i = i + 1) {
            data[0][i] = elements1.get(i).select("td").get(0).text();
        }
        // 存储汇率
        for (int i = 0; i < length; i = i + 1) {
            data[1][i] = elements1.get(i).select("td").get(1).text();
        }
        return data;
    }
}
