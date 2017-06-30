
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by honglin.li on 2017/6/11.
 */
public class Main {

    public static void main(String args[]) throws IOException{

        ArrayList<ArrayList<RateUnit>> rate_info = RateSpider.getRateList();
        RateExcel rateExcel = new RateExcel("exchangerate.xlsx");

        rateExcel.addExcelContent(RateUtil.rateinfoDealToRateExcel(rate_info));
        rateExcel.flushExcel();

       /* for (ArrayList<RateUnit> arr : rate_info) {
            for (RateUnit r : arr) {
                System.out.println(r);
            }
        }*/



    }
}
