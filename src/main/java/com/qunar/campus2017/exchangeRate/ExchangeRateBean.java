package com.qunar.campus2017.exchangeRate;

/**
 * Created by chunming.xcm on 2017/1/10.
 */
public class ExchangeRateBean {
    /**
     * 日期
     */
    private String date;
    /**
     * 汇率中间价
     */
    private String value;

    public ExchangeRateBean(String date, String value) {
        this.date = date;
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public String getValue() {
        return value;
    }

}
