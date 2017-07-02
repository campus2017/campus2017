package com.qunar.fresh.ExchangeRate;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.DefaultHttpClientConnectionOperator;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by wupei on 2017/7/2.
 */
public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    public static String outputFile="E:\\qunar\\huilv.xls";

    public static void main(String[] args) {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://www.safe.gov.cn/AppStructured/view/project_RMBQuery.action");
        HttpResponse response = null;

        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        Date endDate = new Date();
        Date startDate = getDateBefore(new Date(),30);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        nvps.add(new BasicNameValuePair("projectBean.startDate", format.format(startDate)));
        nvps.add(new BasicNameValuePair("projectBean.endDate", format.format(endDate)));
        nvps.add(new BasicNameValuePair("queryYN", "true"));

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
            response = httpclient.execute(httpPost);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            response.getEntity().writeTo(outputStream);
            Document doc = Jsoup.parse(outputStream.toString());
            Element content = doc.getElementById("InfoTable");
            Element tbody = content.getElementsByTag("tbody").first();
            Elements trs = tbody.getElementsByTag("tr");

            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();

            int i = 0;
            for (Element tr : trs) {
                HSSFRow row = sheet.createRow((short)i);
                HSSFCell cell1 = row.createCell((short)0);
                cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell1.setCellValue(tr.child(1).text());
                HSSFCell cell2 = row.createCell((short)1);
                cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell2.setCellValue(tr.child(2).text());
                HSSFCell cell3 = row.createCell((short)2);
                cell3.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell3.setCellValue(tr.child(4).text());
                i++;
            }

            FileOutputStream fOut = new FileOutputStream(outputFile);
            workbook.write(fOut);
            fOut.flush();
            fOut.close();
            System.out.println("文件生成");
        } catch (java.io.IOException e) {
            LOGGER.error("异常, http:{}", httpPost, e);
        }
    }

    private static Date getDateBefore(Date d, int day){
        Calendar now =Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE,now.get(Calendar.DATE)-day);
        return now.getTime();
    }

}
