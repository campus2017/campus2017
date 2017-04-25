package DAO;

import conf.Config;
import entity.RequestData;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

/**
 * Created by Leon on 2017/4/24.
 */
public class ExchangeRateDAO {


    public Document genExchangeRate(RequestData requestData) {
        Connection conn = Jsoup.connect(Config.URL)
                .timeout(Config.DEFAULT_TIMEOUT)
                .userAgent("Chrome")
                .timeout(Config.DEFAULT_TIMEOUT)
                .data(requestData.getRequestData());
        Document doc = null;
        try {
            doc = conn.post();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("网络错误");
        }
        return doc;
    }

    public Document genExchangeRate(RequestData requestData, int retry) {
        int attempt = 0;
        while(++attempt < retry) {
            Document doc = genExchangeRate(requestData);
            if (doc != null) {
                return doc;
            } else {
                continue;
            }
        }
        System.out.println("网络错误");
        return null;
    }
}
