package wz.core;

import wz.base.impl.ChinaMoneyProvider;
import wz.util.ConfigUtils;
import wz.util.ExcelUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * ExchangeRate
 *
 * @author wz
 * @date 16/11/12 17:41
 */
public class ExchangeRate {

    public static void main(String[] args) {

        ChinaMoneyProvider provider = new ChinaMoneyProvider();
        List<String> currencies = Arrays.asList(ConfigUtils.getProperty("currencies").split(","));
        Map<String, Map<String, String>> records = provider.getCentralParityRate(currencies);
        List<String> titles = new ArrayList<String>(currencies);
        titles.add(0, "日期");
        ExcelUtils.export2Excel(titles, records, "人民币汇率中间价");
    }

}
