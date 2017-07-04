package com.qunar.dxq;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;


/**
 * Created by dxq on 2017/7/2.
 */
public class ExchangeRate {

    public static void main(String[] args) {
        List<Rate> list = new ArrayList<Rate>();
        try {
            Document doc = Jsoup.connect("http://www.chinamoney.com.cn/fe-c/historyParity.do").post();
            Elements tables = doc.getElementsByClass("market-new-text");
            Element table = tables.first();
            Elements trs = table.child(0).child(2).child(0).child(0).child(0).child(0).children();
            for (int i = 1; i < trs.size(); i++) {
                Element tr = trs.get(i);
                Rate rate = new Rate();
                rate.setDate(tr.child(0).child(0).html());
                rate.setUSD(Double.parseDouble(tr.child(1).html()));
                rate.setEUD(Double.parseDouble(tr.child(2).html()));
                rate.setHKD(Double.parseDouble(tr.child(4).html()));
                list.add(rate);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void writeExcel(List<Rate> list) {
        WritableWorkbook book = null;
        try {
            // 打开文件
            book = Workbook.createWorkbook(new File("D:/Rate.xls"));

            // 生成名为"Rate"的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("Rate", 0);

            // 指定单元格位置是第一列第一行(0, 0)以及单元格内容为日期
            Label label = new Label(0, 0, "Date");
            sheet.addCell(label);            // 将定义好的单元格添加到工作表中

            Label label1 = new Label(1, 0, "USD");
            sheet.addCell(label1);

            Label label2 = new Label(2, 0, "EUD");
            sheet.addCell(label2);

            Label label3 = new Label(3, 0, "HKD");
            sheet.addCell(label3);

            if (list != null && !list.isEmpty()) {
                for (int i = 1; i < list.size(); i++) {
                    sheet.addCell(new Label(0, i, list.get(i).getDate()));
                    sheet.addCell(new Number(1, i, list.get(i).getUSD()));
                    sheet.addCell(new Number(2, i, list.get(i).getEUD()));
                    sheet.addCell(new Number(3, i, list.get(i).getHKD()));
                }
            }

            book.write(); // 写入数据并关闭文件
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (book != null) {
                try {
                    book.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
