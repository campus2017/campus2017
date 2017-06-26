/**
 * Created by Administrator on 2017/6/26.
 */
public class RateImpl {
    String date;
    String value;

     public RateImpl(String date, String value) {
        this.date = date;
        this.value = value;
    }

    @Override
    public String toString() {
        return "RateImpl{" +
                "date='" + date + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}

