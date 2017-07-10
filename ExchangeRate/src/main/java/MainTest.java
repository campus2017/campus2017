
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.Doc;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


import java.io.IOException;

// excel opetation
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;


/**
 * Created by qiongweiren.
 */

public class MainTest {

    public static void main(String[] args) throws IOException, ParseException, WriteException {

        String url = "http://www.kuaiyilicai.com/bank/rmbfx/b-safe.html";
        int dayNum = 30;
        String fileName = "ExchangeRate.xls";
        ExchangeWork EW = new ExchangeWork(10, fileName);
        EW.run();


    }
}













/*
        Map<String, String> map = new HashMap<String, String>();
        map.put("querydate", "2017-07-02");
        map.put("xx","adc");
        System.out.println(map.toString());
*/






        /*
        Date d = new Date();
        System.out.println(d);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(d);
        System.out.println("格式化后的日期：" + dateNowStr);
        */







  /*  String[] lists = ex.text().split("走势");


        System.out.println(ex.text());
*/

//for(String list:lists)
//   System.out.println(list.trim()) ;

//        for(ele_tmp)

//String title = doc2.body().toString();
//        System.out.println(title);








//Document doc = Jsoup.connect(url).get();

/*    Elements divs = doc.select("div#content_left div[data-tools]");
        for (Element element : divs) {
                String json = element.attr("data-tools");
                if (StringUtils.isNotBlank(json) && json.trim().startsWith("{")) {
                //获取到json
                JSONObject jsonObj = JSONObject.fromObject(json.trim());
                //获取url
                String durl = jsonObj.getString("url");//内容url
                //do something
                }
                }*/




//    Document doc =Jsoup.connect("http://example.com") .data("query","Java")   .userAgent("Mozilla")   .cookie("auth","token")   .timeout(3000)   .post();
