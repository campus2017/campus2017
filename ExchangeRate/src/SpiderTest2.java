import data.ExchangeRateData;
import util.HtmlAnalyze;
import util.StringSplit;
import util.WriteToExcel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/21.
 */
public class SpiderTest2 {

    public static void main(String[] args) {

        List<String> list = null;
        try {
            list = ExchangeRateData.getExchangeRateData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String path = "F:/ExchangeRate.xls";
        WriteToExcel.writeToExcel(list,path);
    }

}
