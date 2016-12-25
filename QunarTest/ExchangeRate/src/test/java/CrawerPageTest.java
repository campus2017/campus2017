import com.qunar.crawer.common.CrawerPage;

/**
 * Created by zhuyin on 16-11-16.
 */
public class CrawerPageTest {
    public static void main(String args[]){
        String html = CrawerPage.getHtml("http://www.kuaiyilicai.com/huilv/d-safe-usd.html");
        System.out.println(html);
    }
}
