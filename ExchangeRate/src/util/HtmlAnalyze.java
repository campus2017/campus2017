package util;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/21.
 */
public class HtmlAnalyze {
    public static final int INDEX_1 = 0;
    public static final int INDEX_2 = 1;
    private static WebClient client = new WebClient(BrowserVersion.CHROME);
    private static HtmlPage page;

    public static ArrayList<String> getItems(String url,int code) throws IOException {
        ArrayList<String> alist = new ArrayList<>();
        int times = (code == INDEX_1 ? 20 : 10);

        page = client.getPage(url);
        InputStream inputStream = page.getWebResponse().getContentAsStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
        String s = "";
        while ((s = br.readLine()) != null){
            if (s.contains("中国外汇交易中心受权公布人民币汇率中间价公告")){
                alist.add(s);
            }
            if (alist.size() == times)
                break;
        }

        return alist;
    }

    public static String getEveryDayRate(String url) throws IOException {

        page = client.getPage(url);
        InputStream inputStream = page.getWebResponse().getContentAsStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
        String s = "";
        while ((s = br.readLine()) != null){
            if (s.contains("中国人民银行授权中国外汇交易中心公布"))
                break;
        }
        return s;
    }

}
