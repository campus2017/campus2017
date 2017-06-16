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
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by xiazihao on 2017/5/7.
 */
public class Main {


    public static void main(String[] argv) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String endDate = simpleDateFormat.format(now);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DAY_OF_YEAR, -30);
        String startDate = simpleDateFormat.format(calendar.getTime());
        System.out.println(startDate);
        System.out.println(endDate);
        String url = "http://www.chinamoney.com.cn/fe-c/historyParity.do";
        List<String> usd = new ArrayList<>();
        List<String> eur = new ArrayList<>();
        List<String> hkd = new ArrayList<>();
        List<String> date = new ArrayList<>();
        Document document = null;
        try {
            document = Jsoup.connect(url)
                    .data("startDate", startDate)
                    .data("endDate", endDate)
                    .timeout(5000).post();
        } catch (IOException ioe) {
            System.out.print("IOException");
        }
        Elements trs = document.select("tr");
        for (Element tr :
                trs) {
            Elements tds = tr.select("td.dreport-row2");
            if (tds.isEmpty()) {
                tds = tr.select("td.dreport-row1");
                if (tds.isEmpty()) {
                    continue;
                }

            }
            Elements day = tr.select("td.dreport-row2-1");
            if (day.isEmpty()) {
                day = tr.select("td.dreport-row1-1");
                if (day.isEmpty()) {
                    continue;
                }
            }
//            System.out.println(day.get(0).text());
            date.add(day.get(0).text());
            usd.add(tds.get(0).text());
            eur.add(tds.get(1).text());
            hkd.add(tds.get(3).text());
        }
        date.remove(0);
        usd.remove(0);
        eur.remove(0);
        hkd.remove(0);
        for (int i = 0; i < usd.size(); i++) {
            System.out.println("usd: " + usd.get(i) + "  eur: " + eur.get(i) + "  hkd: " + hkd.get(i));
        }
        File xlsFile = new File("test.xls");
        WritableWorkbook workbook = null;
        WritableSheet sheet = null;
        try {
            workbook = Workbook.createWorkbook(xlsFile);
            sheet = workbook.createSheet("sheet 1", 0);


        } catch (IOException ioe) {
            ioe.printStackTrace();
            return;
        }

        try {
            sheet.addCell(new Label(0, 0, "日期"));
            sheet.addCell(new Label(1, 0, "美元"));
            sheet.addCell(new Label(2, 0, "欧元"));
            sheet.addCell(new Label(3, 0, "港币"));
            for (int i = 0; i < date.size(); i++) {
                sheet.addCell(new Label(0, i + 1, date.get(i)));
                sheet.addCell(new Label(1, i + 1, usd.get(i)));
                sheet.addCell(new Label(2, i + 1, eur.get(i)));
                sheet.addCell(new Label(3, i + 1, hkd.get(i)));
            }
        } catch (WriteException we) {
            we.printStackTrace();

        }
        try {
            workbook.write();
            workbook.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();

        } catch (WriteException w) {
            w.printStackTrace();
        }
        return;
    }
}
