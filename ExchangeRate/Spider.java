import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 从今天开始过去30天时间，中国人民银行公布的人民币汇率中间价
 * 2017.06.17
 */
public class Spider {

    private List<String> crawlUrls = new ArrayList<>();        // 存放某一天的具体公告的链接地址

    public static String BANK_PREFIX = "http://www.pbc.gov.cn";     // 主页地址,因为在网页中的某些url是相对地址,需要加上这个前缀
    public static String BANK_URL_FIRST = "http://www.pbc.gov.cn/zhengcehuobisi/" +
            "125207/125217/125925/index.html";   // 页面内容见图1
    public static String BANK_URL_SECOND = "http://www.pbc.gov.cn/zhengcehuobisi/125207/125217/125925/17105/index2.html";

    /*
     *  http://www.pbc.gov.cn
     *  这个网站第一次爬取的时候会返回一个javascript代码,这段代码会设置浏览器端的cookie,要把这个cookie添加
     *  到原有的cookie中再访问网站才能正常访问到网站.这里直接从浏览器中获取了最终的cookie
     */
    public static Map<String, String> COOKIES = new HashMap<>();

    static {
        COOKIES.put("ccpassport", "619b18e61b7a8486954efff00b817477");
        COOKIES.put("wzwsconfirm", "591fd90f1ad1c449241c450a53ac3833");
        COOKIES.put("wzwsvtime", "1497617171");
        COOKIES.put("wzwstemplate", "Mw==");
        COOKIES.put("wzwschallenge", "V1pXU19DT05GSVJNX1BSRUZJWF9MQUJFTDMxMjQ1Mzc=");
        COOKIES.put("_gscu_1042262807", "94595334l8tp7p92");
        COOKIES.put("_gscbrs_1042262807", "1");
    }

    /**
     * 这是个工具方法输入url,返回 Document 对象,因为在ExchangeRate类中也会调用该方法,所以提取出来
     * @param url 需要爬取的网页地址
     * @return org.jsoup.nodes.Document对象
     */
    public static Document jsoupCrawl(String url){
        Document document = null;
        try {
            // 注意对cookie, userAgent字段的设置
            document = Jsoup.connect(url)
                .cookies(Spider.COOKIES)
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    /**
     * 爬取人民币汇率中间价公告列表页面,将具体某一天的公告页面地址保存下来,见图1
     * 之所以要返回相隔天数,是因为不一定在一页中就包含了最近30天的汇率,有可能要到第二页去继续添加url
     * @param url 爬取的链接地址,这里为"人民币汇率中间价公告列表"页面, 在本程序中为BANK_URL_FIRST 和 BANK_URL_SECOND
     * @return 相隔天数
     */
    private int crawlData(String url) {
        int discrepantDays = 0;
        Calendar today = Calendar.getInstance();
        String patternString = "[^0-9]+";       // 取出2017年5月17号等日期,与当前日期计算相隔天数
        Pattern pattern = Pattern.compile(patternString);

        Document document = Spider.jsoupCrawl(url);
        Elements elements = document.getElementsByClass("newslist_style");
        String title;

        /* 取出公告列表页面中每一项的日期,计算其与今天的天数差,取相隔小于30的日期对应的
           具体详情页面的url,因为公告中的天数不一定是连续的 */
        for (Element link : elements) {
            title = link.child(0).attr("title");
            String[] tmp_date = pattern.split(title);
            Calendar tmp_day = Calendar.getInstance();
            tmp_day.set(Integer.parseInt(tmp_date[0]), Integer.parseInt(tmp_date[1]) - 1, Integer.parseInt(tmp_date[2]));
            discrepantDays = (int) ((today.getTime().getTime() - tmp_day.getTime().getTime()) / 1000 / 60 / 60 / 24);
            if (discrepantDays <= 30) {
                this.crawlUrls.add(link.child(0).attr("href"));
            } else {
                break;
            }
        }
        return discrepantDays;
    }

    /**
     * 爬取最近30天的具体汇率页面的地址,url列表存放在crawlUrls字段中
     */
    private void crawl() {
        int discrepantDays = this.crawlData(Spider.BANK_URL_FIRST);
        if (discrepantDays <= 30) {
            this.crawlData(Spider.BANK_URL_SECOND);
        }
    }

    /**
     * 供外部类调用
     * @return 返回具体某天汇率详情页面的地址url列表
     */
    public List<String> getCrawlUrls() {
        this.crawl();
        return this.crawlUrls;
    }

    public static void main(String[] args) {
        Spider spider = new Spider();
        spider.crawl();
    }
}
