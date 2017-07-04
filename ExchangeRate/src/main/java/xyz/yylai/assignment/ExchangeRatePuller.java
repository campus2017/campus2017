package xyz.yylai.assignment;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ExchangeRatePuller {
    private final static String url = "http://www.safe.gov.cn/AppStructured/view/project_RMBQuery.action";

    private static Document retrieveWebPage(String url, String from, String to) throws IOException {
        Document document = Jsoup.connect(url)
                .data("projectBean.startDate", from)
                .data("projectBean.endDate", to)
                .data("queryYN", "true")
                .post();
        return document;
    }
    private static LinkedList<LinkedList<String>> parseDocument(Document doc) {
        LinkedList<LinkedList<String>> table = new LinkedList<>();
        Element element = doc.getElementById("InfoTable").child(0);
        for(Element row : element.children()){
            table.add(new LinkedList<>());
            for(Element col : row.children()){
                table.getLast().add(col.text());
            }
        }
        return table;
    }
    private static void retrieveRates(String filePath, String startDate, String endDate) throws IOException {

        Document document = retrieveWebPage(url,startDate,endDate);
        LinkedList<LinkedList<String>> table = parseDocument(document);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("汇率统计");
        int rowNum = 0;
        for(LinkedList<String> row : table){
            Row sheetRow = sheet.createRow(rowNum++);
            int colNum = 0;
            for(String col : row){
                Cell cell = sheetRow.createCell(colNum++);
                cell.setCellValue(col);
            }
        }

        try(FileOutputStream fileOutputStream = new FileOutputStream(filePath)){
            workbook.write(fileOutputStream);
        }

    }
    public static void main(String[] args) throws IOException {
        ExchangeRatePuller.retrieveRates("/tmp/Rates.xlsx","2017-06-01","2017-07-01");
    }
}
