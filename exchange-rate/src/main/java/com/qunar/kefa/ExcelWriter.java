package com.qunar.kefa;

import com.qunar.er.vo.Line;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * Created by kefa.zhang on 2017/6/15.
 */
public class ExcelWriter {
    private static Workbook wb ;
    static {
        wb = new HSSFWorkbook();
    }
    public static boolean writeFile(String path, Map<Date,Line> data){
        boolean flag = false;
        SimpleDateFormat sdf = new SimpleDateFormat("M 月 dd 日");
        CellStyle cellStyle = wb.createCellStyle(); // 标题栏样式
        Font font = wb.createFont();
        font.setBold(true);
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER); // 居中

        try (FileOutputStream out = new FileOutputStream(path)) {
            Sheet s = wb.createSheet();
            Row r =null;
            Cell c = null;
            wb.setSheetName(0, "sheet 1");
            s.setDefaultColumnWidth(20); // 默认列宽

            int rownum=0;
            r = s.createRow(rownum ++);
            int cellnum = 0;
            c = r.createCell(cellnum++);
            c = r.createCell(cellnum++);
            c.setCellValue("一元人民币对美元");
            c.setCellStyle(cellStyle);
            c = r.createCell(cellnum++);
            c.setCellValue("一元人民币对欧元");
            c.setCellStyle(cellStyle);
            c = r.createCell(cellnum++);
            c.setCellValue("一元人民币对港元");
            c.setCellStyle(cellStyle);

            Set<Map.Entry<Date, Line>> entries = data.entrySet();
            for (Map.Entry<Date, Line> entry : entries) {
                Line line = entry.getValue();
                r = s.createRow(rownum ++);
                int cellNum = 0 ;
                c = r.createCell(cellNum++);
                c.setCellValue(sdf.format(entry.getKey()));
                c = r.createCell(cellNum++);
                c.setCellValue(cal(line.getToUS()));
                c = r.createCell(cellNum++);
                c.setCellValue(cal(line.getToEU()));
                c = r.createCell(cellNum++);
                c.setCellValue(cal(line.getToHK()));
            }

            wb.write(out);
            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    private static String cal(double num) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0000");
        return decimalFormat.format(1/num);

    }
}
