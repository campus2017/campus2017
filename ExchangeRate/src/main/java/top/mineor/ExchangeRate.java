package top.mineor;

/**
 * Created by Mineor on 2017/6/29.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ExchangeRate {

    private static String url = "http://www.chinamoney.com.cn/fe-c/historyParity.do";
    private static String exchangeRate = "USD/CNY,EUR/CNY,HKD/CNY";

    public static void main(String[] args) {

        List<String> currencies = Arrays.asList(exchangeRate.split(","));
        Map<String, Map<String, String>> records = CrawlUtils.getCentralParityRate(url,currencies);
        List<String> titles = new ArrayList<String>(currencies);
        titles.add(0, "日期");
        ExcelUtils.exportToExcel(titles, records, "人民币汇率中间价");
    }

}