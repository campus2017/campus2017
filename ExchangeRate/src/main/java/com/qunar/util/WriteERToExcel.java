package com.qunar.util;

import com.qunar.model.ERModel;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet; //http://jexcelapi.sourceforge.net/
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 将集合中的数据写入Excel文件中
 * Created by bmi-xiaoyu on 2017/6/8.
 */
public class WriteERToExcel {
    /**
     * 创建Excel文件，并将集合数据写入文件
     * @param data 待写入的集合数据
     * @param xlsFile 目标Excel文件
     * @param sheetName 要写入的Excel文件的sheet名
     * @param sheetIndex sheet在Excel中的位置
     * @throws IOException
     * @throws WriteException
     * @throws BiffException
     */
    public static void writeERToExcel(List<ERModel> data, File xlsFile, String sheetName, int sheetIndex)
            throws IOException, WriteException, BiffException {
        WritableWorkbook workbook = Workbook.createWorkbook(xlsFile);
        //创建一个工作表
        WritableSheet sheet = workbook.createSheet(sheetName, sheetIndex);
        ERModel er = null;
        int dataLen = data.size();
        sheet.addCell(new Label(0, 0, "时间"));
        sheet.addCell(new Label(1, 0, "中间价"));
        sheet.addCell(new Label(2, 0, "汇率"));
        for (int row = 0; row < dataLen; row++) {
            er = data.get(row);
            sheet.addCell(new Label(0, row + 1, er.getPostDate()));
            sheet.addCell(new Label(1, row + 1, er.getMiddlePrice()));
            sheet.addCell(new Label(2, row + 1, er.getExchangeRate()));
        }
        workbook.write();
        workbook.close();
    }

    /**
     * 更新文件：将新数据追加到Excel中
     * @param data 待写入的集合数据
     * @param xlsFile 目标Excel文件
     * @param sheetName 要写入的Excel文件的sheet名
     * @param sheetIndex sheet在Excel中的位置
     * @throws IOException
     * @throws WriteException
     * @throws BiffException
     */
    public static void appendERToExcel(List<ERModel> data, File xlsFile, String sheetName, int sheetIndex)
            throws IOException, WriteException, BiffException {
        Workbook wb = Workbook.getWorkbook(xlsFile);
        WritableWorkbook workbook = Workbook.createWorkbook(xlsFile, wb);
        //创建一个工作表
        WritableSheet sheet = workbook.createSheet(sheetName, sheetIndex);
        ERModel er = null;
        int dataLen = data.size();
        sheet.addCell(new Label(0, 0, "时间"));
        sheet.addCell(new Label(1, 0, "中间价"));
        sheet.addCell(new Label(2, 0, "汇率"));
        for (int row = 0; row < dataLen; row++) {
            er = data.get(row);
            sheet.addCell(new Label(0, row + 1, er.getPostDate()));
            sheet.addCell(new Label(1, row + 1, er.getMiddlePrice()));
            sheet.addCell(new Label(2, row + 1, er.getExchangeRate()));
        }
        workbook.write();
        workbook.close();
    }
}
