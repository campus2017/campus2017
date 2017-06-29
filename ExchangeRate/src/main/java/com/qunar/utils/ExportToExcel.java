package com.qunar.utils;

import com.qunar.entity.Rate;
import org.apache.poi.hssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by isc on 2017/1/10.
 */
public class ExportToExcel {

    public static void out(List<Rate> rateList,String path){
        // 声明一个工作薄
        HSSFWorkbook wb = new HSSFWorkbook();
        //声明一个单子并命名
        HSSFSheet sheet = wb.createSheet("汇率统计");
        //给单子名称一个长度
        sheet.setDefaultColumnWidth((short)15);
        // 生成一个样式
        HSSFCellStyle style = wb.createCellStyle();
        //创建第一行（也可以称为表头）
        HSSFRow row = sheet.createRow(0);
        //样式字体居中
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //给表头第一行一次创建单元格
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("人民币");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("美元");
        cell.setCellStyle(style);
        cell = row.createCell( (short) 2);
        cell.setCellValue("欧元");
        cell.setCellStyle(style);
        cell = row.createCell( (short) 3);
        cell.setCellValue("港元");
        cell.setCellStyle(style);
        cell = row.createCell((short) 4);
        cell.setCellValue("日期");
        cell.setCellStyle(style);

        for(int i=0;i<rateList.size();i++){
            row = sheet.createRow(i+1);
            row.createCell(0).setCellValue(100);
            row.createCell(1).setCellValue(rateList.get(i).getRate().get("美元"));
            row.createCell(2).setCellValue(rateList.get(i).getRate().get("欧元"));
            row.createCell(3).setCellValue(rateList.get(i).getRate().get("港元"));
            row.createCell(4).setCellValue(rateList.get(i).getDate().toString());
        }

        try {
            FileOutputStream out = new FileOutputStream(new File(path));
            wb.write(out);
            out.close();
            System.out.println("导出成功");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("导出失败："+e.getMessage());
        }
    }
}
