
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import qunar.fury.ExchangeRate;
public class ExchangeRate {

    public static Document getDataByJsoup(String url) {
        Document doc2 = null;
        try {
            doc2 = Jsoup.connect(url).timeout(5000).get();
            String title = doc2.body().toString();
        } catch (SocketTimeoutException e) {
            System.out.println("Socket连接超时");
        } catch (IOException e){
            e.printStackTrace();
        }
        return doc2;
    }
    public static void getThirtyAvg() {
        double dol = 0;
        double eng = 0;
        double hon = 0;
        List<String> list = new ArrayList<String>();
        Document document = ExchangeRate
                .getDataByJsoup("http://wzdig.pbc.gov.cn:8080/dig/ui/search.action?ty=&w=false&f=&dr=true&p=1&sr=score+desc%2Cdatetime+desc&rp=&advtime=&advrange=&fq=&ext=&q=%E4%B8%AD%E5%9B%BD%E5%A4%96%E6%B1%87%E4%BA%A4%E6%98%93%E4%B8%AD%E5%BF%83%E5%8F%97%E6%9D%83%E5%85%AC%E5%B8%83%E4%BA%BA%E6%B0%91%E5%B8%81%E6%B1%87%E7%8E%87%E4%B8%AD%E9%97%B4%E4%BB%B7");
        for (int i = 1; i < 21; i = i + 2) {
            list.add(document.select("a[href]").get(i).attr("href"));
        }
        Document documentP2 = ExchangeRate
                .getDataByJsoup("http://wzdig.pbc.gov.cn:8080/dig/ui/search.action?ty=&w=false&f=&dr=true&p=2&sr=score+desc%2Cdatetime+desc&rp=&advtime=&advrange=&fq=&ext=&q=%E4%B8%AD%E5%9B%BD%E5%A4%96%E6%B1%87%E4%BA%A4%E6%98%93%E4%B8%AD%E5%BF%83%E5%8F%97%E6%9D%83%E5%85%AC%E5%B8%83%E4%BA%BA%E6%B0%91%E5%B8%81%E6%B1%87%E7%8E%87%E4%B8%AD%E9%97%B4%E4%BB%B7");
        for (int i = 1; i < 21; i = i + 2) {
            list.add(documentP2.select("a[href]").get(i).attr("href"));
        }
        Document documentP3 = ExchangeRate
                .getDataByJsoup("http://wzdig.pbc.gov.cn:8080/dig/ui/search.action?ty=&w=false&f=&dr=true&p=3&sr=score+desc%2Cdatetime+desc&rp=&advtime=&advrange=&fq=&ext=&q=%E4%B8%AD%E5%9B%BD%E5%A4%96%E6%B1%87%E4%BA%A4%E6%98%93%E4%B8%AD%E5%BF%83%E5%8F%97%E6%9D%83%E5%85%AC%E5%B8%83%E4%BA%BA%E6%B0%91%E5%B8%81%E6%B1%87%E7%8E%87%E4%B8%AD%E9%97%B4%E4%BB%B7");
        for (int i = 1; i < 7; i = i + 2) {
            list.add(documentP3.select("a[href]").get(i).attr("href"));
        }
        for (int i = 0; i < list.size(); i++) {
            String temp = ExchangeRate.getDataByJsoup(list.get(i)).select("p").get(0)
                    .html();
            String[] temparray = temp.split("，");
            String doltemp = temparray[1];
            doltemp = doltemp.split("：")[1];
            String engtemp = temparray[2];
            String hontemp = temparray[4];
            dol = dol
                    + Double.parseDouble(doltemp.substring(7,
                    doltemp.length() - 1));
            eng = eng
                    + Double.parseDouble(engtemp.substring(7,
                    engtemp.length() - 1));
            hon = hon
                    + Double.parseDouble(hontemp.substring(7,
                    hontemp.length() - 1));
        }
        DecimalFormat df = new DecimalFormat("######0.00");
        dol = dol / list.size();
        eng = eng / list.size();
        hon = hon / list.size();
        System.out.println(df.format(dol));
        System.out.println(df.format(eng));
        System.out.println(df.format(hon));
        WritableWorkbook wwb = null;
        OutputStream os = null;
        try {
            String[] title = { "1美元对人民币", "1欧元元对人民币", "1港币对人民币" };
            String filePath = "./excl/JXL.xls";
            File file = new File(filePath);
            file.createNewFile();
            os = new FileOutputStream(filePath);
            wwb = Workbook.createWorkbook(os);
            WritableSheet sheet = wwb.createSheet("sheet1", 0);
            Label label = new Label(0,0,"30天内RMB汇率中间价");
            sheet.addCell(label);
            for (int i = 0; i < title.length; i++) {
                label = new Label(1 , i , title[i]);
                sheet.addCell(label);
            }
            label = new Label ( 2, 0 , df.format(dol));
            sheet.addCell(label);
            label = new Label ( 2, 1 , df.format(eng));
            sheet.addCell(label);
            label = new Label ( 2 , 2 , df.format(hon));
            sheet.addCell(label);
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
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
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
        ExchangeRate.getThirtyAvg();
    }

