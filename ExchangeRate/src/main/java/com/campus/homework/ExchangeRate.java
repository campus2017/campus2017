package com.campus.homework;

import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import jxl.Workbook;
import org.jsoup.select.Elements;


/**
 * Hello world!
 *
 */
public class ExchangeRate
{
    public static void main( String[] args )
    {
        File file = new File("Exchangerate.xls");
        WritableWorkbook wwb = null;
        ExchangeRate er = new ExchangeRate();
        try {
            wwb = Workbook.createWorkbook(file);
            WritableSheet ws = wwb.createSheet("中国人民银行30天汇率中间价",0);
            er.writeExchangeDataToExcel(ws);
            wwb.write();
            wwb.close();
        } catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("Creat file exception!");
        } catch (WriteException e) {
            e.printStackTrace();
            System.out.println("Write file exception!");
        }
    }

    public Document getDataByDate(String date) throws IOException {
        Document doc = Jsoup.connect("http://www.kuaiyilicai.com/bank/rmbfx/b-safe.html")
                            .data("querydate", date)
                            .get();
        return doc;
    }

    public void writeExchangeDataToExcel(WritableSheet ws) throws WriteException {
        Calendar calendar = Calendar.getInstance();
        //添加表头
        Label lb = new Label(0, 0, "日期");
        ws.addCell(lb);
        lb = new Label(1, 0, "美元");
        ws.addCell(lb);
        lb = new Label(2, 0, "欧元");
        ws.addCell(lb);
        lb = new Label(3, 0, "港币");
        ws.addCell(lb);
        int i = 1;
        while(i < 31) {
            try {
                //按日期获取数据
                SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd" );
                String date = fmt.format(calendar.getTime());
                Document doc = getDataByDate(date);
                Elements tdContent = doc.select("td[align=left]");
                //写入汇率
                lb = new Label(0, i, date);  //日期
                ws.addCell(lb);
                lb = new Label(1, i, tdContent.get(3).text());  //美元
                ws.addCell(lb);
                lb = new Label(2, i, tdContent.get(5).text());  //欧元
                ws.addCell(lb);
                lb = new Label(3, i, tdContent.get(1).text());  //港币
                ws.addCell(lb);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Get data from url exception!");
            }
            i++;
            calendar.add(Calendar.DATE, -1);
        }
    }

}
