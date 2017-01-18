/**
 * 定义一个日期 汇率类
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
