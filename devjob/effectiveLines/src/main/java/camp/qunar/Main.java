package camp.qunar;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by honglin.li on 2017/6/8.
 */
public class Main {
    public static void main(String args[]) {

        String filename = "D:\\java_work\\effectiveLines\\src\\main\\test.txt";
        EffectiveLine effectiveLine = new EffectiveLine(filename);

        try {
            effectiveLine.outputCount();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}




