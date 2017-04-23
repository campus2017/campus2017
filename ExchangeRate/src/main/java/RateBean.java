/**
 * Created by dang on 2017/4/22.
 * All right reserved.
 */
public class RateBean {

    private String date;
    private Double exchangeRate;

    public RateBean(String date, Double exchangeRate) {
        this.date = date;
        this.exchangeRate = exchangeRate;
    }

    public String getDate() {
        return date;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

}
