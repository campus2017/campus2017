package secondProblem;

/**
 *
 * @author gonglei
 * 
 * <noscript> 
  <h1><strong>请开启JavaScript并刷新该页.</strong></h1> 
 </noscript> 
 */

import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ExchangeRate {
    public static void main(String[] args) throws IOException {
        String url1 = "http://www.pbc.gov.cn/zhengcehuobisi/125207/125217/125925/17105/index1.html";
        String url2 = "http://www.pbc.gov.cn/zhengcehuobisi/125207/125217/125925/17105/index2.html";
        Document doc = Jsoup.connect(url1).get();
        //to paser the html
//        System.out.println(doc.head());
//        System.out.println(doc.body());
//        Elements elements = doc.select("table");
//        System.out.println("?");
//        System.out.println(elements.size());
//        String[] urls = null;
//        for (int i = 0; i < urls.length; i++) {
//            Document doc = Jsoup.connect(urls[i]).get();
//            System.out.println(doc.data());
//            System.out.println("OKKK");
//        }
    }
}
