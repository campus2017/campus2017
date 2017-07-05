import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

import jxl.write.WritableWorkbook;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.Label;
import jxl.Workbook;

/**
 * Created by fmz on 2017/6/27.
 */
public class ExchangeRate {
    public static void main(String[] args) {
        File file = new File("rate.xls");
        WritableWorkbook workbook = null;
        WritableSheet sheet = null;

        //创建表格
        try {
            workbook = Workbook.createWorkbook(file);
            sheet = workbook.createSheet("rate", 0);
        } catch(IOException e) {
            e.printStackTrace();
        }

        addCellToSheet(sheet, 0, 0, "日期");
        addCellToSheet(sheet, 1, 0, "USD/CNY");
        addCellToSheet(sheet, 2, 0, "EUR/CNY");
        addCellToSheet(sheet, 3, 0, "HKD/CNY");

        //数据来源
        String url = "http://www.chinamoney.com.cn/fe-c/historyParity.do";
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //选择表
        Elements table = doc.select("td.dreport-title");
        //标题
        Elements title = doc.select("td.mbr-title");
        System.out.println(title.first().text());
        //选择行
        Elements trs = table.select("tr");
        //按行统计数据
        for(int i=0; i<trs.size(); ++i) {
            Element tr = trs.get(i);
            Elements td = tr.select("td.dreport-row2");
            if(td.isEmpty()) {
                td = tr.select("td.dreport-row1");
                if(td.isEmpty()) {
                    continue;
                }
            }

            Elements day = tr.select("td.dreport-row2-1");
            if(day.isEmpty()) {
                day = tr.select("td.dreport-row1-1");
                if(day.isEmpty()) {
                    continue;
                }
            }

            System.out.println("date:" + day.get(0).text() + " usd:" + td.get(0).text() + " eur:" + td.get(1).text() + " hkd:" + td.get(3).text());

            addCellToSheet(sheet, 0, i, day.get(0).text());
            addCellToSheet(sheet, 1, i, td.get(0).text());
            addCellToSheet(sheet, 2, i, td.get(1).text());
            addCellToSheet(sheet, 3, i, td.get(3).text());
        }

        try {
            workbook.write();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }

    }

    public static void addCellToSheet(WritableSheet sheet, int col, int row, String text) {
        try {
            sheet.addCell(new Label(col, row, text));
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }
}