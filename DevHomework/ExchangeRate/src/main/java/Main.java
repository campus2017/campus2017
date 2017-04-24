/**
 * Created by muhongfen on 17/4/24.
 * 分析从过去30天时间里，中国人民银行公布的人民币汇率中间价
 * 得到人民币对美元、欧元、港币的汇率，形成excel文件输出
 * 数据源：http://www.kuaiyilicai.com/huilv/d-safe-usd.html
 *
 */
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        Map<String,String> urlMap = new TreeMap<String,String>();
        //获取今天日期及前30天日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        String today =dateformat.format(calendar.getTime());
        calendar.add(calendar.DATE,-30);
        String dataFrom = dateformat.format(calendar.getTime());
        urlMap.put("usa","http://www.kuaiyilicai.com/huilv/d-safe-usd.html");
        urlMap.put("hk","http://www.kuaiyilicai.com/huilv/d-safe-hkd.html");
        urlMap.put("eur","http://www.kuaiyilicai.com/huilv/d-safe-eur.html");
        Map<String,Map<String,Float>> result = new TreeMap<String, Map<String, Float>>();
        for(Map.Entry<String,String> entry:urlMap.entrySet()){
            try {
                //发送post请求，获取页面
                Connection con= Jsoup.connect(entry.getValue());
                con.data("datefrom",dataFrom);
                con.data("dateto",today);
                Document doc = con.post();
                //解析html页面
                Map<String,Float> rate = Utils.getRateFromDoc(doc);
                result.put(entry.getKey(),rate);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //写入excel
        Utils.writeToExcel(result,"ExchangeRate/files/output.xls");
    }



}
