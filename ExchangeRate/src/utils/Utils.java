package utils;

import crawler.ExchangeRateCrawlerData;
import crawler.ExchangeRateCrawlerRule;
import org.apache.poi.hssf.usermodel.*;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/2/16.
 */
public class Utils {
    public static boolean IsEmpty(String str){
        if(str == null || str.trim().length() == 0){
            return true;
        }
        return false;
    }

    public static boolean IsRightPath(String str){
        if(str == null || str.trim().length() == 0){
            return false;
        }
        int len = str.length();

        if(len < 4 || !str.endsWith(".xls")){
            return false;
        }

        return true;
    }

    public static String GetNum(String str){
        if(str == null || str.length() == 0){
            return "0";
        }

        int len = str.length();
        String ret = "";
        char tmp = ' ';

        for(int i=0;i<len;i++){
            tmp = str.charAt(i);

            if((tmp>='0'&&tmp<='9') || tmp == '.'){
                ret += tmp;
            }
            else{
                break;
            }
        }

        return ret == "" ? "0" : ret;
    }

    public static ExchangeRateCrawlerRule GetCrawlerRule(){
        //set rule
        ExchangeRateCrawlerRule rule = new ExchangeRateCrawlerRule();

        //set url
        rule.SetUrl("http://www.safe.gov.cn/AppStructured/view/project_RMBQuery.action");
        String[] params = new String[3];
        params[0] = "projectBean.startDate";
        params[1] = "projectBean.endDate";
        params[2] = "queryYN";
        rule.SetParams(params);

        //set search params and values
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(calendar.DATE, -30);  //30days ago
        Date daysAgo = calendar.getTime();

        String todayStr = df.format(today);
        String daysAgoStr = df.format(daysAgo);

        String[] values = new String[3];
        values[0] = daysAgoStr;
        values[1] = todayStr;
        values[2] = "true";
        rule.SetValues(values);

        //set tag type
        rule.SetTagType(ExchangeRateCrawlerRule.HtmlElement.ID);

        //set tag name
        rule.SetResultTagName("InfoTable");

        //set http method
        rule.SetRequestMethod(ExchangeRateCrawlerRule.HttpMethod.POST);

        return rule;
    }

    public static void ExportExcel(String path, List<ExchangeRateCrawlerData> data){
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("人民币汇率中间价");
        HSSFRow row = sheet.createRow((int) 0);
        sheet.setColumnWidth(0,5120);

        HSSFCellStyle titleStyle = wb.createCellStyle();
        HSSFCellStyle contentStyle = wb.createCellStyle();
        HSSFFont titleFont = wb.createFont();
        HSSFFont contentFont = wb.createFont();

        titleFont.setFontName("宋体");
        titleFont.setFontHeightInPoints((short) 14);
        titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        contentFont.setFontName("宋体");
        contentFont.setFontHeightInPoints((short) 10);

        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        titleStyle.setFont(titleFont);
        contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        contentStyle.setFont(contentFont);

        HSSFCell cell = row.createCell(0);
        cell.setCellValue("日期");
        cell.setCellStyle(titleStyle);

        cell = row.createCell(1);
        cell.setCellValue("美元");
        cell.setCellStyle(titleStyle);
        cell = row.createCell(2);
        cell.setCellValue("欧元");
        cell.setCellStyle(titleStyle);
        cell = row.createCell( 3);
        cell.setCellValue("港元");
        cell.setCellStyle(titleStyle);

        int size = data == null ? 0 : data.size();
        HSSFCell rowCell = null;

        for (int i = 0; i < size; i++)
        {
            ExchangeRateCrawlerData excelData = data.get(i);
            row = sheet.createRow((int) i + 1);
            rowCell = row.createCell(0);
            rowCell.setCellValue(excelData.GetDate());
            rowCell.setCellStyle(contentStyle);
            rowCell = row.createCell(1);
            rowCell.setCellValue(excelData.GetRMBToDollarRate());
            rowCell.setCellStyle(contentStyle);
            rowCell = row.createCell(2);
            rowCell.setCellValue(excelData.GetRMBToEuroRate());
            rowCell.setCellStyle(contentStyle);
            rowCell = row.createCell(3);
            rowCell.setCellValue(excelData.GetRMBToHKDollarRate());
            rowCell.setCellStyle(contentStyle);
        }

        try
        {
            FileOutputStream fExcel = new FileOutputStream(path);
            wb.write(fExcel);
            fExcel.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
