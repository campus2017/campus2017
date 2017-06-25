package com.QunarTask;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.File;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
	    String[] urls=new String[3];

        urls[0]="http://www.kuaiyilicai.com/huilv/d-safe-usd.html";   //人民币对美元
        urls[1]="http://www.kuaiyilicai.com/huilv/d-safe-eur.html";   //人民币对欧元
        urls[2]="http://www.kuaiyilicai.com/huilv/d-safe-hkd.html";   //人民币对港币

        getThirtyAvg(urls);
    }

    //得到30天时间里，中国人民银行公布的人民币对美元、欧元和港币的汇率中间价
    public static void getThirtyAvg(String[] urls){
        Map<String,List<ContentBean>> map=new HashMap<>();
        Document documentUSA = getDataByJsoup(urls[0]);
        map.put("USA",handleDocument(documentUSA));
        Document documentEUR = getDataByJsoup(urls[1]);
        map.put("EUR",handleDocument(documentEUR));
        Document documentHK = getDataByJsoup(urls[2]);
        map.put("HK",handleDocument(documentHK));
        writeToExcel(map,"ExchangeRate.xls");

    }

    //依赖Jsoup工具抓取网页数据
    public static Document getDataByJsoup(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).timeout(5000).get();
        } catch (SocketTimeoutException e) {
            System.out.println("Socket连接超时");
        } catch (IOException e){
            e.printStackTrace();
        }
        return doc;
    }

    //处理抓取到的Doucument
    public static List<ContentBean> handleDocument(Document document){

        List<ContentBean> list = new ArrayList();

        Elements es =document.select(".table-responsive tr");

        if(es.size()>0){
            for(int i=1;i<es.size();i++){
                Document doc = Jsoup.parse(es.get(i).toString());
                String[] res = doc.body().text().toString().split(" ");
                String date = res[0];
                float exchangeRate = new Float(res[1]);
                ContentBean bean = new ContentBean(date, exchangeRate);
                list.add(bean);
            }
        }
        return list;
    }

    //写入到EXCEL文件中
    private static void writeToExcel(Map<String, List<ContentBean>> map, String path) {
        WritableWorkbook writable = null;
        File file = new File(path);

        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            writable = Workbook.createWorkbook(file);
            WritableSheet sheet = writable.createSheet("30天内RMB汇率中间价",0);
            Label date = new Label(0,0,"DATE");

            sheet.addCell(date);

            int j=1;

            for(Map.Entry<String,List<ContentBean>> entry:map.entrySet()){
                String country = entry.getKey();
                List<ContentBean> data = entry.getValue();

                Label con = new Label(j,0,country);
                sheet.addCell(con);

                for(int i=0;i<data.size();i++){
                    Label dateData = new Label(0,i+1,data.get(i).getDate());
                    Label price = new Label(j,i+1,data.get(i).getExchangeRate()+"");
                    if(j==1)
                        sheet.addCell(dateData);
                    sheet.addCell(price);
                }
                j++;
            }

            writable.write();
            writable.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
