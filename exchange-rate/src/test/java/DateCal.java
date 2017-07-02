import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kefa.zhang on 2017/6/15.
 */
public class DateCal {
    private static final long THIRTY_DAY = 30*24*60*60*1000L;
    public static void main(String[] args) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.format(date));
        System.out.println(sdf.format(new Date(date.getTime()-THIRTY_DAY)));
    }
}
