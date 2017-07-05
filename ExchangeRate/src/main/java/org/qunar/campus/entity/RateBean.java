package org.qunar.campus.entity;

import java.util.Map;

/**
 * Created by zhanghe on 2017/7/4.
 */
public class RateBean {
    private String name;
    private Double rateToRmb;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRateToRmb() {
        return rateToRmb;
    }

    public void setRateToRmb(Double rateToRmb) {
        this.rateToRmb = rateToRmb;
    }

    public RateBean(String name, Double rateToRmb) {
        this.name = name;
        this.rateToRmb = rateToRmb;
    }

    public RateBean() {
    }
}
