package ExchangeRate;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 曹 on 2017/4/24.
 * 利用htmlunit和jsoup爬取汇率数据
 */
public class ExchangeRate {
    //输出至XLS
    public static void printToXLS(String temp, String path) {
        double dol = 0;
        double eng = 0;
        double hon = 0;
        String[] temparray = temp.split("：");
        String[] doltemps = temparray[1].split("，");
        String doltemp = doltemps[0];// 1--0
        String engtemp = doltemps[1];
        String hontemp = doltemps[3];
        dol = dol
                + Double.parseDouble(doltemp.substring(7, doltemp.length() - 1));
        eng = eng
                + Double.parseDouble(engtemp.substring(7, engtemp.length() - 1));
        hon = hon
                + Double.parseDouble(hontemp.substring(7, hontemp.length() - 1));

        DecimalFormat df = new DecimalFormat("######0.00");
        System.out.println(df.format(dol));
        System.out.println(df.format(eng));
        System.out.println(df.format(hon));
        WritableWorkbook wwb = null;
        OutputStream os = null;
        try {
            String[] title = { "1美元对人民币", "1欧元元对人民币", "1港币对人民币" };
            File file = new File(path);
            file.createNewFile();
            os = new FileOutputStream(path);
            wwb = Workbook.createWorkbook(os);
            WritableSheet sheet = wwb.createSheet("sheet1", 0);
            Label label = new Label(0, 0, "30天内RMB汇率中间价");
            sheet.addCell(label);
            for (int i = 0; i < title.length; i++) {
                label = new Label(1, i, title[i]);
                sheet.addCell(label);
            }
            label = new Label(2, 0, df.format(dol));
            sheet.addCell(label);
            label = new Label(2, 1, df.format(eng));
            sheet.addCell(label);
            label = new Label(2, 2, df.format(hon));
            sheet.addCell(label);
            wwb.write();
        } catch (FileNotFoundException e) {
            System.out.println("文件没找到");
        }
         catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (wwb != null)
                try {
                    wwb.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (os != null)
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

    }
    //获取网页数据
    public static Document getDataByJsoup(String url) {

        Document doc2 = null;
        try {
            WebClient webClient = new WebClient(BrowserVersion.FIREFOX_17);
            // 设置webClient的相关参数

            webClient.setJavaScriptEnabled(true);
            webClient.setCssEnabled(false);
            webClient
                    .setAjaxController(new NicelyResynchronizingAjaxController());
            webClient.setTimeout(50000);
            webClient.setThrowExceptionOnScriptError(false);
            // 模拟浏览器打开一个目标网址
            HtmlPage rootPage = webClient.getPage(url);
            System.out.println("为了获取js执行的数据 线程开始沉睡等待");
            Thread.sleep(10000);// 注意 这步非常重要 因为页面的初始化js加载也是需要时间的 具体时间可以自己调
            // System.out.println("线程结束沉睡");
            String html = rootPage.asXml();
            // System.out.println(html);
            doc2 = Jsoup.parse(html);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Socket连接超时");
        }
        return doc2;
    }

    // 获取url的汇率数据
    public static void getALlByURL(String url, String path) {

        List<String> list = new ArrayList<String>();
        Document document = ExchangeRate.getDataByJsoup(url);
        for (int i = 0; i < 10; i = i++) {
            if (document.select("a[href]") != null)
                list.add(document.select("a[href]").get(i).attr("href"));
        }

        for (int i = 0; i < list.size(); i++) {

            if (ExchangeRate.getDataByJsoup(list.get(i)).select("p") != null) {
                String temp = ExchangeRate.getDataByJsoup(list.get(i)).select("p")
                        .get(0).html();
                printToXLS(temp, path);
            }
        }
    }
    //测试
    public static void main(String[] args) {
        ExchangeRate.getALlByURL(
                "http://www.pbc.gov.cn/zhengcehuobisi/125207/125217/125925/3306620/index.html",
                "./a.xls");
    }
}
