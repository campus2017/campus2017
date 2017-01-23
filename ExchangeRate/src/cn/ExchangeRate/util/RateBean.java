package cn.ExchangeRate.util;

/**
 * Created by 朱潇翔 on 2017/1/23.
 */
public class RateBean {
    private String date;
    private String usduCNY; //美元
    private String eurCNY; //欧元
    private String hkdCNY; //港币

    public RateBean(String date, String usduCNY, String eurCNY, String hkdCNY){
        this.date = date;
        this.usduCNY = usduCNY;
        this.eurCNY = eurCNY;
        this.hkdCNY = hkdCNY;
    }

    public String getUsdCNY() {
        return this.usduCNY;
    }

    public String getEurCNY() {
        return this.eurCNY;
    }

    public String getHkdCNY() {
        return this.hkdCNY;
    }

    public String getDate() {
        return date;
    }

}
