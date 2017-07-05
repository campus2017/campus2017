package utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by dayong.gao on 2017/7/5.
 */
public class ExcelUntil {
    public static void toExcel(List<String> codeList,List<List<String>> rate,String path) {
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("Rate");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("time");
        cell.setCellStyle(style);
        for (int i = 0; i <codeList.size(); i++) {
            cell = row.createCell((short) i+1);
            cell.setCellValue(codeList.get(i));
            cell.setCellStyle(style);
        }
        for (int j = 0; j < rate.get(0).size(); j++)
        {
            row = sheet.createRow((int) j + 1);
            // 第四步，创建单元格，并设置值
            for(int k=0;k<rate.size();k++){
                    row.createCell((short) k).setCellValue(rate.get(k).get(j));
            }
        }
        try
        {
            FileOutputStream fout = new FileOutputStream(path);
            wb.write(fout);
            fout.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
