/**
 * Created by steamedfish on 17-6-24.
 * http://www.open-open.com/jsoup/parsing-a-document.htm
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.SocketTimeoutException;

import com.google.common.collect.Ordering;
import com.google.common.collect.TreeBasedTable;
import jxl.write.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.DateTime;

import jxl.Workbook;


import java.util.Set;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ExchangeRate {

    private static String Bankurl = "http://www.safe.gov.cn/AppStructured/view/project_RMBQuery.action";//the url;
    private TreeBasedTable<DateTime,String,BigDecimal> rateTable=
            TreeBasedTable.create(Ordering.<DateTime>natural().reverse(),Ordering.<String>natural());

    //get the data from url China people bank and analyze the thirty days' exchange rate
    private void getDigitFromJsoup(String url){

        Document doc = null;
        DateTime end=DateTime.now();
        DateTime start=end.minusDays(30);
        DateTimeFormatter formatter=DateTimeFormat.forPattern("yyyy-MM-dd");

        //System.out.println(start.toString(formatter));
        try{
            doc = Jsoup.connect(url).timeout(1000).data("projectBean.startDate",start.toString(formatter)).
                    data("projectBean.endDate",end.toString(formatter)).data("queryYN","true").
                    userAgent("Chrome").referrer("http://www.safe.gov.cn/AppStructured/view/project_RMBQuery.action")
                    .post();
            Element InfoTable=doc.getElementById("InfoTable");
            //System.out.println(doc);
            Element table=InfoTable.child(0);
            Elements digit=table.children();
            digit.remove(0);//remove the first line;

            DateTime date;
            String dateString;
            BigDecimal tableCell;
            String rate;

            for(Element row:digit){

                dateString = row.child(0).html();//get 0 col,html()remove the html tag
                dateString = dateString.substring(0,dateString.lastIndexOf("&")).trim();//remove the blank
                date = formatter.parseDateTime(dateString);

                rate = row.child(1).html();
                rate=rate.substring(0,rate.lastIndexOf("&")).trim();
                tableCell=new BigDecimal(rate);
                rateTable.put(date,"美元",tableCell);

                rate = row.child(2).html();
                rate=rate.substring(0,rate.lastIndexOf("&")).trim();
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

    private void writeToExcel()throws IOException{
       //DecimalFormat df=new DecimalFormat("#######.##");
       WritableFont font=new WritableFont(WritableFont.TIMES,12,WritableFont.BOLD);
       WritableCellFormat format=new WritableCellFormat(font);
       WritableWorkbook wwb=null;
       Set<DateTime> rows=rateTable.rowKeySet();
       Map<String,BigDecimal> rowValue;
       Set<String> countries;
       OutputStream os=null;
       try{
           String[] title={"100 Dollar to CNY(¥)","100 Euro to CNY(¥)","100 HongKong Dollar to CNY(¥)"};
           String filePath="Exchange.xls";
           File file=new File(filePath);
           file.createNewFile();
           os=new FileOutputStream(filePath);
           wwb=Workbook.createWorkbook(os);
           WritableSheet sheet=wwb.createSheet("Near 30 days exchange rate",0);
           //sheet.setRowView(1,300);
           sheet.setColumnView(0,300);
           sheet.setColumnView(1,300);
           sheet.setColumnView(2,300);
          // Label label=new Label(0,0,"Near 30 days exchange rate");
           //sheet.addCell(label);
           for(int t=0;t<title.length;t++){
               Label label=new Label(t,0,title[t],format);
               sheet.addCell(label);
           }
           int i=1;
           for(DateTime date:rows) {
               rowValue = rateTable.row(date);
               countries = rowValue.keySet();
               System.out.println(rowValue);

               Label label = new Label(0, i, rateTable.get(date, "美元").toString());
               sheet.addCell(label);
               //wwb.write();
               System.out.println(label);

               Label label1 = new Label(1, i, rateTable.get(date, "欧元").toString());
               sheet.addCell(label1);
               // wwb.write();

               Label label2 = new Label(2, i, rateTable.get(date, "港元").toString());
               sheet.addCell(label2);
               //wwb.write();
               i++;
           }
           wwb.write();

       }catch(FileNotFoundException e){
           System.out.println("File not found");
       }catch(WriteException e){
           System.out.println("input error");
       }finally {
           if(wwb!=null)
               try{
               wwb.close();
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
        ExchangeRate exchangeRate=new ExchangeRate();
        exchangeRate.getDigitFromJsoup(Bankurl);
        try {
            exchangeRate.writeToExcel();
        }catch(IOException e){
        }
    }

}
