package conf;

/**
 * Created by Leon on 2017/4/24.
 */
public interface Config {

    int INDEX_DATE = 0;
    int INDEX_USD = 1;
    int INDEX_EUR = 2;
    int INDEX_HKD = 3;

    String URL = "http://www.chinamoney.com.cn/fe-c/historyParity.do";
    int DEFAULT_TIMEOUT = 5000;

    String DATE_FORMAT = "yyyy-MM-dd";
    int DEFAULT_HISTORY_PERIOD = 30;  // days

    String DEFAULT_SHEET_NAME = "ExchangeRate";
    String DEFAULT_FILE_PATH = "./output/exchangeRate.xls";
}
