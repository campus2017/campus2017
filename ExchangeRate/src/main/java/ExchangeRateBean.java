/**
 * Created by Administrator on 2017/6/13.
 */
public class ExchangeRateBean {

    private String currency;
    private String date;
    private Double price;

    public ExchangeRateBean(String currency, String date, double price) {
        this.currency = currency;
        this.date = date;
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ExchangeRateBean{" +
                "currency='" + currency + '\'' +
                ", date='" + date + '\'' +
                ", price=" + price +
                '}';
    }
}
