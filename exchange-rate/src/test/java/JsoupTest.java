import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by kefa.zhang on 2017/6/15.
 */
public class JsoupTest {
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("http://www.kuaiyilicai.com/huilv/d-boc-usd.html?datefrom=2017-05-15&dateto=2017-06-15").get();
        Elements trs = doc.select(".table tr");
        for (Element tr : trs) {
            Elements tds = tr.select("td");
            if(tds.size()==6){
                System.out.println(tds.get(5).text());
            }
        }

    }
}
