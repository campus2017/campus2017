package top.mineor;

/**
 * Created by Mineor on 2017/6/29.
 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CrawlUtils{


    public static Map<String, Map<String, String>> getCentralParityRate(String url,List<String> currencies) {

        Map<String, Map<String, String>> res = new TreeMap<String, Map<String, String>>();

        Document document = null;
        try {
            document = Jsoup.connect(url).userAgent("Mozilla/5.0").post();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements data = document.body()
                .select("tr[align=left]").first()
                .getElementsByTag("tbody").first()
                .getElementsByTag("tr");

        //标题行
        String[] titles = data.first().text().split(" ");
        String[] record;
        Map<String, String> map;

        //查找符合指定币种的汇率中间价，按日期放入res中
        for (int i = 1, count = 0; i < titles.length; i++) {
            if (currencies.contains(titles[i])) {
                for (int j = 1; j < data.size(); j++) {
                    record = data.get(j).text().split(" ");
                    if ((map = res.get(record[0])) == null) {
                        map = new HashMap<String, String>();
                        res.put(record[0], map);
                    }
                    map.put(titles[i], record[i]);
                }
                count++;
            }
            if (count >= currencies.size())
                break;
        }
        return res;
    }
}