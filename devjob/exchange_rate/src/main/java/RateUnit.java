import java.util.Date;

/**
 * Created by honglin.li on 2017/6/29.
 */
public class RateUnit {

    private String type;
    private double rate;
    private String date;

    public RateUnit(String type, double rate, String date) {

        this.type = type;
        this.rate = rate;
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "RateUnit{" +
                "type='" + type + '\'' +
                ", rate=" + rate +
                ", date=" + date +
                '}';
    }
}
