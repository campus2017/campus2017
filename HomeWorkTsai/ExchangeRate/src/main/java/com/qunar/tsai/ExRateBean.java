package com.qunar.tsai;


/**
 * Created by joeTsai on 2017/6/20.
 */

public class ExRateBean {

    private String date;  //日期数据
    private double dollar;//美元汇率
    private double euro; // 欧元汇率
    private double hkd; // 港币汇率

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getDollar() {
        return dollar;
    }

    public void setDollar(double dollar) {
        this.dollar = dollar;
    }

    public double getEuro() {
        return euro;
    }

    public void setEuro(double euro) {
        this.euro = euro;
    }

    public double getHkd() {
        return hkd;
    }

    public void setHkd(double hkd) {
        this.hkd = hkd;
    }
}
