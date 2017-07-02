package com.java;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by apple on 17/7/2.
 */
public class FileOperate {
    public  static  boolean WriteFile(Vector<exchangeRateData> rate)
    {
        HSSFWorkbook wb = new HSSFWorkbook();

        HSSFSheet sheet=wb.createSheet("汇率表");

        HSSFRow row1=sheet.createRow(0);

        HSSFCell cell=row1.createCell(0);

        cell.setCellValue("汇率辩护情况表");

        sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));

        HSSFRow row2=sheet.createRow(1);
        row2.createCell(0).setCellValue("时间");
        row2.createCell(1).setCellValue("美元");
        row2.createCell(2).setCellValue("欧元");
        row2.createCell(3).setCellValue("港元");

        for(int i=0;i<rate.size();i++)
        {
            HSSFRow row3=sheet.createRow(2+i);
            exchangeRateData temp=rate.get(i);
            row3.createCell(0).setCellValue(temp.getDate());
            row3.createCell(1).setCellValue(temp.getDollar());
            row3.createCell(2).setCellValue(temp.getEuro());
            row3.createCell(3).setCellValue(temp.getHkd());
        }

        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream("result/汇率.xls");
            wb.write(fileOut);
            fileOut.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return  false;
        }

        return true;

    }
}
