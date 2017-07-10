package com.qunar.wireless.mlx;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * Created by mlx on 2016-12-29.
 */
public class WriteToExcelPoi implements IWriteToExcel {

    public void writeACurrency(String fileName, Map<String,Map<String,String>> rates){
        // 创建一个XSSF的Excel文件
        Workbook workbook;
        workbook = new XSSFWorkbook();

        // 创建名称为 rate 的 sheet
        Sheet sheet = workbook.createSheet("rate");

        Row row;
        Cell cell;
        row = sheet.createRow(0);
        cell = row.createCell(0);
        cell.setCellValue("日期");

        int colnum = 1, rownum = 1;
        for(String currency:rates.keySet()){
            // 设置列标题
            row = sheet.getRow(0);
            cell = row.createCell(colnum);
            cell.setCellValue(currency);

            for(String date:rates.get(currency).keySet()){
                row = sheet.getRow(rownum);
                if(row==null){
                    // 新生成一行,并设置第一列为日期
                    row = sheet.createRow(rownum);
                    cell = row.createCell(0);
                    cell.setCellValue(date);
                }
                cell = row.createCell(colnum);
                cell.setCellValue(rates.get(currency).get(date));
                ++rownum;
            }
            rownum=1;
            ++colnum;
        }

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fileName);
            // 将Excel写出到文件流
            workbook.write(fos);
        } catch (FileNotFoundException e) {
            System.out.println("打开文件时发生错误:"+fileName);
        } catch (IOException e) {
            System.out.println("写入文件时发生错误:"+fileName);
        }

    }
}