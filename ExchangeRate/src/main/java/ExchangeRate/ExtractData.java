package ExchangeRate;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.nio.DoubleBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能：通过对html文本中元素进行解析获取数据
 * Created by yangyening on 2016/12/27.
 */
public class ExtractData {
    public static List<RateBean> getRateData(String html){
        List<RateBean> rateBeanList=new ArrayList<RateBean>();
        Document document= Jsoup.parse(html);
        Elements trs=document.select("table[width=787]").select("tr");
        for(int i=1;i<trs.size();i++){
            Elements tds=trs.get(i).select("td");
            RateBean rateBean=new RateBean();
               rateBean.setDate(tds.get(0).text());
               rateBean.setUsdPrice(Float.parseFloat(tds.get(1).text()));
               rateBean.setEurPrice(Float.parseFloat(tds.get(2).text()));
               rateBean.setHkdPrice(Float.parseFloat(tds.get(4).text()));
               rateBeanList.add(rateBean);
        }
        return  rateBeanList;
    }
}
