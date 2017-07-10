package com.qunar.wireless.mlx;

import java.util.Map;
import java.util.TreeMap;


/**
 * Created by mlx on 2016-12-16.
 */
public class Main {
    String site = "http://www.kuaiyilicai.com";  // 站点基址
    String enter = "http://www.kuaiyilicai.com/bank/rmbfx/b-safe.html";  // 入口地址

    String[] needCurs;

    {
        needCurs = new String[]{"USD", "EUR", "HKD"};
//        needCurs = new String[]{"USD"};
    }

    public Map<String,Map<String,String>> getRates(){
        Map<String,Map<String,String>> result = new TreeMap<>();

        ICrawler crawler = new CrawlerJdb();
        String html = crawler.getHtml(enter);

        IHtmlParser parser = new HtmlParserJsoup(site);
        Map<String,String> dataUrl = parser.getDataUrl(html);

        for(String d : needCurs){
            String url = dataUrl.get(d);
            html = crawler.getHtml(url);
            Map<String,String> rate = parser.getRate(html);
            result.put(d,rate);
        }

        crawler.close();
        return result;
    }


    public void getAndWriteRates(String filename){
        Map<String,Map<String,String>> rates = getRates();
        IWriteToExcel writeToExcel = new WriteToExcelPoi();
        writeToExcel.writeACurrency(filename,rates);
    }

    public static void main(String[] args) {
        if(args.length>1){
            System.out.println("参数太多了!");
        }else if(args.length<1){
            System.out.println("请提供导出文件名(filename.xlsx)!");
        }else{
            new Main().getAndWriteRates(args[0]);
            System.out.println("完成,请查看文件:" + args[0]);
        }
    }
}