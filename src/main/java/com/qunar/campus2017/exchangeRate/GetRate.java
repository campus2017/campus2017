package com.qunar.campus2017.exchangeRate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chunming.xcm on 2017/1/10.
 */
public class GetRate {
    /**
     * 输出excel文件
     */
    public void get() {
        ExchangeRate exchangeRate = new ExchangeRate();
        String usd = "http://www.kuaiyilicai.com/huilv/d-safe-usd.html";
        String eur = "http://www.kuaiyilicai.com/huilv/d-safe-eur.html";
        String hkd = "http://www.kuaiyilicai.com/huilv/d-safe-hkd.html";
        Map<String, List<ExchangeRateBean>> map = new HashMap<String, List<ExchangeRateBean>>();
        map.put("usd", exchangeRate.exchange(usd));
        map.put("eur", exchangeRate.exchange(eur));
        map.put("hkd", exchangeRate.exchange(hkd));
        Write2Excel.write(map, "src/main/resources/人民币汇率中间价.xls");
    }
}
