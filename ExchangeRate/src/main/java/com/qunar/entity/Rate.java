package com.qunar.entity;

import java.util.Date;
import java.util.Map;

/**
 * Created by isc on 2017/1/10.
 */
public class Rate {
    private Map<String, Double> rate;
    private Date date;

    public Map<String, Double> getRate() {
        return rate;
    }

    public void setRate(Map<String, Double> rate) {
        this.rate = rate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "rate=" + rate +
                ", date=" + date +
                '}';
    }
}
