import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/6/13.
 */
public class ExchangeRate {

    private static String USDUrl = "http://www.kuaiyilicai.com/huilv/d-safe-usd.html";//美元汇率
    private static String EURUrl = "http://www.kuaiyilicai.com/huilv/d-safe-eur.html";//欧元汇率
    private static String HKDUrl = "http://www.kuaiyilicai.com/huilv/d-safe-hkd.html";//港元汇率
    private static String[] urlList = {USDUrl,EURUrl,HKDUrl};
    private static String[] currencyList = {"USD","EUR","HKD"};//currencyList与urlList对应
    private DateTime dateTo = DateTime.now();
    private DateTime dateFrom = dateTo.minusDays(30);
    DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        ExchangeRate er = new ExchangeRate();
        String dirPath = "./result";
        try {
            HashMap<String,List<ExchangeRateBean>> map = er.getData();
            er.printToExcel(map,dirPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取汇率
     * @return
     * @throws IOException
     */
    public HashMap<String,List<ExchangeRateBean>> getData() throws IOException {

        HashMap<String,List<ExchangeRateBean>> map = new HashMap<String, List<ExchangeRateBean>>();

        for (int i = 0; i < urlList.length; i++) {
            List<ExchangeRateBean> list = new ArrayList<ExchangeRateBean>();

            //获取从今天开始过去30天时间的汇率数据
            Document doc = Jsoup.connect(urlList[i])
                    .data("datefrom", dateFrom.toString(formatter))
                    .data("dateto",dateTo.toString(formatter))
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36")
                    .timeout(3000)
                    .post();

            Element infoTable = doc.getElementsByTag("table").last();//table标签
            Elements infoTr = infoTable.getElementsByTag("tr");//table标签下的tr标签
            for(int j = 1; j < infoTr.size(); j++ ) {
                Elements rTd = infoTr.get(j).getElementsByTag("td");
                list.add(new ExchangeRateBean(currencyList[i],rTd.get(0).text(),Double.parseDouble(rTd.get(1).text())));
            }

            map.put(currencyList[i],list);
        }
        return map;
    }

    /**
     * 输出为excel文件
     * @param map
     * @param dirPath
     * @throws IOException
     */
    public void printToExcel(HashMap<String,List<ExchangeRateBean>> map, String dirPath) throws IOException {

        //生成目录
        File file = new File(dirPath);
        if (!file.exists()) {
            file.mkdirs();
        }

        HSSFWorkbook hwb = new HSSFWorkbook();
        HSSFSheet sheet = hwb.createSheet("人民币近30天汇率"); //创建表格
        HSSFRow rowTitle = sheet.createRow(0); //第1行
        rowTitle.createCell(0).setCellValue("日期");
        rowTitle.createCell(1).setCellValue("美元汇率");
        rowTitle.createCell(2).setCellValue("欧元汇率");
        rowTitle.createCell(3).setCellValue("港币汇率");

        for (int row = 0; row < map.get("USD").size(); row++) {
            HSSFRow rowContent = sheet.createRow(row + 1); //第row+1行
            rowContent.createCell(0).setCellValue(map.get("USD").get(row).getDate()); //日期
            rowContent.createCell(1).setCellValue(map.get("USD").get(row).getPrice()); //美元汇率
            rowContent.createCell(2).setCellValue(map.get("EUR").get(row).getPrice()); //欧元汇率
            rowContent.createCell(3).setCellValue(map.get("HKD").get(row).getPrice()); //港币汇率
        }

        FileOutputStream fileOutputStream = new FileOutputStream(dirPath+"\\exchangeRate.xls");
        hwb.write(fileOutputStream);
        fileOutputStream.close();
    }
}
