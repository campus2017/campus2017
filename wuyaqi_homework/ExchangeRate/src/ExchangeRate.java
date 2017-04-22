import jxl.Workbook;
import jxl.write.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuyaqi on 17-4-8.
 */

public class ExchangeRate {

    public static Document getDataByJsoup(String url) {
        Document doc2 = null;
        try {
            doc2 = Jsoup.connect(url)
                    .data("query","Java")
                    .userAgent("I am jsoup")
                    .cookie("auth", "token")
                    .timeout(10000)
                    .post();//通过post方法获取网页内容，有的网页不能用get方法获得
        } catch (SocketTimeoutException e) {
            System.out.println("Socket连接超时");
        } catch (IOException e){
            e.printStackTrace();
        }
        return doc2;
    }
    public static List<Rate> getThirtyRatesList() {

        List<Rate> rates_list = new ArrayList<Rate>();//记录30天内的汇率总和

        List<String> hreflist = new ArrayList<String>();//记录30天的汇率的超链接

        Document document = ExchangeRate.getDataByJsoup("http://www.pbc.gov.cn/zhengcehuobisi/125207/125217/125925/17105/index1.html");
        Elements links1 = document.select("a[href]");//得到该页面的所有超链接
        //System.out.println("document a href size is :" + links1.size());
        if(links1.size()>54 && links1.size()<80)
        {
            for (int i = 54; i < 74; i++) {//从超链接中，找到需要的超链接，进行保存。在第一页中，找出20个，
                hreflist.add(links1.get(i).attr("abs:href"));//得到的是绝对网址，需要用abs:href,如果只写href，得到的是相对超链接网址
            }
        }

        Document documentP2 = ExchangeRate.getDataByJsoup("http://www.pbc.gov.cn/zhengcehuobisi/125207/125217/125925/17105/index2.html");
        Elements links2 = documentP2.select("a[href]");
        //System.out.println("document a href size is :" + links2.size());
        if(links2.size()>56 && links2.size()<80)
        {
            for (int i = 54; i < 64; i++) {//在第二页中，找出前十个。
                hreflist.add(links2.get(i).attr("abs:href"));
            }
        }

        for (int i = 0; i < hreflist.size(); i++) {

            Document doc = ExchangeRate.getDataByJsoup(hreflist.get(i));//从超链接页面中得到相应数据
            String title = doc.title();//得到日期。title为：2017年4月7日中国外汇交易中心受权公布人民币汇率中间价公告
            String date = title.split("中")[0];

            String temp = doc.select("p").get(0).html();//得到网页中的那段汇率文字

            String[] temparray = temp.split("，");
            String doltemp = temparray[1];
            doltemp = doltemp.split("：")[1].substring(7);//得到后面的钱数
            String eurtemp = temparray[2].substring(7);
            String hontemp = temparray[4].substring(7);

            rates_list.add(new Rate(date,doltemp,eurtemp,hontemp));

        }
        //最终得到rates_list为所有30天的汇率记录
        return rates_list;
    }
    public static void output_exl(List<Rate> rates_list)
    {
        WritableWorkbook wwb = null;
        OutputStream os = null;
        try {
            String[] title = { "日期","1美元对人民币", "1欧元元对人民币", "1港币对人民币" };
            wwb = Workbook.createWorkbook(new File("./excel/30天内人民币汇率中间价公告.xls"));//excl这个文件夹必须存在，文件可以不存在。
            WritableSheet sheet = wwb.createSheet("30天内RMB汇率中间价", 0);

            //设置字体格式
            WritableFont font1=new WritableFont(WritableFont.createFont("楷体 _GB2312"), 12, WritableFont.NO_BOLD);
            WritableCellFormat format1=new WritableCellFormat(font1);
            //把水平对齐方式指定为居中
            format1.setAlignment(jxl.format.Alignment.CENTRE);
            //把垂直对齐方式指定为居中
            format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
            //设置自动换行
            format1.setWrap(true);

            //确定表行，label的第一个参数为列，第二个参数为行，第三个参数为需要写的内容
            for (int i = 0; i < title.length; i++) {
                sheet.setColumnView(i, 20);//设置第i列的宽度
                sheet.addCell(new Label(i, 0 , title[i],format1));
            }
            for(int k = 0;k<rates_list.size();k++)//从30天的汇率列表中，取出数据，存到sheet中。
            {
                Rate record_temp = rates_list.get(k);
                //System.out.println(record_temp);
                sheet.addCell(new Label(0,k+1,record_temp.getDate(),format1));
                sheet.addCell(new Label(1,k+1,record_temp.getDol_rate(),format1));
                sheet.addCell(new Label(2,k+1,record_temp.getEur_rate(),format1));
                sheet.addCell(new Label(3,k+1,record_temp.getHon_rate(),format1));
            }
            wwb.write();

        } catch(FileNotFoundException e){
            System.out.println("文件没找到");
        }  catch (WriteException e) {
            System.out.println("输入异常");
        }
        catch (Exception exception) {
            exception.printStackTrace();
        } finally{
            if(wwb != null)
                try {
                    wwb.close();
                } catch (WriteException e) {
                   e.printStackTrace();
                } catch (IOException e) {
                   e.printStackTrace();
                }
            if(os != null)
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
    public static void main(String[] args) {
        List<Rate> rate_list = ExchangeRate.getThirtyRatesList();
        ExchangeRate.output_exl(rate_list);
        System.out.println("success");
    }
}

 class Rate{
    private String date;
    private String dol_rate;
    private String eur_rate;
    private String hon_rate;

     Rate(String date, String dol_rate, String eur_rate, String hon_rate) {
         this.date = date;
         this.dol_rate = dol_rate;
         this.eur_rate = eur_rate;
         this.hon_rate = hon_rate;
     }

     public String getDate() {
         return date;
     }

     public String getDol_rate() {
         return dol_rate;
     }

     public String getEur_rate() {
         return eur_rate;
     }

     public String getHon_rate() {
         return hon_rate;
     }

     public void setDate(String date) {
         this.date = date;
     }

     public void setDol_rate(String dol_rate) {
         this.dol_rate = dol_rate;
     }

     public void setEur_rate(String eur_rate) {
         this.eur_rate = eur_rate;
     }

     public void setHon_rate(String hon_rate) {
         this.hon_rate = hon_rate;
     }

     @Override
     public String toString() {
         return "Rate{" +
                 "date='" + date + '\'' +
                 ", dol_rate='" + dol_rate + '\'' +
                 ", eur_rate='" + eur_rate + '\'' +
                 ", hon_rate='" + hon_rate + '\'' +
                 '}';
     }
 }