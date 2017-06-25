package com.QunarTask;

/**
 * Created by JimmyWang on 2017/6/12.
 */
public class ContentBean {
    String date;
    float exchangeRate;

    public ContentBean(String date,float exchangeRate) {
        this.date = date;
        this.exchangeRate = exchangeRate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(float exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

}
