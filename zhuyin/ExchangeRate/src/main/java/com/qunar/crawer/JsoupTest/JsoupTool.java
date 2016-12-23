package com.qunar.crawer.JsoupTest;

import com.qunar.crawer.common.ContentBean;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuyin on 16-11-16.

 */
public class JsoupTool {
    public static List<ContentBean> extract(String html){

        List<ContentBean> list = new ArrayList();

        Document document = Jsoup.parse(html);
        Elements es =document.select(".table-responsive tr");

        if(es.size()>0){
            for(int i=1;i<es.size();i++){
                Document doc = Jsoup.parse(es.get(i).toString());
                String[] res = doc.body().text().toString().split(" ");
                String date = res[0];
                float price = new Float(res[1]);
                ContentBean bean = new ContentBean(date, price);
                list.add(bean);
            }
        }
        return list;
    }
}
