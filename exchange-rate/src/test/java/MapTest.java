import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by KevinZhang on 2017/6/26.
 */
public class MapTest {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = sdf.parse("2014-10-10");
        Date parse2 = sdf1.parse("2014-10-10");
        System.out.println(parse.equals(parse2));


    }
}
