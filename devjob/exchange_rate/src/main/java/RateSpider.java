import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Created by honglin.li on 2017/6/29.
 */
public class RateSpider {

    private static String excelPath = "D:\\java_work\\exchange_rate\\";
    private static String excelFileName = "exchange_rate.xls";

    private static String pre_url = "http://www.kuaiyilicai.com/";
    private static String[] goal = {
            "huilv/d-safe-hkd.html",
            "huilv/d-safe-usd.html",
            "huilv/d-safe-eur.html",
    };

    private static ArrayList<ArrayList<RateUnit>> res_rate = new ArrayList<ArrayList<RateUnit>>();

    public static ArrayList<ArrayList<RateUnit>> getRateList() {

        int cnt = 1;
        for(String str : goal) {
            String url = pre_url + str;
            System.out.println(url + "  ");
            Connection conn = Jsoup.connect(url);
            Document doc = null;
            try {
//                System.out.println(url);
                doc = conn.get();
            }catch (IOException e) {
                System.out.println(e.getCause());
                e.printStackTrace();
            }
            Elements elementTable = doc.getElementsByClass("table");
            Iterator<Element> eleIte = elementTable.iterator();
            ArrayList<RateUnit> rate_info = new ArrayList<RateUnit>();
            while(eleIte.hasNext()) {
                String tmpstr = eleIte.next().getElementsByTag("td").html();
                String[] tmparr = tmpstr.split("\n");
                int j = 0, len = tmparr.length;
                for (int i = 0; i < len / 3; i++) {
                    String day, rate, type;
                    day = tmparr[j++];
                    rate = tmparr[j++];
                    type = tmparr[j++].substring(3, 5);
                    RateUnit u = new RateUnit(type, Double.valueOf(rate), day);
                    rate_info.add(u);
                }
            }
            res_rate.add(rate_info);
        }

        return res_rate;
    }

}
