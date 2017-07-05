import org.apache.poi.ss.formula.ptg.ArrayPtg;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.ArrayList;

/**
 * Created by honglin.li on 2017/6/30.
 */
public class RateUtil {

    private static final String MAX_RATE = "最大汇率";
    private static String MIN_RATE = "最小汇率";
    private static final String AVG_RATE = "平均汇率";

    private static ArrayList<String> maxinfo = new ArrayList<String>();
    private static ArrayList<String> mininfo = new ArrayList<String>();
    private static ArrayList<String> avginfo = new ArrayList<String>();

    static {
        maxinfo.add(MAX_RATE);
        mininfo.add(MIN_RATE);
        avginfo.add(AVG_RATE);
        for (int i = 0; i < 2; i++) {
            maxinfo.add("");
            mininfo.add("");
            avginfo.add("");
        }
    }

    public static double getMaxExchangeRate(ArrayList<RateUnit> rate_info) {

        return 0;
    }

    public static double getMinExchangeRate(ArrayList<RateUnit> rate_info) {

        return 0;
    }

    public static double getMidExchangeRate(ArrayList<RateUnit> rate_info) {

        return 0;
    }

    public static ArrayList<ArrayList<String>> rateinfoDealToRateExcel(ArrayList<ArrayList<RateUnit>> rate_info) {
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();


        for (ArrayList<RateUnit> arrrate : rate_info) {

            double maxRate = 0, minRate = 100000, sumRate = 0, cnt = 0;
            String type = "";
            for (RateUnit r : arrrate) {
                type = r.getType();
                cnt++;
                sumRate += r.getRate();
                maxRate = Math.max(maxRate, r.getRate());
                minRate = Math.min(minRate, r.getRate());
                ArrayList<String> tmp = new ArrayList<String>();
                tmp.add(r.getDate());
                tmp.add(r.getType());
                tmp.add(Double.valueOf(r.getRate()).toString());
                result.add(tmp);
            }
            maxinfo.set(1, type);
            mininfo.set(1, type);
            avginfo.set(1, type);
            maxinfo.set(2, Double.valueOf(maxRate).toString());
            mininfo.set(2, Double.valueOf(minRate).toString());
            avginfo.set(2, Double.valueOf(sumRate / cnt).toString().substring(0, 8));
            result.add(maxinfo);
            result.add(mininfo);
            result.add(avginfo);
        }

        return result;
    }
}
