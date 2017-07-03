
import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import jxl.*;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import javax.print.DocFlavor;

import static java.lang.String.format;

public class ExchangeRate{
    private static int p=50;
    public static void main(String arg[]) throws IOException {
        Calendar today=getToday();
        today.add(Calendar.DATE,-p);
        java.util.List<String> excelData = new ArrayList<String>();
        excelData.add("日期");excelData.add("人民币对美元");
        excelData.add("人民币对欧元");excelData.add("人民币对港元");
        long dateNum=today.get(Calendar.YEAR)*500+(today.get(Calendar.MONTH)+1)*31+today.get(Calendar.DATE);
        boolean end=false;
        for(int page=1;false==end;page++)
        {
            Document doc = Jsoup.connect(getUrl(page)).post();
            Elements links=doc.getElementsByTag("a");

            for (Element link:links) {
                String name = link.text();
                if (name.indexOf("中国外汇交易中心受权公布人民币汇率中间价公告")!=-1) {
                    int []date=getDateFromString(name);
                    if((date[0]*500+date[1]*31+date[2])>dateNum) //在30天之类
                    {

                        System.out.println(":  " + name + "\n" + link.attr("abs:href"));
                        String []data=getRate(link.attr("abs:href"));
                        excelData.add(String.format("%d-%d-%d",date[0],date[1],date[2]));
                        excelData.add(data[0]);
                        excelData.add(data[1]);
                        excelData.add(data[2]);
                    }

                    else {
                        end=true;
                        break;
                    }
                }
            }

        }
        try {
            writeExcel(excelData,"ExchangeRate.xls");
        } catch (WriteException e) {
            e.printStackTrace();
        }

    }
    //从网络获取当前日期 北京时间
    public static Calendar getToday()  {
        try {
            URL url = new URL("http://www.beijing-time.org");
            URLConnection conn=url.openConnection();
            conn.connect();
            long dateL=conn.getDate();
            Date date=new Date(dateL);
            Calendar today=Calendar.getInstance();
            today.setTime(date);
            return today;
        }catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    // 获取每一页的网址
    public static String getUrl(int page) {
        return new String(format("http://www.pbc.gov.cn/zh" +
                "engcehuobisi/125207/125217/125925/17105/index%d.html",page));
    }
    public  static int [] getDateFromString(String name)
    {
        int []date={0,0,0};
        for (int i=0,j=0;i<name.length()&j<3;i++)
        {
            char t=name.charAt(i);
            if((t>='0')&&(t<='9'))
                date[j]=date[j]*10+(t-'0');
            else
                j++;
        }
        return date;
    }
    // 获取当日汇率
    private static String [] getRate(String newsUrl)
    {
        String rate[]=new String[3];
        try {
            Document doc=Jsoup.connect(newsUrl).post();
            String text=doc.text();
            rate[0]=getSingleRate(text,"美元对人民币");
            rate[1]=getSingleRate(text,"欧元对人民币");
            rate[2]=getSingleRate(text,"港元对人民币");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return rate;

    }
    private static String getSingleRate(String text,String country)
    {
        int index=text.indexOf(country);
        boolean flag=true;
        float d= 10;
        float rate=0;
        for(int i=index+country.length();;i++)
        {
            char t=text.charAt(i);
            if(t<='9'&&t>='0'&&flag)
                rate=rate*10+(t-'0');
            else if(t<='9'&&t>='0'&&(!flag)) {
                rate = rate + (t - '0')/d;
                d=d*10;
            }
            else if ('.'==t)
                flag=false;
            else
                break;

        }
        rate=1/rate;
        String result=String.format("%.4f",rate);
        return result;
    }
    private static  void writeExcel(List<String> excelData, String excelName) throws IOException, WriteException {
        File file=new File(excelName);
        WritableWorkbook workbook = Workbook.createWorkbook(file);
        WritableSheet sheet=workbook.createSheet("第一页",0);

        int i=0;
        for(String tmp :excelData)
        {
            jxl.write.Label label = new jxl.write.Label(i%4, i/4, tmp);
            sheet.addCell(label);
            i++;
        }

        workbook.write();
        workbook.close();
    }

}