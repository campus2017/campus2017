package com.qunar.zhangguoqiang;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
public class ExchangeRate {
    private String outExcelName="";
    private List< String> date=new ArrayList<String>();
    private List< String> usd=new ArrayList<String>();;
    private List< String> eur=new ArrayList<String>();;
    private List< String> hkd=new ArrayList<String>();;
    public ExchangeRate(String filename)
    {
        outExcelName=filename;
        String[] rateUrl = new String[]{
        "http://www.kuaiyilicai.com/huilv/d-safe-usd.html",
         "http://www.kuaiyilicai.com/huilv/d-safe-eur.html",
         "http://www.kuaiyilicai.com/huilv/d-safe-hkd.html"
        };
        Document rateDoc=null;
        int type=0;
        for(String url:rateUrl)
        {
            try {
                rateDoc=getRateHtml(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            parseRateHtml(rateDoc,type++);
        }
        outToExcel();
    }
    private Document getRateHtml(String url) throws IOException {
        Document rateDoc = Jsoup.connect(url).get();
        return rateDoc;
    }
    private void parseRateHtml(Document rateDoc,int type)
    {
        Element  rateTbody;
        rateTbody=rateDoc.getElementsByTag("tbody").iterator().next();
        for(Element rateTr:rateTbody.children())
        {
            if(type==0)
            {
                date.add(rateTr.child(0).html());
                usd.add(rateTr.child(1).html());
            }
            else if(type==1)
                eur.add(rateTr.child(1).html());
            else
                hkd.add(rateTr.child(1).html()); ;
        }
    }

    private void outToExcel()
    {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("Rate");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        sheet.setDefaultColumnWidth(15);
        cell.setCellValue("日期");
        cell = row.createCell(1);
        cell.setCellValue("美元汇率中间价");
        cell = row.createCell(2);
        cell.setCellValue("欧元汇率中间价");
        cell = row.createCell(3);
        cell.setCellValue("港币汇率中间价");
        //向单元格里填充数据
        for (int i = 0; i <date.size(); i++) {
            row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(date.get(i));
            row.createCell(1).setCellValue(Double.parseDouble(usd.get(i)));
            row.createCell(2).setCellValue(Double.parseDouble(eur.get(i)));
            row.createCell(3).setCellValue(Double.parseDouble(hkd.get(i)));
        }
        try {
            FileOutputStream outexcel = new FileOutputStream(outExcelName);
            wb.write(outexcel);
            outexcel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args)
    {
        Scanner scanner=new Scanner(System.in);
        System.out.println("输入Excel路径");
        String outExcelFile=scanner.nextLine();
        ExchangeRate exchangeRate=new ExchangeRate(outExcelFile);
    }
}
