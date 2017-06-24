/**
 * Created by Administrator on 2017/6/7.
 */
package com.qunar.com.exchange;
import java.io.*;
import java.net.*;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class ExchangeRate{
    public static void main(String[] args) {
        ExchangeRate1 ex=new ExchangeRate1("http://www.kuaiyilicai.com/huilv/d-safe-usd.html");
        TreeMap<String,String> map1=ex.getRate();
        ExchangeRate1 hkd=new ExchangeRate1("http://www.kuaiyilicai.com/huilv/d-safe-hkd.html");
        TreeMap<String,String> map2=hkd.getRate();
        ExchangeRate1 eur=new ExchangeRate1("http://www.kuaiyilicai.com/huilv/d-safe-eur.html");
        TreeMap<String,String> map3=eur.getRate();
        //写入excel
        WritableWorkbook writable = null;
        File file = new File("./exchangeExcel.xls");
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            writable = Workbook.createWorkbook(file);
            WritableSheet sheet = writable.createSheet("Test sheet 1",0);
            Label date = new Label(0,0,"DATE");
            sheet.addCell(date);
            Label usd1 = new Label(1,0,"USD");
            sheet.addCell(usd1);
            Label hkd1 = new Label(2,0,"HKD");
            sheet.addCell(hkd1);
            Label eur1 = new Label(3,0,"EUR");
            sheet.addCell(eur1);
            int i=1;
            Set<Map.Entry<String, String>> entrySet1=map1.entrySet();
            Iterator<Map.Entry<String,String>> it=entrySet1.iterator();
            while(it.hasNext()) {
                Map.Entry<String,String> mapentry=it.next();
                String date1=mapentry.getKey();
                String pric=mapentry.getValue();
               Label con = new Label(0,i,date1);
                Label con2 = new Label(1,i,pric);
                sheet.addCell(con);
                sheet.addCell(con2);
                i=i+1;
            }
            int j=1;
            Set<Map.Entry<String, String>> entrySet2=map2.entrySet();
            Iterator<Map.Entry<String,String>> it2=entrySet2.iterator();
            while(it2.hasNext()) {
                Map.Entry<String,String> mapentry2=it2.next();
                String price2=mapentry2.getValue();
                Label con3 = new Label(2,j,price2);
                sheet.addCell(con3);
                j=j+1;
            }
            int k=1;
            Set<Map.Entry<String, String>> entrySet3=map3.entrySet();
            Iterator<Map.Entry<String,String>> it3=entrySet3.iterator();
            while(it3.hasNext()) {
                Map.Entry<String,String> mapentry3=it3.next();
                String price3=mapentry3.getValue();
                Label con4 = new Label(3,k,price3);
                sheet.addCell(con4);
                k=k+1;
            }
            writable.write();
            writable.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
 class ExchangeRate1 {
    String url=null;
   public  ExchangeRate1(String url){
        this.url=url;
    }
    public  Document getDataByJsoup(String url) {
       //通过Jsoup解析页面
        Document doc2 = null;
        try {
            doc2 = Jsoup.connect(url).timeout(50000).get();
           // String title = doc2.body().toString();
        } catch (SocketTimeoutException e) {
            System.out.println("Socket连接超时");
        } catch (IOException e){
            e.printStackTrace();
        }
        return doc2;
    }
    public TreeMap<String,String> getRate() {
       //将汇率统计到Map中
       TreeMap<String,String> map = new TreeMap<>();
        Document doc1 = this.getDataByJsoup(url);
        Elements elements=doc1.select(".table-responsive tr");
        if(elements.size()>0){
            for (int i = 1; i < elements.size(); i++) {
                Document ele_document = Jsoup.parse(elements.get(i).toString());
                String body_text = ele_document.body().text().toString();
                String[] split_string = body_text.split(" ");
                String date = split_string[0];
                String price = new String(split_string[1]);
                map.put(date,price);
            }
        }
        return map;
    }
}
