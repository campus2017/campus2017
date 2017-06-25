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
import java.text.DecimalFormat;

import com.google.common.collect.Ordering;
import com.google.common.collect.TreeBasedTable;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.DateTime;

import jxl.Workbook;
import jxl.write.*;

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

                rate = row.child(1).html();
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
       DecimalFormat df=new DecimalFormat("######0.00");
       WritableWorkbook wwb=null;
       Set<DateTime> rows=rateTable.rowKeySet();
       Map<String,BigDecimal> rowValue;
       Set<String> countries;
       OutputStream os=null;
       try{
           String[] title={"1 dollar to RMB","1 dollar to euro","1 dollar to HongKong dollar"};
           String filePath="JXL.xls";
           File file=new File(filePath);
           file.createNewFile();
           os=new FileOutputStream(filePath);
           wwb=Workbook.createWorkbook(os);
           WritableSheet sheet=wwb.createSheet("sheet1",0);
           Label label=new Label(0,0,"near 30 days exchange rate");
           sheet.addCell(label);
           for(DateTime date:rows){
               rowValue=rateTable.row(date);
               countries = rowValue.keySet();
               for(String country:countries) {
                   label = new Label(2, 0, rateTable.get(date, country).toString());
                   label = new Label(2, 1, rateTable.get(date, country).toString());
                   label = new Label(2, 2, rateTable.get(date, country).toString());
                   sheet.addCell(label);
                   wwb.write();
               }
           }

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
