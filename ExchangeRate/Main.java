package com.lfz;

import org.apache.poi.hssf.usermodel.*;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws Exception {
        HSSFWorkbook workbook= new HSSFWorkbook();
        HSSFSheet sheet= workbook.createSheet("rate");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell=null;
        row.createCell(0).setCellValue("日期");
        row.createCell(1).setCellValue("美元");
        row.createCell(2).setCellValue("欧元");
        row.createCell(3).setCellValue("港币");
        String[] money={"1316","1326","1315"};
        Date d=new Date();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 30; i++) {
            row=sheet.createRow(i+1);
            String time=df.format(new Date(d.getTime() - (long)i * 24 * 60 * 60 * 1000));
            row.createCell(0).setCellValue(time);
            for (int j = 0; j < 3; j++) {
                Document doc= Jsoup.connect("http://srh.bankofchina.com/search/whpj/search.jsp?erectDate="+time+"&nothing="+time+"&pjname="+money[j]).get();
                row.createCell(j + 1).setCellValue(doc.select("[class=BOC_main publish]").select("td").get(6).text());
            }

        }
        FileOutputStream os=new FileOutputStream("/Users/lfz/15.xls");
        workbook.write(os);
        os.close();
    }
}

