package wz.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Crawler
 *
 * @author wz
 * @date 2016年11月11日 11:25 AM
 */
public class Crawler {

    @Test
    public void connect() throws IOException {
        Document document = Jsoup.connect("http://www.chinamoney.com.cn/fe-c/historyParity.do")
                .userAgent("Mozilla/5.0")
                .ignoreContentType(true)
                .post();
        Element element = document.body();

//        URL url = new URL("http://www.chinamoney.com.cn/fe-c/historyParityExport.do");
//        InputStream in = url.openStream();
//        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("./data.xls"));
//        int b;
//        while ((b = in.read()) != -1) {
//            out.write(b);
//        }
//        out.flush();
//
//        in.close();
//        out.close();

//        System.out.println(element.toString());
        Elements trs = element.select("tr[align=left]").first().getElementsByTag("tbody").first().getElementsByTag("tr");
//        trs = element.select("[class=]");

        System.out.println("td number:"+trs.size());
        System.out.println(trs.get(0).text());
        System.out.println(trs.get(1).text());
        for (Element e : trs) {
//            String[] columns = e.text().split(" ");
//            for (String s : columns) {
//                System.out.println(s);
//            }
        }
//        for (Element e : trs) {
//            Elements elements = e.getElementsByTag("tr");
//            System.out.println("record number:"+elements.size());
//            for (Element ele : elements) {
//                System.out.println(ele.text());
//            }
//        }





//        System.out.println(document.head().toString());
//        System.out.println(document.body().data());
    }

}
