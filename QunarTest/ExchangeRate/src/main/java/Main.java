import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/10.
 */
public class Main {
    public static void main(String[] args) throws IOException{
        Map<String, String> urls = new HashMap();
        urls.put("usa", "http://www.kuaiyilicai.com/huilv/d-safe-usd.html");
        urls.put("hk", "http://www.kuaiyilicai.com/huilv/d-safe-hkd.html");
        urls.put("eur", "http://www.kuaiyilicai.com/huilv/d-safe-eur.html");

        Map<String, List<Bean>> forWrite = new HashMap();

        for (Map.Entry<String, String> entry : urls.entrySet()) {
            String html = InternetWorm.downloadPageHtml(entry.getValue());
            List<Bean> list = JsoupTool.extract(html);
            forWrite.put(entry.getKey(), list);
        }
        Writer2Excel.write(forWrite, "D:\\JAVA\\Homework\\ExchangeRate\\ua.xls");
        System.out.println("完成");
    }
}
