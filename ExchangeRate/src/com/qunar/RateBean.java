package com.qunar;

/**
 * 汇率实体
 * Created by WanlongMa on 2016/12/23.
 */
public class RateBean {

    private String sourceUrl;               // 来源页面
    private String pubDate;                 // 发布时间
    private String middleRateDollarStr;     // 美元人民币汇率中间价
    private String middleRateEuroStr;       // 欧元人民币汇率中间价
    private String middleRateHKStr;         // 港元人民币汇率中间价
    private float rateDollar;               // 人民币对美元汇率
    private float rateEuro;                 // 人民币对欧元汇率
    private float rateHK;                   // 人民币对港元汇率

    public String getSourceUrl() {
        return sourceUrl;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getMiddleRateDollarStr() {
        return middleRateDollarStr;
    }

    public String getMiddleRateEuroStr() {
        return middleRateEuroStr;
    }

    public String getMiddleRateHKStr() {
        return middleRateHKStr;
    }

    public float getRateDollar() {
        return rateDollar;
    }

    public float getRateEuro() {
        return rateEuro;
    }

    public float getRateHK() {
        return rateHK;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public void setMiddleRateDollarStr(String middleRateDollarStr) {
        this.middleRateDollarStr = middleRateDollarStr;
    }

    public void setMiddleRateEuroStr(String middleRateEuroStr) {
        this.middleRateEuroStr = middleRateEuroStr;
    }

    public void setMiddleRateHKStr(String middleRateHKStr) {
        this.middleRateHKStr = middleRateHKStr;
    }

    public void setRateDollar(float rateDollar) {
        this.rateDollar = rateDollar;
    }

    public void setRateEuro(float rateEuro) {
        this.rateEuro = rateEuro;
    }

    public void setRateHK(float rateHK) {
        this.rateHK = rateHK;
    }

    @Override
    public String toString() {
        return "RateBean{" +
                ", pubDate='" + pubDate + '\'' +
                ", middleRateDollarStr='" + middleRateDollarStr + '\'' +
                ", middleRateEuroStr='" + middleRateEuroStr + '\'' +
                ", middleRateHKStr='" + middleRateHKStr + '\'' +
                ", rateDollar=" + rateDollar +
                ", rateEuro=" + rateEuro +
                ", rateHK=" + rateHK +
                '}';
    }
}
