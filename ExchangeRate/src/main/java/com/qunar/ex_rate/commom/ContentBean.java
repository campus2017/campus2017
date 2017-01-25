package com.qunar.ex_rate.commom;

/**
 * Created by zhuyin on 16-11-16.
 */
public class ContentBean {
    String date;
    float price;

    public ContentBean(String date, float price) {
        this.date = date;
        this.price = price;
    }

    public ContentBean(){

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
