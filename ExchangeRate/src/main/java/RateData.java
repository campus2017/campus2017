import com.google.common.collect.ArrayListMultimap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

/**
 * Created by asus on 2016/12/30.
 */
public class RateData {
    //    获取网上中间价数据
    public  ArrayListMultimap getdata() throws IOException {
//        将数据存在ArrayListMultimap
        ArrayListMultimap<String,String> data =  ArrayListMultimap.create();
//        取网上数据
        URL url = new URL("http://www.chinamoney.com.cn/fe-c/historyParity.do");
        Document doc = Jsoup.parse(url.openStream(), "UTF-8", url.toString());
        Elements element = doc.select("table").get(2).select("tr");

        for (Element e : element) {
            String date = e.select("td").get(0).text();
            String usd = e.select("td").get(1).text();
            String eur = e.select("td").get(2).text();
            String hkd = e.select("td").get(4).text();
            data.put("dates",date);
            data.put("usds",usd);
            data.put("eurs",eur);
            data.put("hkds",hkd);
        }
        return data;
    }
}
