package com.qunar.crawer.main;

import com.qunar.crawer.JsoupTest.JsoupTool;
import com.qunar.crawer.common.ContentBean;
import com.qunar.crawer.common.CrawerPage;
import com.qunar.crawer.common.Writer2Excel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhuyin on 16-11-16.
 */
public class GetPrice {
    public static void main(String args[]){
        Map<String,String> urls = new HashMap();
        urls.put("usa","http://www.kuaiyilicai.com/huilv/d-safe-usd.html");
        urls.put("hk","http://www.kuaiyilicai.com/huilv/d-safe-hkd.html");
        urls.put("eur","http://www.kuaiyilicai.com/huilv/d-safe-eur.html");

        Map<String,List<ContentBean>> forWrite = new HashMap();

        for(Map.Entry<String,String> entry:urls.entrySet()){
            String html = CrawerPage.getHtml(entry.getValue());
            List<ContentBean> list = JsoupTool.extract(html);
            forWrite.put(entry.getKey(),list);
        }
        Writer2Excel.write(forWrite,"ExchangeRate/resources/usa.xls");
    }
}
