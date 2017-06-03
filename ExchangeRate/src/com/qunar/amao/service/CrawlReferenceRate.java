package com.qunar.amao.service;

import com.qunar.amao.pojo.ReferenceRate;
import com.qunar.amao.pojo.UrlQueue;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.select.Elements;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by FGT on 2017/5/10.
 */
public class CrawlReferenceRate {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  //日期格式转换
    String urlPBC="http://www.pbc.gov.cn";  //中国人民银行官网
    int day = 30;   //查询从今天起前day天的数据
    /**
     * 爬去一级页面信息，main函数调用接口，可以配置查询日期
     * @param url 一级页面链接地址
     * @return 爬取的数据
     */
    public ArrayList<ReferenceRate> CrawlReferenceData(String url) throws Exception{
        ArrayList<ReferenceRate> list = new ArrayList<ReferenceRate>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - day);
        Date start = calendar.getTime();
        String endString = sdf.format(start);
        Date end = sdf.parse(endString);
        start = new Date();
        //list = CrawlReferenceData(url,list,start,end);
        UrlQueue queue = new UrlQueue();
        queue.enQueue(url); //第一页进入队列
        queue = GetUrlQueue(url,queue);

        while(!queue.isQueueEmpty()){
            String urlTemp = queue.deQueue().toString();
            list = CrawlReferenceData(urlTemp,list,start,end);
        }
        return list;
    }

    /**
     * 爬取二级页面汇率中间价的信息
     * @param url 二级页面链接地址
     * @param list 爬取信息对象
     * @return 爬去信息对象
     */
    public ArrayList<ReferenceRate> CrawlReferenceData(String url,ArrayList<ReferenceRate> list) throws Exception{
        String html = downloadHtml(url);
        Document doc = Jsoup.parse(html);

        Element time = doc.getElementById("shijian");
        String timeString = time.text().substring(0,10);
        Date date = sdf.parse(timeString);

        Element pNode = doc.getElementsByTag("p").first();
        String pString = pNode.text();
        int start = pString.lastIndexOf("1美元对人民币");
        String usDollarString = pString.substring(start+7,start+13);
        Float usDollar = Float.parseFloat(usDollarString);

        start = pString.lastIndexOf("1欧元对人民币");
        String euroString = pString.substring(start+7,start+13);
        Float euro = Float.parseFloat(euroString);

        start = pString.lastIndexOf("1港元对人民币");
        String hkDollarString = pString.substring(start+7,start+14);
        Float hkDollar = Float.parseFloat(hkDollarString);

        ReferenceRate rr = new ReferenceRate(date,usDollar,euro,hkDollar);
        list.add(rr);

        return list;
    }

    /**
     * 带起止日期的一级页面爬取方法
     * @param url 一级页面地址
     * @param list 爬取信息对象
     * @param start 起始日期
     * @param end 截止日期
     * @return 爬取信息对象
     */
    public ArrayList<ReferenceRate> CrawlReferenceData(String url,ArrayList<ReferenceRate> list,Date start,Date end) throws Exception{
        String html = downloadHtml(url);
        Document doc = Jsoup.parse(html);                   // 解析获取Document对象

        Element divNode1 = doc.getElementById("17105");    //根据id获取节点对象
        Element divNode2 = divNode1.getElementsByTag("div").get(3); //获取第div1节点下的第二个div
        Element trNode = divNode2.getElementsByTag("tr").get(1);    //获取结点下的tr
        Elements tables = trNode.getElementsByTag("table");

        for(Element e : tables){
            Element aNode = e.getElementsByTag("a").first();
            String href = aNode.attr("href");
            String dateString = e.getElementsByClass("hui12").first().text();
            Date date = sdf.parse(dateString);
            if(date.getTime() >= end.getTime() && date.getTime() <= start.getTime()){
                //进入二级页面爬取相关信息
                list = CrawlReferenceData(urlPBC + href,list);
            }
            if(date.getTime() < end.getTime()){
                //当超过日期限制条件时，爬取终止
                return list;
            }
        }
        return list;
    }

    public UrlQueue GetUrlQueue(String url,UrlQueue queue) throws Exception{
        String html = downloadHtml(url);
        Document doc = Jsoup.parse(html);                   // 解析获取Document对象
        Element footA = doc.getElementsByClass("pagingNormal").first();

        Elements nextPage =doc.getElementsContainingOwnText("下一页");

        String href = nextPage.attr("tagname");

        if(nextPage.attr("onclick") != "") {
            queue.enQueue(urlPBC + href);
            queue = GetUrlQueue(urlPBC + href,queue);
        }

        return queue;
    }

    /**
     * 将html网页转换成字符串
     * @param url 网页地址
     * @return 网页源代码字符串
     * @throws Exception
     */
    public String downloadHtml(String url) throws Exception{
        String htmlString="";
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setCssEnabled(false);
        HtmlPage html = webClient.getPage(url);
        htmlString = html.asXml();

        webClient.close();

        return htmlString;
    }
}
