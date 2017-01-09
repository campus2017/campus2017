package ExchangeRate;

import java.util.Date;

/**
 * Created by Administrator on 2017/1/9.
 */
public class Rate {
    private String date;
    private double usd;
    private double eur;
    private double hkd;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getUsd() {
        return usd;
    }

    public void setUsd(double usd) {
        this.usd = usd;
    }

    public double getEur() {
        return eur;
    }

    public void setEur(double eur) {
        this.eur = eur;
    }

    public double getHkd() {
        return hkd;
    }

    public void setHkd(double hkd) {
        this.hkd = hkd;
    }
}
