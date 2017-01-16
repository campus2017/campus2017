package com.company;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        String html = "http://www.chinamoney.com.cn/fe-c/historyParity.do";
        ExchangeRate er = new ExchangeRate(html);
        er.openPage();
        List<List<String>> ans = er.getExchangeRate();
        er.save("rate.xlsx");
    }
}
