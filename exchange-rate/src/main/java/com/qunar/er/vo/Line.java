package com.qunar.er.vo;

import java.util.Date;

/**
 * 表示Excel的一个数据行
 * Created by KevinZhang on 2017/6/26.
 */
public class Line {
    Date date;
    double toUS;
    double toEU;
    double toHK;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getToUS() {
        return toUS;
    }

    public void setToUS(double toUS) {
        this.toUS = toUS;
    }

    public double getToEU() {
        return toEU;
    }

    public void setToEU(double toEU) {
        this.toEU = toEU;
    }

    public double getToHK() {
        return toHK;
    }

    public void setToHK(double toHK) {
        this.toHK = toHK;
    }

    @Override
    public String toString() {
        return "Line{" +
                "toUS=" + toUS +
                ", toEU=" + toEU +
                ", toHK=" + toHK +
                '}';
    }
}

