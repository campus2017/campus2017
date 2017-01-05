/**
 * Created by woo on 1/5.
 */
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

public class ExchangeRateProcessor implements PageProcessor {
    private Site site = Site
            .me()
            .setSleepTime(3000)
            .setUserAgent(
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");


    @Override
    public void process(Page page) {
        if (page.getUrl().regex("http://www.kuaiyilicai.com/huilv/d-safe-usd.html").match()){
            page.putField("date", page.getHtml().xpath("div[@class='table-responsive']/table/tbody/tr/td[@align='center']/text()").all());
            page.putField("rateUsd", page.getHtml().xpath("div[@class='table-responsive']/table/tbody/tr/td[@align='left   ']/text()").all());
            page.addTargetRequest("http://www.kuaiyilicai.com/huilv/d-safe-eur.html");
            page.addTargetRequest("http://www.kuaiyilicai.com/huilv/d-safe-hkd.html");
        }
        else if(page.getUrl().regex("http://www.kuaiyilicai.com/huilv/d-safe-eur.html").match()){
            page.putField("rateEur", page.getHtml().xpath("div[@class='table-responsive']/table/tbody/tr/td[@align='left   ']/text()").all());
        }
        else if(page.getUrl().regex("http://www.kuaiyilicai.com/huilv/d-safe-hkd.html").match()){
            page.putField("rateHkd", page.getHtml().xpath("div[@class='table-responsive']/table/tbody/tr/td[@align='left   ']/text()").all());
        }
        //page.putField("currentUrl",page.getUrl());
        //page.putField("content", page.getHtml().xpath("//div[@id='articlebody']//div[@class='articalContent']"));
        //page.putField("date",page.getHtml().xpath("//div[@id='articlebody']//span[@class='time SG_txtc']").regex("\\((.*)\\)"));
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new ExchangeRateProcessor())
                .addUrl("http://www.kuaiyilicai.com/huilv/d-safe-usd.html")
                .addPipeline(new ConsolePipeline())
                .addPipeline(new ExcelPipeline())
                .run();
//        Spider.create(new ExchangeRateProcessor())
//                .addUrl("http://www.kuaiyilicai.com/huilv/d-safe-eur.html")
//                .addPipeline(new ConsolePipeline())
//                .addPipeline(new ExcelPipeline())
//                .run();
//        Spider.create(new ExchangeRateProcessor())
//                .addUrl("http://www.kuaiyilicai.com/huilv/d-safe-hkd.html")
//                .addPipeline(new ConsolePipeline())
//                .addPipeline(new ExcelPipeline())
//                .run();
    }
}