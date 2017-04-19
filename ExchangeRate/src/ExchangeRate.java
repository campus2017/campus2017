
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.net.SocketTimeoutException;
import java.text.DecimalFormat;
import java.util.*;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.SimpleDateFormat;

//import qunar.fury.ExchangeRate;
public class ExchangeRate {

    //爬取数据
    public static Document getDataByJsoup(String url, String startDate, String endDate) {
        Document doc2 = null;
        try {
            doc2 = Jsoup.connect(url)
                    .data("startDate",startDate)
                    .data("endDate", endDate)
                    .timeout(5000).post();
            String title = doc2.body().toString();
        } catch (SocketTimeoutException e) {
            System.out.println("Socket连接超时");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc2;
    }

    //写入excel
    public static void outputToExcel(double usd, double eur, double hkd){
        DecimalFormat df = new DecimalFormat("######0.00");
        WritableWorkbook wwb = null;
        OutputStream os = null;
        try {
            String[] title = {"1美元对人民币", "1欧元元对人民币", "1港币对人民币"};
            String filePath = "./excl/JXL.xls";
            File file = new File(filePath);
            file.createNewFile();
            os = new FileOutputStream(filePath);
            wwb = Workbook.createWorkbook(os);
            WritableSheet sheet = wwb.createSheet("sheet1", 0);
            Label label = new Label(0, 0, "30天内RMB汇率中间价");
            sheet.addCell(label);
            for (int i = 0; i < title.length; i++) {
                label = new Label(1, i, title[i]);
                sheet.addCell(label);
            }
            label = new Label(2, 0, df.format(usd));
            sheet.addCell(label);
            label = new Label(2, 1, df.format(eur));
            sheet.addCell(label);
            label = new Label(2, 2, df.format(hkd));
            sheet.addCell(label);
            wwb.write();
        } catch (FileNotFoundException e) {
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
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            if (os != null)
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    //求平均值
    public static void getAvg(int days) {
        double usd = 0;
        double eur = 0;
        double hkd = 0;
        //http://www.chinamoney.com.cn/fe-c/historyParity.do
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String endDate = sdf.format(now);
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        c.add(Calendar.DAY_OF_YEAR, -days);
        String startDate = sdf.format(c.getTime());

        Document document = ExchangeRate
                .getDataByJsoup("http://www.chinamoney.com.cn/fe-c/historyParity.do",startDate,endDate);

        Elements trs = document.select("tr");
        int count = 0;
        for (Element tr : trs){
            Elements tds = tr.select("td.dreport-row1");
            if (tds.isEmpty())
                tds = tr.select("td.dreport-row2");
            if (tds.isEmpty())
                continue;
            usd += Double.parseDouble(tds.get(0).html());
            eur += Double.parseDouble(tds.get(1).html());
            hkd += Double.parseDouble(tds.get(3).html());
            count++;
        }

        usd = usd / count;
        eur = eur / count;
        hkd = hkd / count;
        outputToExcel(usd, eur, hkd);
    }

    public static void main(String[] args){
        ExchangeRate.getAvg(30);
    }
}

