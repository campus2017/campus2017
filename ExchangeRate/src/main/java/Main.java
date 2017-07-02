import bean.ExchangeStruct;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.HttpUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Main {
    public static List<ExchangeStruct> parseRowHtml(String rowHtml){
        List<ExchangeStruct> res = new ArrayList<ExchangeStruct>();
        Document document = Jsoup.parse(rowHtml);
        Elements tableList = document.getElementsByClass("table-responsive");
        Element table = tableList.get(0);
        Elements trs = table.getElementsByTag("tr") ;
        for(Element tr : trs){
            Elements tds = tr.getElementsByTag("td");
            ExchangeStruct exchangeStruct = new ExchangeStruct();

            if(tds.size() == 3) {
                exchangeStruct.setDateStr(tds.get(0).text());
                exchangeStruct.setMiddlePrice(tds.get(1).text());
                exchangeStruct.setDes(tds.get(2).text());
                res.add(exchangeStruct);
            }

        }
        return res;
    }

    public static void exportToExcel(List<ExchangeStruct> exchangeStructs, String filePath){
        Workbook wb = null;
        OutputStream out = null;
        try {
            wb = new HSSFWorkbook();
            Sheet sheet = wb.createSheet("中国人民银行外汇管理局美元历史汇率与走势图");
            sheet.setColumnWidth(0, 18 * 256);
            sheet.setColumnWidth(1, 18 * 256);
            Row r = sheet.createRow(0);
            r.createCell(0).setCellValue("日期");
            r.createCell(1).setCellValue("中间价");
            r.createCell(2).setCellValue("备注");
            for(int i = 0; i < exchangeStructs.size(); i++) {
                r = sheet.createRow(i+1);
                r.createCell(0).setCellValue(exchangeStructs.get(i).getDateStr());
                r.createCell(1).setCellValue(exchangeStructs.get(i).getMiddlePrice());
                r.createCell(2).setCellValue(exchangeStructs.get(i).getDes());
                System.out.println(exchangeStructs.get(i));
            }
            out = new FileOutputStream(filePath);
            wb.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public static void main(String[] args){
        String url = "http://www.kuaiyilicai.com/huilv/d-safe-usd.html";
        String res = HttpUtil.getRequest(url, new HashMap<String, String>());
//        System.out.println(res);
        List<ExchangeStruct> exchangeStructs = parseRowHtml(res);
        exportToExcel(exchangeStructs,"/Users/wst/Desktop/外汇管理局美元历史汇率与走势图.xls");

    }
}
