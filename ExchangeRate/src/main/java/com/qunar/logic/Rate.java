package com.qunar.logic;

/**
 * Created by Logic on 2017/7/5.
 */
public class Rate {
    private String Date;
    private String USDollar;
    private String Euro;
    private String HKDollar;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getUSDollar() {
        return USDollar;
    }

    public void setUSDollar(String USDollar) {
        this.USDollar = USDollar;
    }

    public String getEuro() {
        return Euro;
    }

    public void setEuro(String euro) {
        Euro = euro;
    }

    public String getHKDollar() {
        return HKDollar;
    }

    public void setHKDollar(String HKDollar) {
        this.HKDollar = HKDollar;
    }
}
