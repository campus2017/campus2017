/**
 * Created by dxq on 2017/7/2.
 */
package com.qunar.dxq;
public class Rate {
    private String date;
    private double usd;  //美元
    private double eud;  // 欧元
    private double hkd;  // 港币
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public double getUSD() {
        return usd;
    }
    public void setUSD(double dollar) {
        this.usd = usd;
    }
    public double getEUD() {
        return eud;
    }
    public void setEUD(double eud) {
        this.eud = eud;
    }
    public double getHKD() {
        return hkd;
    }
    public void setHKD(double hkd) {
        this.hkd = hkd;
    }
}