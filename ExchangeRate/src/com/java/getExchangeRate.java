package com.java;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * Created by apple on 17/7/1.
 */

public class getExchangeRate {
   private Vector<exchangeRateData> allExchangeRate=null;//一个月的汇率
    private  String removeHtmlString(String str)
    {
        String res=str.replace("<td width=\"8%\" align=\"center\">","").replace("</td>","").replace("&nbsp;","").replace(" ","");
        return  res;
    }
    private  void getHttpResponse(Date today)
    {
        int days=30;

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_MONTH, -30);
        Date firstDay = calendar.getTime();
        Document doc = null;

        String url="http://www.safe.gov.cn/AppStructured/view/project_RMBQuery.action";
        Connection conn = Jsoup.connect(url);

        conn.data("projectBean.startDate","2017-06-01");
        conn.data("projectBean.endDate","2017-06-30");
        conn.data("queryYN","true");
//        Connection conn=Jsoup.connect("http://wzdig.pbc.gov.cn:8080/dig/ui/search.action?ty=&w=false&f=&dr=true&p=1&sr=score+desc%2Cdatetime+desc&rp=&advtime=&advrange=&fq=&ext=&q=+%E6%B1%87%E7%8E%87");
//
        try {
            doc = conn.timeout(100000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
      //  Elements results = new Elements();
        Element infoTable = doc.getElementById("InfoTable");
        Elements tableLineInfos = infoTable.select("tr");
        //定位美元、欧元、港币在表格中的位置

        int usaPos=-1;
        int eurPos=-1;
        int hkPos=-1;
        Elements tableHeader=tableLineInfos.get(0).select("th");
        int i=0;
        for(Element ele:tableHeader)
        {
            String str=ele.toString();
            if(str.contains("美元"))
                usaPos=i;
            else  if(str.contains("欧元"))
                eurPos=i;
            else if(str.contains("港元"))
                hkPos=i;
            i++;
        }
        tableLineInfos.remove(0);
        for (Element lineInfo : tableLineInfos) {
            Elements info=lineInfo.select("td");

            String now=removeHtmlString(info.get(0).toString());

            double usa= Double.valueOf(removeHtmlString(info.get(usaPos).toString()));
            double eur= Double.valueOf(removeHtmlString(info.get(eurPos).toString()));
            double hk= Double.valueOf(removeHtmlString(info.get(hkPos).toString()));
            exchangeRateData newData=new exchangeRateData(now,usa,eur,hk);
            allExchangeRate.add(newData);
        }


    }

    private void getExchangeRate(Date date)
    {
        allExchangeRate.clear();
        getHttpResponse(date);

    }
    public  getExchangeRate()
    {
        allExchangeRate=new Vector<>();
        Date today= new Date();
        getExchangeRate(today);

    }

    public Vector<exchangeRateData> getAllExchangeRate() {
        return allExchangeRate;
    }
}
