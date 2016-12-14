package wz.base.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import wz.base.DataProvider;
import wz.util.ConfigUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 中国货币网 数据源
 *
 * @author wz
 * @date 2016年11月11日 8:57 PM
 */
public class ChinaMoneyProvider implements DataProvider {


    /**
     * 数据源url
     */
    private String url;

    public ChinaMoneyProvider() {
        url = ConfigUtils.getProperty("chinamoney_url");
    }

    public Map<String, Map<String, String>> getCentralParityRate(List<String> currencies) {
        if (url == null)
            return null;
        Map<String, Map<String, String>> res = new TreeMap<String, Map<String, String>>();

        //从url解析数据并返回
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
