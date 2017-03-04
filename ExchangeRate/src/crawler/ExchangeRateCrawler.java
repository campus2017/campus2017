package crawler;
import java.io.IOException;
import java.util.*;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utils.Utils;


/**
 * Created by Administrator on 2017/2/14.
 */
public class ExchangeRateCrawler implements Crawler{

    private Elements m_data = null;

    @Override
    public void Extract(Object data){
        try {
            if(!(data instanceof ExchangeRateCrawlerRule)){
                throw new IOException("请输入正确的爬虫规则类型！");
            }
            ExchangeRateCrawlerRule rule = (ExchangeRateCrawlerRule)data;
            String url = rule.GetUrl();
            String[] params = rule.GetParams();
            String[] values = rule.GetValues();
            String resultTagName = rule.GetTagName();
            ExchangeRateCrawlerRule.HtmlElement tagType = rule.GetTagType();
            ExchangeRateCrawlerRule.HttpMethod requestType = rule.GetRequestMethod();

            Connection conn = Jsoup.connect(url);
            conn.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            conn.header("Accept-Encoding", "gzip, deflate");
            conn.header("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
            conn.header("Connection", "keep-alive");
            conn.header("Content-Type", "application/x-www-form-urlencoded");
            conn.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");

            conn.postDataCharset("utf-8");

            // set queryjson
            if (params != null)
            {
                for (int i = 0; i < params.length; i++)
                {
                    conn.data(params[i], values[i]);
                }
            }

            // 设置请求类型
            Document doc = null;
            switch (requestType)
            {
                case GET:
                    doc = conn.timeout(100000).get();
                    break;
                case POST:
                    doc = conn.timeout(100000).post();
                    break;
            }

            //处理返回数据
            m_data = new Elements();
            switch (tagType)
            {
                case CLASS:
                    m_data = doc.getElementsByClass(resultTagName);
                    break;
                case ID:
                    Element result = doc.getElementById(resultTagName);
                    m_data.add(result);
                    break;
                case SELECTION:
                    m_data = doc.select(resultTagName);
                    break;
                default:
                    if (Utils.IsEmpty(resultTagName))
                    {
                        m_data = doc.getElementsByTag("body");
                    }
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void Export(String path){
        if(m_data == null){
            return;
        }

        ArrayList<ExchangeRateCrawlerData> dataList = new ArrayList<ExchangeRateCrawlerData>();
        ExchangeRateCrawlerData data = null;

        for (Element result : m_data)
        {
            Elements trs = result.getElementsByTag("tr");

            for (Element tr : trs)
            {
                Elements tds = tr.getElementsByTag("td");
                int i = 0;
                data = new ExchangeRateCrawlerData();
                for (Element td : tds) {
                    String text = Utils.GetNum(td.text());

                    switch (i){
                        case 0:
                            data.SetDate(td.text());
                            break;
                        case 1:
                            data.SetRMBToDollarRate(Double.valueOf(text));
                            break;
                        case 2:
                            data.SetRMBToEuroRate(Double.valueOf(text));
                            break;
                        case 4:
                            data.SetRMBToHKDollarRate(Double.valueOf(text));
                            break;
                        default:
                            break;
                    }

                    if(++i > 4){
                        break;
                    }
                }

                if(data.GetDate() != null && data.GetDate().length()>0){
                    dataList.add(data);
                }
            }
        }

        Utils.ExportExcel(path,dataList);
    }
}
