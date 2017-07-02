package com.java;

/**
 * Created by apple on 17/7/1.
 */
public class exchangeRateData {



    private String date;
    private double dollar;//美元汇率
    private double euro;//欧元汇率
    private double hkd;//港币汇率

    /**
     *
     * @param todayDate 时间
     * @param todayDollar 美元汇率
     * @param todayEuro 欧元汇率
     * @param todayHkd 港币汇率
     */
    public exchangeRateData(String todayDate,double todayDollar,double todayEuro,double todayHkd)
    {
     date=todayDate;
        dollar=todayDollar;
        euro=todayEuro;
        hkd=todayHkd;
    }
    public String getDate() {
        return date;
    }

    public double getDollar() {
        return dollar;
    }

    public double getEuro() {
        return euro;
    }

    public double getHkd() {
        return hkd;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public void setDollar(int dollar) {
        this.dollar = dollar;
    }

    public void setEuro(int euro) {
        this.euro = euro;
    }

    public void setHkd(int hkd) {
        this.hkd = hkd;
    }
}
