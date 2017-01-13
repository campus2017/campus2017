/**
 * Created by wwy on 2017/1/10 0010.
 */
public class RateBean {

    String date;
    double price;


    public RateBean(String date, double price){
        this.date = date;
        this.price = price;
    }
    public double getPrice() {
        return price;
    }
    public String getDate() {
        return date;
    }




}
