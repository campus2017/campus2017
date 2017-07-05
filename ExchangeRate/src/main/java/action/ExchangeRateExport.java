package action;


import com.google.common.base.CharMatcher;
import com.google.common.collect.Lists;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.List;

/**
 * Created by dayong.gao on 2016/12/27.
 */
public class ExchangeRateExport {
    public List<List<String>> getdata(List<String> codeList,String startDate,String endDate) throws IOException {
        List<List<String>> rate = Lists.newArrayList();
        List<String> time=Lists.newArrayList();
        boolean hadtimelist=false;
        for (String code :codeList) {
        List<String> tem=Lists.newArrayList();
        Document doc = Jsoup.connect("http://biz.finance.sina.com.cn/forex/forex.php?startdate="+startDate+"&enddate="+endDate+"&money_code="+code+"&type=0")
                .data("query", "Java")   // 请求参数
                .userAgent("I ’ m jsoup") // 设置 User-Agent
                .cookie("auth", "token") // 设置 cookie
                .timeout(3000)           // 设置连接超时时间
                .get();                 // 使用 POST 方法访问 URL

        Elements trs=doc.getElementsByClass("list_table").get(0).select("tr");
        for(int i = 1;i<trs.size();i++){
            Elements tds = trs.get(i).select("td");
            if(hadtimelist==false){
                time.add(tds.get(0).toString().substring(4, tds.get(0).toString().length()-5));
            }
            tem.add(tds.get(4).toString().substring(4, tds.get(4).toString().length()-5));
        }
        if(hadtimelist==false){
            rate.add(time);
            hadtimelist=true;
        }
        rate.add(tem);
        }
        return rate;
    }
}
