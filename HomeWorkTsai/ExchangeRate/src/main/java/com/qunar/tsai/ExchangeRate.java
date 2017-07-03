package com.qunar.tsai;


import org.apache.poi.hssf.usermodel.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by joeTsai on 2017/6/20.
 * 分析从今天开始过去 30 天时间里，中国人民银行公布的人民币汇率中间价，得到人民币对美元、欧元、
 *港币的汇率，形成 excel 文件输出。汇率数据找相关的数据源，自己爬数据分析。（作业命名：ExchangeRate）
 */

public class ExchangeRate {

    private static ResourceBundle bundle = ResourceBundle.getBundle("constants");

    public static void main(String[] args) {
        String url = bundle.getString("url");
        String filePath = bundle.getString("path");
        List<ExRateBean> beans = getExchangeRateBean(url);
        generateRateExcel(beans, filePath);
    }

    private static List<ExRateBean> getExchangeRateBean(String url) {
        List<ExRateBean> list = new ArrayList<ExRateBean>();
        try {
            Document doc = Jsoup.connect(url).post();
            Elements tables = doc.getElementsByClass("market-new-text");
            Element table = tables.first();
            Elements elements = table.child(0).child(2).child(0).child(0).child(0).child(0).children();
            for (int i = 1; i < elements.size(); i++) {
                Element element = elements.get(i);
                ExRateBean bean = new ExRateBean();
                bean.setDate(element.child(0).child(0).html());
                bean.setDollar(Double.parseDouble(element.child(1).html()));
                bean.setEuro(Double.parseDouble(element.child(2).html()));
                bean.setHkd(Double.parseDouble(element.child(4).html()));
                list.add(bean);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static void generateRateExcel(List<ExRateBean> beans, String filePath) {

        HSSFWorkbook workbook = new HSSFWorkbook();  //创建workbook;
        HSSFSheet sheet = workbook.createSheet(bundle.getString("filename")); //创建sheet;
        HSSFRow row = sheet.createRow(0); //创建行row;
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //设置居中处理;
        //创建单元格
        HSSFCell cell = row.createCell(0);
        cell.setCellValue(bundle.getString("date"));
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue(bundle.getString("rmb2dollar"));
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue(bundle.getString("rmb2euro"));
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue(bundle.getString("rmb2hkd"));
        cell.setCellStyle(style);

        for (int i = 0; i < beans.size(); i++) {
            ExRateBean bean = beans.get(i);
            row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(bean.getDate());
            row.createCell(1).setCellValue(bean.getDollar());
            row.createCell(2).setCellValue(bean.getEuro());
            row.createCell(3).setCellValue(bean.getHkd());
        }
        try {
            FileOutputStream stream = new FileOutputStream(filePath);
            workbook.write(stream);
            stream.close();
            System.out.println(bundle.getString("tip") + bundle.getString("path"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

