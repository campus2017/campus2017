package DAO;


import entity.RequestData;
import org.jsoup.nodes.Document;
import org.junit.Test;


import java.util.Date;

/**
 * Created by Leon on 2017/4/25.
 */
public class ExchangeRateDAOTest {

    @Test
    public void testGenExchangeRate() {
        ExchangeRateDAO exchangeRateDAO = new ExchangeRateDAO();
        RequestData reqData = new RequestData(new Date(), 3);
        Document doc = exchangeRateDAO.genExchangeRate(reqData);
        System.out.println(doc);
    }



}
