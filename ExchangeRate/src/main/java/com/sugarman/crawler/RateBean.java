package com.sugarman.crawler;

/**
 * Created by SugarMan on 2017/1/18.
 */
public class RateBean {

    private String date;
    private double rate;

    public RateBean(String date,double rate){
        this.date = date;
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public double getRate() {
        return rate;
    }

}
