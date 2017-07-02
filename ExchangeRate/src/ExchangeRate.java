import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.SocketTimeoutException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.common.collect.Ordering;
import com.google.common.collect.TreeBasedTable;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.DateTime;

import jxl.Workbook;
import jxl.write.*;

import java.util.Set;
import java.util.Map;

public class ExchangeRate {

    private static String url = "http://www.safe.gov.cn/AppStructured/view/project_RMBQuery.action";
    private static TreeBasedTable<DateTime,String,BigDecimal> rateTable = TreeBasedTable.create(Ordering.<DateTime>natural().reverse(),Ordering.<String>natural());

    private static void getData(String url){

        Document doc = null;
        DateTime endTime=DateTime.now();
        DateTime startTime=endTime.minusDays(30);
        DateTimeFormatter formatter=DateTimeFormat.forPattern("yyyy-MM-dd");

        try{
            doc = Jsoup.connect(url).timeout(1000).data("projectBean.startDate",startTime.toString(formatter)).
                    data("projectBean.endDate", endTime.toString(formatter)).data("queryYN","true").
                    userAgent("Chrome").referrer("http://www.safe.gov.cn/AppStructured/view/project_RMBQuery.action")
                    .post();
            Element InfoTable=doc.getElementById("InfoTable");
            Element table=InfoTable.child(0);
            Elements data=table.children();
            data.remove(0);

            DateTime date;
            String dateString;
            BigDecimal tableCell;
            String rate;

            for(Element row:data){
                dateString = row.child(0).html();
                dateString = dateString.substring(0,dateString.lastIndexOf("&")).trim();
                date = formatter.parseDateTime(dateString);

                rate = row.child(1).html();
                rate = rate.substring(0,rate.lastIndexOf("&")).trim();
                tableCell=new BigDecimal(rate);
                rateTable.put(date,"美元",tableCell);

                rate = row.child(2).html();
                rate = rate.substring(0,rate.lastIndexOf("&")).trim();
                tableCell=new BigDecimal(rate);
                rateTable.put(date,"欧元",tableCell);

                rate = row.child(4).html();
                rate=rate.substring(0,rate.lastIndexOf("&")).trim();
                tableCell=new BigDecimal(rate);
                rateTable.put(date,"港元",tableCell);
            }

        }catch(SocketTimeoutException e){
            System.out.println("Socket is time out");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void createExcel()throws IOException{

        getData(url);
        WritableWorkbook book = null;
        WritableCellFormat cellFormat=new WritableCellFormat();
        cellFormat.setFont(new WritableFont(WritableFont.createFont("楷体_GB2312"),12,WritableFont.NO_BOLD,false));

        Set<DateTime> rows=rateTable.rowKeySet();
        Map<String,BigDecimal> rowValue;
        Set<String> countries;
        OutputStream os=null;
        try{
            String filePath="C:\\Users\\Echo\\Desktop\\Exchange.xls";
            File file=new File(filePath);
            file.createNewFile();
            os=new FileOutputStream(filePath);
           book=Workbook.createWorkbook(os);
            WritableSheet sheet=book.createSheet("near 30 days rate",0);
            sheet.setColumnView(0, 300);
            sheet.setColumnView(1,300);
            sheet.setColumnView(2, 300);

            String[] title={"100 Dollar to CNY","100 Euro to CNY","100 HongKong to CNY"};
            for(int t=0;t<title.length;t++){
                Label label=new Label(t,0,title[t],cellFormat);
                sheet.addCell(label);
            }
            int i=1;
            for(DateTime date:rows) {
                rowValue = rateTable.row(date);
                countries = rowValue.keySet();

                Label labe1 = new Label(0, i, rateTable.get(date, "美元").toString());
                sheet.addCell(labe1);

                Label labe2 = new Label(1, i, rateTable.get(date, "欧元").toString());
                sheet.addCell(labe2);

                Label labe3 = new Label(2, i, rateTable.get(date, "港元").toString());
                sheet.addCell(labe3);
                i++;
            }
            book.write();

        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }catch(WriteException e){
            System.out.println("input error");
        }finally {
            if(book!=null)
                try{
                    book.close();
                }catch (WriteException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }
            if(os!=null)
                try{
                    os.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
        }
    }
    public  static void main(String[] args){
     try {
            ExchangeRate.createExcel();
        }catch(IOException e){
        }
    }

}