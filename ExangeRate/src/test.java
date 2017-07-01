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

/**
 * Created by dell--pc on 2017/6/18.
 */
public class test {
    public static void main(String[] args) throws IOException, WriteException {
        //jxl创建excel文件
        File excel=new File("ExangeRate.xls");
        WritableWorkbook workbook= Workbook.createWorkbook(excel);
        WritableSheet sheet = workbook.createSheet("sheet1", 0);

        //使用jsoup爬取网页信息
        Document doc = Jsoup.connect("http://www.chinamoney.com.cn/fe-c/historyParity.do").get();
        Elements elements=doc.select("tr>td.dreport-title");
        Elements elements1=doc.select("td[class~=dreport-row.*]");


        sheet.addCell(new Label(0, 0, "日期/货币"));

        System.out.print("    日期     ");
        int i=0,j=0;

        //写入excel
        for(Element e:elements){
            if(j==0){
                j++;
                continue;
            }
            if(j==1||j==2){
                sheet.addCell(new Label(j, 0, e.text()));
                System.out.print(e.text()+"  ");
            }
            if(j==4){
                sheet.addCell(new Label(j-1, 0, e.text()));
                System.out.print(e.text()+"  ");
            }
            j++;

        }
        System.out.println();
        System.out.println("-----------------------------");

        int row=1,col=0;
        for (Element e:elements1){
            if(col==0||col==1||col==2){
                sheet.addCell(new Label(col, row, e.text()));
                System.out.print(e.text()+"   ");
            }
            if(col==4){
                sheet.addCell(new Label(col-1, row, e.text()));
                System.out.print(e.text()+"   ");
            }
            col++;

            if(col%24==0){
                row++;
                col=0;
                System.out.println();
            }

        }
        workbook.write();
        workbook.close();

    }
}
