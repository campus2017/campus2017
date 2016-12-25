import com.qunar.crawer.JsoupTest.JsoupTool;
import com.qunar.crawer.common.ContentBean;
import com.qunar.crawer.common.CrawerPage;
import com.qunar.crawer.common.Writer2Excel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhuyin on 16-11-17.
 */
public class ExcellTest {
    public static void main(String args[]){

        String html = CrawerPage.getHtml("http://www.kuaiyilicai.com/huilv/d-safe-usd.html");
        List<ContentBean> list = JsoupTool.extract(html);

        Map<String,List<ContentBean>> map = new HashMap();
        map.put("usa",list);

        Writer2Excel.write(map,"ExchangeRate/resources/usa.xls");
    }
}
