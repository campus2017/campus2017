package com.exchangeRate.action;

import com.exchangeRate.bean.ExchangeRateBean;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.spec.ECField;
import java.util.*;

/**
 * Created by canda on 6/29/17.
 */
public class ExchangeRate {
    public static final String[] CURRENCY_URL = {"http://www.kuaiyilicai.com/huilv/d-safe-usd.html",
            "http://www.kuaiyilicai.com/huilv/d-safe-eur.html", 
            "http://www.kuaiyilicai.com/huilv/d-safe-hkd.html"}; //汇率爬取链接
    public static final String[] CURRENCY_TYPE = {"USD", "EUR", "HKD"}; //汇率货币种类
   

    public static void main(String[] args) {
        ExchangeRate exchangeRate = new ExchangeRate();
        //按货币种类存储汇率
        DateTime today = DateTime.now();
        DateTime thirtyDaysAgo = today.minusDays(30);
        Map<String,List<ExchangeRateBean>> result= exchangeRate.parseExchangeRateFromUrl(thirtyDaysAgo,today);
        String dir = "./output";
        File file = new File(dir);
        if (!file.exists()){
            file.mkdirs();
        }
        HSSFWorkbook excel = new HSSFWorkbook();
        HSSFSheet sheet = excel.createSheet();
        HSSFRow head = sheet.createRow(0); //创建excel文件标题
        head.createCell(0).setCellValue("日期");
        head.createCell(1).setCellValue("人民币兑美元汇率");
        head.createCell(2).setCellValue("人民币兑欧元汇率");
        head.createCell(3).setCellValue("人民币兑港币汇率");
        for (int i = 0; i < result.get(CURRENCY_TYPE[0]).size() ; i++) {
            HSSFRow row = sheet.createRow(i+1);
            row.createCell(0).setCellValue(result.get(CURRENCY_TYPE[0]).get(i).getDate());//写日期
            row.createCell(1).setCellValue(result.get(CURRENCY_TYPE[0]).get(i).getRate());//写美元汇率
            row.createCell(2).setCellValue(result.get(CURRENCY_TYPE[1]).get(i).getRate());//写欧元汇率
            row.createCell(3).setCellValue(result.get(CURRENCY_TYPE[2]).get(i).getRate());//写港币汇率
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(dir + "/ExchangeRate.xls");
            excel.write(fileOutputStream);
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }


        /*//打印汇率
        for (Map.Entry<String, List<ExchangeRateBean>> stringListEntry : result.entrySet()) {
            System.out.println(stringListEntry.getKey());
            for (int i = 0; i < stringListEntry.getValue().size(); i++) {
                ExchangeRateBean bean = stringListEntry.getValue().get(i);
                System.out.println(bean.getCurrency()+"-"+bean.getDate()+"-"+bean.getRate());

            }
        }*/
    }

    /**
     *@Description:
     *@Params:[timeFrom起始时间, timeTo截止时间]
     *@Return:java.util.Map<java.lang.String,java.util.List<com.exchangeRate.bean.ExchangeRateBean>> String货币种类，List某货币以日期为单位的汇率集合
     *@Date:3:24 AM 6/30/17
     */
    public Map<String, List<ExchangeRateBean>> parseExchangeRateFromUrl(DateTime timeFrom, DateTime timeTo) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        Map<String,List<ExchangeRateBean>> result = new HashMap<String, List<ExchangeRateBean>>();
        for (int i = 0; i < CURRENCY_TYPE.length; i++) {
            Document doc = null;
            try {
                doc = Jsoup.connect(CURRENCY_URL[i])
                        .data("datefrom", timeFrom.toString(dateTimeFormatter))
                        .data("dateto",timeTo.toString(dateTimeFormatter))
                        .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36")
                        .timeout(5000)
                        .post();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Element table = doc.getElementsByTag("table").last();//table标签
            Elements trs = table.getElementsByTag("tr");//table标签下的tr标签
            List<ExchangeRateBean> rateList = new ArrayList<ExchangeRateBean>();
            for(int j = 1; j < trs.size(); j++ ) {
                Elements tds = trs.get(j).getElementsByTag("td");
                rateList.add(new ExchangeRateBean(CURRENCY_TYPE[i],tds.get(0).text(),Double.parseDouble(tds.get(1).text())));
            }

            result.put(CURRENCY_TYPE[i],rateList);
            
        }
        return result;
    }
}
