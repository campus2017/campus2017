import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by liuliling on 2017/6/20.
 */
public class ExchangeRate {

    public static void insertExcel(List<Element> rows) throws IOException, BiffException, WriteException {
        int row=0;
        String title=rows.get(0).text();     //文件名
        File file =new File(title+".xls");
        file.createNewFile();
        Workbook wb = Workbook.getWorkbook(file);     //创建excel表格
        WritableWorkbook book = Workbook.createWorkbook(file, wb);
        WritableSheet sheet=book.getSheet(0);
        Elements td = rows.get(4).select("td");//获取表格第三行的属性

        for (int i=4;i<rows.size();i++){   //遍历工作列表，一行行插入到表格中
            int col=0;
            for (int j = 0; j < td.size(); j++) {
                String text = td.get(j).text();
                Pattern p = Pattern.compile("日期|USD/CNY|EUR/CNY|HKD/CNY");
                Matcher m = p.matcher(text);
                boolean b = m.matches();
                if (b)
                {
                    sheet.addCell(new Label(col,row,rows.get(i).select("td").get(j).text()));
                    col++;
                }
            }
            row++;
        }
        book.write();
        book.close();
    }
    public static void main(String[] args) {
        try {
            Document  document = Jsoup.connect(
                    "http://www.chinamoney.com.cn/fe-c/historyParity.do")
                    .get();
            List<Element> rows = document.select("table").select("tr");    //获取表格行属性
            insertExcel(rows);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }

    }
}


