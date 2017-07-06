package com.qunar.logic;

import com.sun.xml.internal.ws.spi.db.DatabindingException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Logic on 2017/7/5.
 */
public class ExchangeRate {
    public static void main(String[] args) {
        Date endDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Document usDocument = null;
        Document eurDocument = null;
        Document hkDocument = null;
        List<Rate> rates = new ArrayList<Rate>();
        try {
            for (int i = 0; i < 30; i++) {
                String currentTime = simpleDateFormat.format(new Date(endDate.getTime() - (long)i * 24 * 60 * 60 * 1000));
                usDocument = Jsoup.connect("http://srh.bankofchina.com/search/whpj/search.jsp?erectDate="+currentTime+"&nothing="+currentTime+"&pjname=1316").get();
                eurDocument = Jsoup.connect("http://srh.bankofchina.com/search/whpj/search.jsp?erectDate="+currentTime+"&nothing="+currentTime+"&pjname=1326").get();
                hkDocument = Jsoup.connect("http://srh.bankofchina.com/search/whpj/search.jsp?erectDate="+currentTime+"&nothing="+currentTime+"&pjname=1315").get();
                Rate rate = new Rate();
                rate.setDate(currentTime);
                rate.setUSDollar(usDocument.select("[class=BOC_main publish]").select("td").get(6).text());
                rate.setEuro(eurDocument.select("[class=BOC_main publish]").select("td").get(6).text());
                rate.setHKDollar(hkDocument.select("[class=BOC_main publish]").select("td").get(6).text());
                rates.add(rate);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeDataToExcel(rates);
    }

    /**
     * 讲抓取的数据存到Excel表格中
     * @param list
     */
    public static void writeDataToExcel(List<Rate> list) {
        Workbook workbook = new HSSFWorkbook();
        Calendar calendar = Calendar.getInstance();
        try (FileOutputStream fileOutputStream = new FileOutputStream("C:/Users/Logic/Dropbox/IntelijProject/ExchangeRate/ExchangeRate" +
                calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DATE) +".xls")) {
            Sheet sheet = workbook.createSheet();
            Row title = sheet.createRow(0);
            title.createCell(0).setCellValue("日期");
            title.createCell(1).setCellValue("美元");
            title.createCell(2).setCellValue("欧元");
            title.createCell(3).setCellValue("港币");
            int i = 1;
            for (Rate rate :
                    list) {
                Row row = sheet.createRow(i);
                row.createCell(0).setCellValue(rate.getDate());
                row.createCell(1).setCellValue(rate.getUSDollar());
                row.createCell(2).setCellValue(rate.getEuro());
                row.createCell(3).setCellValue(rate.getHKDollar());
                i++;
            }
            workbook.write(fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
