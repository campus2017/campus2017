package getData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
 * function:extract data from designated web
 * editor:Wang Yishu
 * Created by Administrator on 2016/12/8.
 */
public class ExtractService {
    public  Map<String, Map<String, String>> extract(Rule rule,List<String> currency) {

        // 进行对rule的必要校验
        validateRule(rule);

        Map<String, Map<String, String>> res = new TreeMap<String, Map<String, String>>();
            /**
             * 解析rule
             */
            String url = rule.getUrl();

            //从url解析数据并返回
            Document document = null;
            try {
                document = Jsoup.connect(url).userAgent("Mozilla/5.0").post();
            } catch (IOException e) {
                e.printStackTrace();
            }

        //处理返回数据
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
            if (currency.contains(titles[i])) {
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
            if (count >= currency.size())
                break;
        }
        return res;
    }

    /**
     * 对传入的参数进行必要的校验
     */
    private static void validateRule(Rule rule)
    {
        String url = rule.getUrl();
        if (TextUtil.isEmpty(url))
        {
            throw new MyException("url不能为空！");
        }
        if (!url.startsWith("http://"))
        {
            throw new MyException("url的格式不正确！");
        }
    }
}
