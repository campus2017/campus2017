package com.cn.edu.java;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
//import javax.swing.text.Document;
//import javax.lang.model.util.Elements;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ASUS on 2017/1/30.
 */
public class ExchangeRate {
    public static Document getDocument(String url) throws IOException {
        return (Document) Jsoup.connect(url).get();
    }
    public static void filldata(ArrayList<String> data,Document doc,int col){
        Elements trs = doc.select("table").select("tr");
        for(int i=2;i<trs.size();++i){
            Elements tds = trs.get(i).select("td");
            data.add(tds.get(col).text());
        }
    }
    public static void fillExcel(ArrayList<String> data,ExcelWrite excel,int col){
        for(int i=1;i<data.size()+1;i++){
            if(col == 1){
                excel.fillLable(i,col,"1.0000");
            }else{
                excel.fillLable(i,col,data.get(i-1));
            }
        }
    }
    public static void main(String args[]) throws Exception {
        Document us = getDocument("http://www.kuaiyilicai.com/huilv/d-boc-usd.html");
        Document hk = getDocument("http://www.kuaiyilicai.com/huilv/d-boc-hkd.html");
        Document eu = getDocument("http://www.kuaiyilicai.com/huilv/d-boc-eur.html");
        ArrayList<String> datadate = new ArrayList<String>();
        ArrayList<String> dataus = new ArrayList<String>();
        ArrayList<String> datahk = new ArrayList<String>();
        ArrayList<String> dataeu = new ArrayList<String>();
        filldata(datadate,us,0);
        filldata(dataus,us,5);
        filldata(datahk,hk,5);
        filldata(dataeu,eu,5);
        ExcelWrite excel = new ExcelWrite("F:\\Rate.xls");
        excel.createExcel();
        excel.createRowCol(dataeu.size()+1,5);
        excel.fillLable(0,1,"人民币");
        excel.fillLable(0,2,"港币");
        excel.fillLable(0,3,"美元");
        excel.fillLable(0,4,"欧元");
        fillExcel(datadate,excel,0);
        fillExcel(datahk,excel,1);
        fillExcel(datahk,excel,2);
        fillExcel(dataus,excel,3);
        fillExcel(dataeu,excel,4);
        excel.save();
    }
}

