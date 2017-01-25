package com.qunar.main_test;

import com.qunar.com.qunar.JsoupTool.JsoupTool;
import com.qunar.ex_rate.commom.ContentBean;
import com.qunar.ex_rate.commom.Writer2Excel;
import com.qunar.ex_rate.commom.getHtmlPage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gcy0904 on 2017/1/20.
 */
public class main_test {
    public static void main(String args[]){
        Map<String,String> urls = new HashMap();
        urls.put("usa","http://www.kuaiyilicai.com/huilv/d-safe-usd.html");
        urls.put("hk","http://www.kuaiyilicai.com/huilv/d-safe-hkd.html");
        urls.put("eur","http://www.kuaiyilicai.com/huilv/d-safe-eur.html");

        Map<String, List<ContentBean>> forWrite = new HashMap<String, List<ContentBean>>();
        for(Map.Entry<String,String> entry:urls.entrySet()){
            String html = getHtmlPage.getHtml(entry.getValue());
            List<ContentBean> list = JsoupTool.HtmlParse(html);
            forWrite.put(entry.getKey(),list);
        }
        //System.out.print(forWrite.toString());
       Writer2Excel.write(forWrite, "ExchangeRate/resources/usa.xls");
    }
}
