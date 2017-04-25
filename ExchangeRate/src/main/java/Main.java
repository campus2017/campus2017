import Service.ExchangeRateService;
import conf.Config;
import entity.ExchangeRateData;
import org.jsoup.nodes.Document;


import java.util.List;

/**
 * Created by Leon on 2017/4/24.
 */
public class Main {


    public static void main(String[] args) {

        ExchangeRateService exService = new ExchangeRateService();
        Document doc = exService.genDocument();
        List<ExchangeRateData> dataList = exService.parseExchangeRateData(doc);
        exService.saveAsExcelFile(dataList, Config.DEFAULT_FILE_PATH);
    }
}
