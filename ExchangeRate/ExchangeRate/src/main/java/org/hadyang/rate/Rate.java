package org.hadyang.rate;

/**
 * Created by AstaYang on 2016/11/13.
 */
public class Rate {
    String date;
    String value;

    public Rate(String date, String value) {
        this.date = date;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "date='" + date + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
