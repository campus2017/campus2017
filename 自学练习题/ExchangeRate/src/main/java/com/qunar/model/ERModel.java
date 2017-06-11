package com.qunar.model;

import java.util.Date;

/**
 * Created by bmi-xiaoyu on 2017/6/8.
 */
public class ERModel {
    private String postDate;
    private String buyingPrice;
    private String cashBuyingPrice;
    private String sellingPrice;
    private String cashSellingPrice;
    private String middlePrice;
    private String exchangeRate;


    public ERModel(String postDate, String buyingPrice, String cashBuyingPrice, String sellingPrice,
                   String cashSellingPrice, String middlePrice, String exchangeRate) {
        this.postDate = postDate;
        this.buyingPrice = buyingPrice;
        this.cashBuyingPrice = cashBuyingPrice;
        this.sellingPrice = sellingPrice;
        this.cashSellingPrice = cashSellingPrice;
        this.middlePrice = middlePrice;
        this.exchangeRate = exchangeRate;
    }

    public String getPostDate() {
        return postDate;
    }
    public String getBuyingPrice() {
        return buyingPrice;
    }

    public String getCashBuyingPrice() {
        return cashBuyingPrice;
    }

    public String getSellingPrice() {
        return sellingPrice;
    }

    public String getCashSellingPrice() {
        return cashSellingPrice;
    }

    public String getMiddlePrice() {
        return middlePrice;
    }

    public String getExchangeRate() {
        return middlePrice;
    }

    @Override
    public String toString() {
        return this.postDate + " " + this.buyingPrice  + " " + this.sellingPrice + " " + this.cashBuyingPrice + " "
                + this.cashSellingPrice + " " + this.middlePrice + " " + this.exchangeRate;
    }
}
