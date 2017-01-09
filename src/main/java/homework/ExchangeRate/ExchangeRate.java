package homework.ExchangeRate;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/9.
 */
public class ExchangeRate {

    public static void main(String[] args) {
        String url = "http://www.chinamoney.com.cn/fe-c/historyParity.do";
        List<Rate> list = new ArrayList<Rate>();
        try {
            Document doc = Jsoup.connect(url).post();
            Elements tables = doc.getElementsByClass("market-new-text");
            Element table = tables.first();
            Elements trs = table.child(0).child(2).child(0).child(0).child(0).child(0).children();
            for (int i = 1; i < trs.size(); i++) {
                Element tr = trs.get(i);
                Rate rate = new Rate();
                rate.setDate(tr.child(0).child(0).html());
                rate.setUsd(Double.parseDouble(tr.child(1).html()));
                rate.setEur(Double.parseDouble(tr.child(2).html()));
                rate.setHkd(Double.parseDouble(tr.child(4).html()));
                list.add(rate);
            }

            createExcel(list);
        } catch (Exception e) {
            System.out.println("解析出错");
            e.printStackTrace();
        }
    }

    private static void createExcel(List<Rate> list) throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();
        int row = 0;
        HSSFRow header = sheet.createRow(row++);
        HSSFCell cell = header.createCell(0);
        cell.setCellValue("日期");
        cell = header.createCell(1);
        cell.setCellValue("美元");
        cell = header.createCell(2);
        cell.setCellValue("欧元");
        cell = header.createCell(3);
        cell.setCellValue("港币");

        for (Rate rate : list) {
            HSSFRow r = sheet.createRow(row++);
            int col = 0;
            cell = r.createCell(col++);
            cell.setCellValue(rate.getDate());
            cell = r.createCell(col++);
            cell.setCellValue(rate.getUsd());
            cell = r.createCell(col++);
            cell.setCellValue(rate.getEur());
            cell = r.createCell(col++);
            cell.setCellValue(rate.getHkd());
        }

        OutputStream out = new FileOutputStream(new File("汇率.xls"));
        wb.write(out);
        out.flush();
        out.close();
    }
}
