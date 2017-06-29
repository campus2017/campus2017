package com.exchangeRate.bean;

/**
 * Created by canda on 6/30/17.
 */
public class ExchangeRateBean {
    private String currency; //货币
    private String date; //日期
    private Double rate; //汇率

    public ExchangeRateBean(String currency, String date, Double rate) {
        this.currency = currency;
        this.date = date;
        this.rate = rate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
