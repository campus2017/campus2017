import action.ExchangeRateExport;
import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import utils.ExcelUntil;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by dayong.gao on 2016/12/27.
 */
public class Test {
    public static void main(String[] args) throws IOException {
        ExchangeRateExport exchangeRate =new ExchangeRateExport();
        List<List<String>> rate =Lists.newArrayList();
        Date date=new Date();
        DateTime dt =new DateTime(date);
        String endDate=dt.toString("yyyy-MM-dd");
        String startDate=dt.minusMonths(1).toString("yyyy-MM-dd");
        List<String> codeList = Lists.newArrayList();
        codeList.add("USD");
        codeList.add("EUR");
        codeList.add("HKD");
        rate=exchangeRate.getdata(codeList,startDate,endDate);
        ExcelUntil.toExcel(codeList,rate,"D:/rate.xls");
    }
}
