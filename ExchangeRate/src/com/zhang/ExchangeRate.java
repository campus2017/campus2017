package com.zhang;

/**
 * Created by zhangpei on 2017/6/26.
 *
 * 分析从今天开始过去 30 天时间里，中国人民银行公布的人民币汇率中间价，得到人民币对美元、欧元、
 * 港币的汇率，形成 excel 文件输出。汇率数据找相关的数据源，自己爬数据分析。（作业命名：ExchangeRate）
 */
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
public class ExchangeRate {
    //爬取数据
    public static Document getDataByJsoup(String url, String startDate, String endDate) {
        Document document = null;
        try {
            document = Jsoup.connect(url)
                    .data("startDate",startDate)
                    .data("endDate", endDate)
                    .timeout(5000).post();
        } catch (java.net.SocketTimeoutException e) {
            System.out.println("Socket连接超时");
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    //写入excel
    public static void outputToExcel(List day,List usd, List eur, List hkd){

        WritableWorkbook wwb = null;
        OutputStream os = null;
        try {
            String[] title = {"1美元对人民币", "1欧元元对人民币", "1港币对人民币"};
            String filePath = "D:\\JXL.xls";
            File file = new File(filePath);
            file.createNewFile();
            os = new java.io.FileOutputStream(filePath);
            wwb = Workbook.createWorkbook(os);
            WritableSheet sheet = wwb.createSheet("过去30天内人民币对美元、欧元、港币汇率中间价", 0);
            Label label = new Label(0, 0, "日期（30天）");
            sheet.addCell(label);
            for (int i = 1; i < title.length+1; i++) {
                label = new Label(i, 0, title[i-1]);
                sheet.addCell(label);
            }

            for (int a = 1; a < usd.size(); a++) {
                label = new Label(1,a,(String) usd.get(a));
                sheet.addCell(label);
            }
            for (int b = 1; b < eur.size(); b++) {
                label = new Label(2,b, (String) eur.get(b));
                sheet.addCell(label);
            }
            for (int c = 1; c < hkd.size(); c++) {
                label = new Label(3,c, (String) hkd.get(c));
                sheet.addCell(label);
            }
            for (int d = 1; d < day.size(); d++) {
                label = new Label(0,d,(String) day.get(d));
                sheet.addCell(label);
            }
            wwb.write();
        } catch (java.io.FileNotFoundException e) {
            System.out.println("文件没找到");
        } catch (WriteException e) {
            System.out.println("输入异常");
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (wwb != null)
                try {
                    wwb.close();
                } catch (WriteException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (java.io.IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            if (os != null)
                try {
                    os.close();
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }
        }
    }

    //从网页中抓取数据放入对应的参数中
    public static void getDataFromHTML(int days) {
        java.util.List<String> day = new java.util.ArrayList();
        java.util.List<String> usd = new java.util.ArrayList();
        java.util.List<String> eur = new java.util.ArrayList();
        java.util.List<String> hkd = new java.util.ArrayList();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String endDate = sdf.format(now);
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.setTime(now);
        c.add(java.util.Calendar.DAY_OF_YEAR, -days);
        String startDate = sdf.format(c.getTime());

        Document document = ExchangeRate
                .getDataByJsoup("http://www.chinamoney.com.cn/fe-c/historyParity.do",startDate,endDate);

        Elements trs = document.select("tr");

        for (Element tr : trs){
            Elements tds = tr.select("td.dreport-row1");
            if (tds.isEmpty())
                tds = tr.select("td.dreport-row2");
            if (tds.isEmpty())
                continue;
            usd.add(tds.get(0).html());
            eur.add(tds.get(1).html());
            hkd.add(tds.get(3).html());
        }
        Elements day_ele = document.select("tr");
        for (Element tr : day_ele){
            Elements day_ele_d = tr.select("td.dreport-row1-1");
            if (day_ele_d.isEmpty())
                day_ele_d = tr.select("td.dreport-row2-1");
            if (day_ele_d.isEmpty())
                continue;
            day.add(day_ele_d.text());

        }

        outputToExcel(day,usd, eur, hkd);
    }

    public static void main(String[] args){
        ExchangeRate.getDataFromHTML(30);
    }

}