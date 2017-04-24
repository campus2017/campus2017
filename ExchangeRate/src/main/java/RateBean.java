/**
 * Created by dang on 2017/4/24.
 * All right reserved.
 */
public class RateBean {

    private String Date;
    private Double Rate;
    private String name;

    public RateBean(String date, Double rate) {
        Date = date;
        Rate = rate;
    }
    public RateBean() {
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public Double getRate() {
        return Rate;
    }

    public void setRate(Double rate) {
        Rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ExchangeRateBean{" +
                "Date='" + Date + '\'' +
                ", Rate=" + Rate +
                ", name='" + name + '\'' +
                '}';
    }
}
