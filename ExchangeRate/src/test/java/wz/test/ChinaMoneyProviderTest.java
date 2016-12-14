package wz.test;

import org.junit.Test;
import wz.base.impl.ChinaMoneyProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * ChinaMoneyProviderTest
 *
 * @author wz
 * @date 16/11/12 17:09
 */
public class ChinaMoneyProviderTest {

    @Test
    public void getCentralParityRate() throws Exception {
        ChinaMoneyProvider provider = new ChinaMoneyProvider();
        List<String> currencies = new ArrayList<String>(3);
        currencies.add("USD/CNY");
        currencies.add("EUR/CNY");
        currencies.add("HKD/CNY");
        Map res = provider.getCentralParityRate(currencies);
        System.out.println(res.size());
    }

}