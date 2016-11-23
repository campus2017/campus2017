import com.qunar.crawer.JsoupTest.JsoupTool;
import com.qunar.crawer.common.ContentBean;
import com.qunar.crawer.common.CrawerPage;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhuyin on 16-11-16.
 */
public class JsoupTest {
    public static void main(String args[]){

        Map<String,String> urls = new HashMap();
        urls.put("usa","http://www.kuaiyilicai.com/huilv/d-safe-usd.html");
        urls.put("hk","http://www.kuaiyilicai.com/huilv/d-safe-hkd.html");
        urls.put("eu","http://www.kuaiyilicai.com/huilv/d-safe-eur.html");

        for(Map.Entry<String,String> entry:urls.entrySet()){
            String html = CrawerPage.getHtml(entry.getValue());
            List<ContentBean> list = JsoupTool.extract(html);
            System.out.println(entry.getKey());
            System.out.println("====================");
            for(ContentBean bean:list){
                System.out.println("date: "+bean.getDate()+" price: "+bean.getPrice());
            }
        }
    }
}
