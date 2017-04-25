package Service;

import DAO.ExcelDAO;
import DAO.ExchangeRateDAO;
import conf.Config;
import entity.ExchangeRateData;
import entity.RequestData;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by Leon on 2017/4/24.
 */
public class ExchangeRateService {

    private ExchangeRateDAO mERDAO = new ExchangeRateDAO();
    private ExcelDAO mExcelDAO = new ExcelDAO();


    public Document genDocument() {
        RequestData requestData = new RequestData(new Date(), Config.DEFAULT_HISTORY_PERIOD);
        return mERDAO.genExchangeRate(requestData);
    }

    public List<ExchangeRateData> parseExchangeRateData(Document doc) {

        List<ExchangeRateData> erList = new ArrayList<>();
        Element tableElement = doc.getElementsByTag("tbody").get(2);
        Elements trElements = tableElement.getElementsByTag("tr");
        for (int i = 1; i < trElements.size(); ++i) {   // omit the first <tr> block
            Elements tdElements = trElements.get(i).select("td");

            String date = tdElements.get(Config.INDEX_DATE).select("div").get(0).text();
            Double eRUSD = Double.valueOf(tdElements.get(Config.INDEX_USD).text());
            Double eREUR = Double.valueOf(tdElements.get(Config.INDEX_EUR).text());
            Double eRHKD = Double.valueOf(tdElements.get(Config.INDEX_HKD).text());
            ExchangeRateData data = new ExchangeRateData(date, eRUSD, eREUR, eRHKD);
            erList.add(data);
        }
        return erList;
    }

    public void saveAsExcelFile(List<ExchangeRateData> dataList, String filePath) {

        mExcelDAO.setVal(Config.DEFAULT_SHEET_NAME, 0, 0, "Date");
        mExcelDAO.setVal(Config.DEFAULT_SHEET_NAME, 0, 1, "USD/CNY");
        mExcelDAO.setVal(Config.DEFAULT_SHEET_NAME, 0, 2, "EUR/CNY");
        mExcelDAO.setVal(Config.DEFAULT_SHEET_NAME, 0, 3, "HKD/CNY");
        for(int i = 0; i < dataList.size(); ++i) {
            ExchangeRateData data = dataList.get(i);
            mExcelDAO.setVal(Config.DEFAULT_SHEET_NAME, i+1, 0, data.getmDate());
            mExcelDAO.setVal(Config.DEFAULT_SHEET_NAME, i+1, 1, data.getmUSD());
            mExcelDAO.setVal(Config.DEFAULT_SHEET_NAME, i+1, 2, data.getmEUR());
            mExcelDAO.setVal(Config.DEFAULT_SHEET_NAME, i+1, 3, data.getmHKD());
        }

        mExcelDAO.exportToExcelFile(filePath);
    }

}
