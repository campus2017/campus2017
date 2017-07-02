package com.qunar.homework.two;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
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
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by deep on 2017/6/12.
 */
public class ExchangeRate {

    public static final String sourceURL = "http://www.safe.gov.cn/AppStructured/view/project_RMBQuery.action";

    public static final String[] countryNames = {"美元", "港元", "欧元"};

    public static void main(String[] args) {
        Date now = new Date();
        Date startDate = DateUtils.addDays(now, -30);
        ExchangeRate.GetExchangeRate(startDate, now);
    }

    /**
     * 生成中间价 Excel 文件
     *
     * @param startDate
     * @param endDate
     */
    public static void GetExchangeRate(Date startDate, Date endDate) {
        try {
            String startDay = DateFormatUtils.format(startDate, "yyyy-MM-dd");
            String endDay = DateFormatUtils.format(endDate, "yyyy-MM-dd");
            Map<String, String> params = Maps.newHashMap();
            params.put("projectBean.startDate", startDay);
            params.put("projectBean.endDate", endDay);
            params.put("queryYN", "true");
            Document document = Jsoup.connect(sourceURL)
                    .data(params)
                    .timeout(5000)
                    .post();
            LinkedHashMap<String, Map<String, String>> countryRate = ExchangeRate.ResolveDocument(document);
            ExchangeRate.CreateExcelFile(countryRate);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析抓取的 document
     *
     * @param document
     * @return
     */
    public static LinkedHashMap<String, Map<String, String>> ResolveDocument(Document document) {
        LinkedHashMap<String, Map<String, String>> result = Maps.newLinkedHashMap();
        Map<String, Integer> countryOrder = Maps.newHashMap();

        Element infoTable = document.getElementById("InfoTable");
        Elements tableHead = infoTable.getElementsByClass("table_head");
        for (int i = 0; i < tableHead.size(); i++) {
            Element next = tableHead.get(i);
            String text = next.text();
            if (StringUtils.containsAny(text, countryNames)) {
            //过滤空格
                countryOrder.put(text.replaceAll("\\u00A0", ""), i);
            }
        }
        Elements rows = infoTable.getElementsByClass("first");
        for (Element row : rows) {
            Elements tds = row.getElementsByTag("td");
            Map<String, String> oneDay = Maps.newHashMap();
            for (Map.Entry<String, Integer> entry : countryOrder.entrySet()) {
                String country = entry.getKey();
                Integer order = entry.getValue();
                oneDay.put(country, tds.get(order).text());
            }
            result.put(tds.get(0).text().trim(), oneDay);
        }
        return result;
    }

    /**
     * 根据抓取的 map 生成 xls 文件
     *
     * @param dateRate
     * @throws IOException
     */
    public static void CreateExcelFile(LinkedHashMap<String, Map<String, String>> dateRate) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        int rowNum = 0;
        HSSFRow firstRow = sheet.createRow(rowNum++);

        int columnNum = 0;
        firstRow.createCell(columnNum++);
        for (String country : countryNames) {
            HSSFCell cell = firstRow.createCell(columnNum++);
            cell.setCellValue(country);
        }

        for (String date : dateRate.keySet()) {
            columnNum = 0;
            HSSFRow row = sheet.createRow(rowNum++);
            HSSFCell cell = row.createCell(columnNum++);
            cell.setCellValue(date);

            for (String country : countryNames) {
                HSSFCell cell1 = row.createCell(columnNum++);
                Map<String, String> stringStringMap = dateRate.get(date);
                String s = stringStringMap.get(country);

                cell1.setCellValue(s);
            }
        }

        File file = new File("output.xls");
        FileOutputStream os = new FileOutputStream(file);
        workbook.write(os);
        workbook.close();
        os.close();
    }
}
