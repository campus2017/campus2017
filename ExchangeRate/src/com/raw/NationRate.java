package com.raw;

/**
 * Created by chenli on 2017/1/3.
 */
public class NationRate {
    private String nation = null;
    private String rate = null;
    private String date = null;
    public NationRate(){}
    public NationRate(String nation){
        this.nation = nation;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
