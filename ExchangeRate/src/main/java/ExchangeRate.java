import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by fxd on 2017/6/18.
 */
public class ExchangeRate {

    private static final String url  = "http://www.chinamoney.com.cn/fe-c/historyParity.do";

    /*
    解析html，采用jsoup：详细参考博客： http://www.open-open.com/jsoup/dom-navigation.htm
     */
    public  ArrayList<RateRow> getExchangeRate()
    {
        ArrayList<RateRow> RateList = new ArrayList<RateRow>();
        try {
            Map<String, String> map = new HashMap<String, String>(2);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateNowStr = sdf.format(new Date());
            System.out.println("格式化后的日期：" + dateNowStr);
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            c.add(Calendar.MONTH, -2);
            Date m = c.getTime();
            String lastmon = sdf.format(m);
            System.out.println("过去一个月："+lastmon);

            map.put("startDate", lastmon);
            map.put("endDate", dateNowStr);
            Document doc = Jsoup.connect(url)
                    .data(map)
                    .post();

            Element body = doc.body();
            Element tbody = body.getElementsByTag("tbody").last();
            Elements row = tbody.getElementsByTag("tr");
            for(int i = 1; i < row.size(); i++ ){
                Elements rTd = row.get(i).getElementsByTag("td");
                String date = rTd.get(0).getElementsByTag("div").get(0).text();
                RateRow raterow = new RateRow();
                raterow.setDate(date);
                raterow.setUsdRate(Double.parseDouble(rTd.get(1).text()));
                raterow.setEurRate(Double.parseDouble(rTd.get(2).text()));
                raterow.setHkdRate(Double.parseDouble(rTd.get(4).text()));
                RateList.add(raterow);
            }

            System.out.println("网页解析成功");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return RateList;
    }

    /*
        存储到excel中，利用POI
       Apache POI是Apache软件基金会的开放源码函式库，POI提供API给Java程序对Microsoft Office格式档案读和写的功能
     */
    public static void storeExcel (ArrayList<RateRow> RateList, String path) throws IOException
    {
        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
        }
        HSSFWorkbook wb = new HSSFWorkbook();//创建Excel工作簿对象
        HSSFSheet sheet = wb.createSheet("new sheet");//创建Excel工作表对象
        HSSFRow row = sheet.createRow(0); //创建Excel工作表的行
        row.createCell(0).setCellValue("日期");
        row.createCell(1).setCellValue("美元汇率");
        row.createCell(2).setCellValue("欧元汇率");
        row.createCell(3).setCellValue("港币汇率");

        for(int i=0; i<RateList.size(); i++)
        {
            HSSFRow row2=sheet.createRow(i);
            row2.createCell(0).setCellValue(RateList.get(i).getDate());
            row2.createCell(1).setCellValue(RateList.get(i).getUsdRate());
            row2.createCell(2).setCellValue(RateList.get(i).getEurRate());
            row2.createCell(3).setCellValue(RateList.get(i).getHkdRate());
        }
        sheet.setColumnWidth(0,15*256);


        FileOutputStream fos = new FileOutputStream(path + "\\ExchangeRate.xls");
        wb.write(fos);
        fos.close();

    }
    public static void main(String[] args)
    {
        ExchangeRate er = new ExchangeRate();
        System.out.print("请输入打印路径:");
        Scanner in = new Scanner(System.in);
        String path = in.nextLine();
        try {
            ArrayList<RateRow> list = er.getExchangeRate();
            er.storeExcel(list, path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("打印成功");

    }

    private class RateRow
    {
        private String date;
        private double UsdRate;
        private double EurRate;
        private double HkdRate;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public double getUsdRate() {
            return UsdRate;
        }

        public void setUsdRate(double usdRate) {
            UsdRate = usdRate;
        }

        public double getEurRate() {
            return EurRate;
        }

        public void setEurRate(double eurRate) {
            EurRate = eurRate;
        }

        public double getHkdRate() {
            return HkdRate;
        }

        public void setHkdRate(double hkdRate) {
            HkdRate = hkdRate;
        }
    }
}
