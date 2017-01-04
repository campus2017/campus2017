package com.qunar.meta;

/**
 * 汇率类
 * Created by 张竣伟 on 2017/1/3.
 */
public class ExchangeRate {
    private String name;
    private double rate;
    private String date;

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
