package com.qunar;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.script.*;
import java.io.IOException;
import java.util.*;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * 人民币汇率爬虫(基于jsoup)
 * Created by WanlongMa on 2016/12/21.
 */
public class RequestRate extends BaseObject {

    private static final Random random = new Random();
    public static final String BASE_URL = "http://www.pbc.gov.cn";
    private static final double PAGE_SIZE = 20;

    /**
     * 从指定的页面中获取汇率实体信息
     * @param pages
     * @return
     */
    public List<RateBean> getRateBeans(List<String> pages){

        List<RateBean> list = new ArrayList<>();

        for(int i = 0; i<pages.size() && i< Main.DAYS; i++){
            String url = pages.get(i);
            Document document = requestFromPBC(url);
            Element element = document.getElementById("zoom");
            String pubContent = element.getElementsByTag("p").get(0).text();
            RateBean rb = new RateBean();
            rb.setPubDate(pubContent.substring(
                    pubContent.indexOf("中国人民银行授权中国外汇交易中心公布，") + "中国人民银行授权中国外汇交易中心公布，".length(),
                    pubContent.indexOf("银行间外汇市场人民币汇率中间价为")
            ));
            rb.setMiddleRateDollarStr(getNumberFollowWithSubwith(pubContent,"1美元对人民币"));
            rb.setMiddleRateEuroStr(getNumberFollowWithSubwith(pubContent,"1欧元对人民币"));
            rb.setMiddleRateHKStr(getNumberFollowWithSubwith(pubContent,"1港元对人民币"));
            rb.setRateDollar(1/Float.valueOf(rb.getMiddleRateDollarStr()));
            rb.setRateEuro(1/Float.valueOf(rb.getMiddleRateEuroStr()));
            rb.setRateHK(1/Float.valueOf(rb.getMiddleRateHKStr()));
            list.add(rb);
        }
        return list;

    }


    /**
     * 获取指定数量的人民币汇率中间价公告页面url
     * @param days
     * @return
     */
    public List<String> getRatePageUrlList(double days){

        ArrayList<String> list = new ArrayList<>();
        String formatUrl = "http://www.pbc.gov.cn/zhengcehuobisi/125207/125217/125925/17105/index%d.html";
        for(int i=1;i<=Math.ceil(days/PAGE_SIZE);i++){
            Document document = requestFromPBC(String.format(formatUrl, i));
            Elements elements = document.getElementsByTag("a");
            elements.forEach((e)->{
                if(e.toString().contains("中国外汇交易中心受权公布人民币汇率中间价公告")){
                    // System.out.println(e.attr("href"));
                    list.add(BASE_URL + e.attr("href"));
                }
            });
        }
        return list;

    }

    /**
     * 从中国人民银行货币政策司网站抓取一次网页内容
     * 抓取过程：
     * 1、访问原网页，得到js脚本；
     * 2、简单处理并执行1中获取到的脚本，得到返回的两个cookie:wzwsvtime和wzwsconfirm，
     *    并解析得到另外两个cookie：wzwstemplate和wzwschallenge；同时得到动态url：dynamicurl；
     * 3、利用2中得到的url和cookie再次构造请求，返回目标结果页面。
     *
     * @param url
     * @return
     */
    private Document requestFromPBC(String url) {

        Map<String,String> cookies = new HashMap<>();

        Document document = request(url,cookies);
        Elements elements = document.getElementsByTag("script");

        try{

            // 第一个js代码
            String firstJs = elements.get(0).html();
            firstJs = firstJs.trim();
            firstJs = firstJs.substring(5,firstJs.length()-1);
            firstJs = "d=" + firstJs;

            // 执行第一个js代码
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
            String jsRusultFirst = (String)engine.eval(firstJs);

            /* 第二次执行js：运行第一步中得到的动态js代码，得到几个动态cookie值：wzwstemplate，wzwschallenge和dynamicurl*/
            String wzwstemplate, wzwschallenge, dynamicurl;
            engine.eval(jsRusultFirst.substring(0, jsRusultFirst.length() - 16));
            ScriptContext context = engine.getContext();
            Integer template = (Integer) context.getAttribute("template");
            dynamicurl = (String) context.getAttribute("dynamicurl");

            Invocable invocable = (Invocable) engine;
            wzwstemplate = (String) invocable.invokeFunction("KTKY2RBD9NHPBCIHV9ZMEQQDARSLVFDU", template.toString());
            String confirm = (String) invocable.invokeFunction("QWERTASDFGXYSF");
            wzwschallenge = (String) invocable.invokeFunction("KTKY2RBD9NHPBCIHV9ZMEQQDARSLVFDU", confirm);

            // Map<String, String> mCookies = new HashMap<>();
            cookies.put("wzwstemplate", wzwstemplate);
            cookies.put("wzwschallenge", wzwschallenge);

            // 根据获取到的动态cookie和URL请求数据
            String newUrl = BASE_URL + dynamicurl;
            document = request(newUrl,cookies);

        }catch(ScriptException | NoSuchMethodException e){
            e.printStackTrace();
        }

        return document;

    }

    /**
     * 请求一次页面数据
     * @param url
     * @return
     */
    private Document request(String url,Map<String,String> cookies){

        log("请求数据：" + url);
        delay(); // 每次访问加一个随机延时

        Document doc = null;

        Map<String, String> header = new HashMap<>();
        header.put("Host", "http://info.bet007.com");
        header.put("User-Agent", "  Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/5.0");
        header.put("Accept", "  text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        header.put("Accept-Language", "zh-cn,zh;q=0.5");
        header.put("Accept-Charset", "  GB2312,utf-8;q=0.7,*;q=0.7");
        header.put("Connection", "keep-alive");
        Connection connect = Jsoup.connect(url).timeout(1000 * 100).data(header);

        try{
            if(cookies != null) connect.cookies(cookies);
            Connection.Response response = connect.execute();
            cookies.putAll(response.cookies());//注意：第一次请求有返回cookie，需要保存起来，与第二次请求得到的cookie字段同时发送才能访问原来的页面
            connect.cookies(response.cookies());
            doc = response.parse();
        }catch(IOException e){
            e.printStackTrace();
        }

        return doc;

    }

    /**
     * 随机延时
     */
    private void delay() {
        int delay = 100 + random.nextInt(500);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 得到string中紧跟在subString后面的第一个浮点数字
     * @param subString
     * @return
     */
    private String getNumberFollowWithSubwith(String string, String subString){

        StringBuffer buffer = new StringBuffer();
        int index = 0;
        int start = string.indexOf(subString) + subString.length();
        index = start;
        while((string.charAt(index)>=48 && (string.charAt(index)<= 57) || (string.charAt(index)==46))){
            buffer.append(string.charAt(index));
            index++;
        }
        return buffer.toString();

    }

}
