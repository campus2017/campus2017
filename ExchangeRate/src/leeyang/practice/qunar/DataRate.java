package leeyang.practice.qunar;

/**
 * 用于保存日期和不同货币种类的人民币汇率中间价
 */

public class DataRate {
    private String date;
    private float usaPrice; //人民币对美元的汇率中间价
    private float euPrice; //人民币对欧元的汇率中间价
    private float hkPrice; //人民币对港币的汇率中间价

    public DataRate(String date, float usaPrice, float euPrice, float hkPrice) {
        this.date = date;
        this.usaPrice = usaPrice;
        this.euPrice = euPrice;
        this.hkPrice = hkPrice;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public float getUsaPrice() {
        return usaPrice;
    }

    public void setUsaPrice(float usaPrice) {
        this.usaPrice = usaPrice;
    }

    public float getEuPrice() {
        return euPrice;
    }

    public void setEuPrice(float euPrice) {
        this.euPrice = euPrice;
    }

    public float getHkPrice() {
        return hkPrice;
    }

    public void setHkPrice(float hkPrice) {
        this.hkPrice = hkPrice;
    }
}
