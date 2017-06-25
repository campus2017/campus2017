package util;

import org.apache.poi.hssf.usermodel.*;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by Administrator on 2016/11/21.
 */
public class WriteToExcel {

    public static void writeToExcel(List<String> list,String path){
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("汇率表");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow(0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        HSSFCell cell = row.createCell(0);
        cell.setCellValue("日期");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("1美元对人民币");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("1欧元对人民币");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("1港元对人民币");
        cell.setCellStyle(style);

        //写入实体数据
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            String date = str.split(" ")[0];
            String usd = str.split(" ")[1];
            String euro = str.split(" ")[2];
            String hkd = str.split(" ")[3];

            row = sheet.createRow(i+1);
            cell = row.createCell(0);
            cell.setCellValue(date);
            cell.setCellStyle(style);

            cell = row.createCell(1);
            cell.setCellValue(usd);
            cell.setCellStyle(style);

            cell = row.createCell(2);
            cell.setCellValue(euro);
            cell.setCellStyle(style);

            cell = row.createCell(3);
            cell.setCellValue(hkd);
            cell.setCellStyle(style);
        }

        // 第六步，将文件存到指定位置
        try {
            FileOutputStream fos = new FileOutputStream(path);
            wb.write(fos);
            fos.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
