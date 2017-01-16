package com.company;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by admin on 2016/12/23.
 */
public class ExchangeRate {
    private String url = "";
    private String html = "";
    private List<String> title = null;
    private List<List<String>> rate = null;

    public ExchangeRate(String url) {
        this.url = url;
    }

    public void openPage() {
        BufferedReader in = null;
        try {
            // 连接
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.connect();

            // 读取内容
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder("");
            for (String line = ""; (line = in.readLine()) != null; sb.append(line)) {
                line = line.trim(); // 去除空白符
                //System.out.println(line);
            }
            html = sb.toString();
            // System.out.println(html);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<List<String>> getExchangeRate() {
        rate = new ArrayList<List<String>>();
        // 匹配一行
        Pattern rowPattern = Pattern.compile("<tr>(.+?)</tr>");
        Matcher rowMatcher = rowPattern.matcher(html);
        while (rowMatcher.find()) {
            // 从行中匹配日期及汇率
            Pattern pattern = Pattern.compile(
                    "<td class=\"dreport-row[1|2]-1\" style=\"text-align: center; width:80px;\"><div style=\"text-align: center; width:80px;\">(.+?)</div>" +
                            "</td>" +
                            "<td class=\"dreport-row[1|2]\">(.+?)</td>" +
                            "<td class=\"dreport-row[1|2]\">(.+?)</td>" +
                            "<td class=\"dreport-row[1|2]\">(.+?)</td>" +
                            "<td class=\"dreport-row[1|2]\">(.+?)</td>");
            Matcher matcher = pattern.matcher(rowMatcher.group(1));
            // 记录结果
            if (matcher.find()) {
                List<String> row = new ArrayList<String>(5);
                row.add(matcher.group(1));      // 日期
                row.add(matcher.group(2));      // 汇率1 美元
                row.add(matcher.group(3));      // 汇率2 欧元
                row.add(matcher.group(5));      // 汇率4 港币

                rate.add(row);
            }
        }

        // 标题行
        title = new ArrayList<String>(5);
        title.add("date");
        title.add("USD");
        title.add("EUR");
        title.add("HKD");
        return rate;
    }

    public void save(String filename) {
        try {
            // 创建工作簿工作表
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("rate");

            // 标题行
            XSSFRow bookRow = sheet.createRow(0);
            for (int i=0; i<title.size(); ++i) {
                bookRow.createCell(i).setCellValue(title.get(i));
            }

            // 数据内容
            for (int i=0; i<rate.size(); ++i) {
                bookRow = sheet.createRow(i+1);
                List<String> row = rate.get(i);
                for (int j=0; j<row.size(); ++j) {
                    bookRow.createCell(j).setCellValue(row.get(j));
                }
            }

            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
