package com.zhenghan.qunar.util;

import com.google.common.base.Throwables;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.SortedSet;

/**
 * Created by Administrator on 2016/11/15.
 */
public class ExecelUtils {
    private Logger logger = LoggerFactory.getLogger(ExecelUtils.class);
    private  HSSFCellStyle defaultValueStyle;
    private  HSSFCellStyle defaultTitleStyle;
    private HSSFSheet sheet;
    private HSSFWorkbook workbook;
    /**
     *
     * @param execelName
     * @return
     * @description
     * 创建一个ExecelUtils并返回
     */
    public static ExecelUtils create(String execelName){
        return new ExecelUtils().execelCreate(execelName);
    }

    public  ExecelUtils execelCreate(String execelName){
        workbook = new HSSFWorkbook();
        sheet = workbook.createSheet(execelName);
        sheet.setDefaultRowHeight((short)300);
        defaultValueStyle = setDefaultValueStyle(workbook);
        defaultTitleStyle = setDefaultTitleStyle(workbook);
        return this;
    }

    /**
     *
     * @param coll Set<String>
     * @return
     * @description
     * 设置第一行为标题写入各个国家的名称
     * Set<String> coll是使用SortedMap得到的Set
     * 所以Set是有序的
     */
    public ExecelUtils setRowTitle(SortedSet<String> coll){
        HSSFRow rowTitle = sheet.createRow(0);
        int j =1;
        for(String value:coll) {
            sheet.setColumnWidth(j,256*20);
            HSSFCell cell2 = rowTitle.createCell(j);
            cell2.setCellStyle(defaultTitleStyle);
            cell2.setCellValue(value);
            j++;
        }
        return this;
    }

    /**
     *
     * @param row 行索引
     * @param date 对应改行汇率的日期
     * @return
     * @description
     * 创建一行同时设置这一行的第一列的值为对应汇率的日期
     */
    public HSSFRow createRow(int row,Date date){
        HSSFRow hssfRow = sheet.createRow(row);
        sheet.setColumnWidth(0,256*20);
        HSSFCell  cell = hssfRow.createCell(0);
        cell.setCellValue(DateUtils.dateToString(date));
        cell.setCellStyle(defaultValueStyle);
        return hssfRow;
    }

    /**
     *
     * @param filename
     * @throws FileNotFoundException
     * @description
     * 导出execel文件如果文件路径没有被找到那么将会抛出异常
     */
    public void exportExecelFile(String filename) throws FileNotFoundException {
        OutputStream out= null;
        try {
            out= new FileOutputStream(new File(filename));
            workbook.write(out);
        } catch (IOException e) {
            Throwables.propagateIfInstanceOf(e,FileNotFoundException.class);
            logger.error(e.getMessage());
        }finally {
            if(out!=null)
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    /**
     *
     * @param row HSSFROW 需要在该行上创建列
     * @param col 需要创建的列的索引值
     * @param rate 该行列上的汇率值
     * @return
     * @description
     * 该函数为一个指定行创建一个列然后设置value
     */
    public ExecelUtils setRowValue(HSSFRow row,int col, BigDecimal rate){
        HSSFCell cell= row.createCell(col);
        cell.setCellValue(rate.toString());
        cell.setCellStyle(defaultValueStyle);
        return this;
    }

    /**
     *
     * @param wb  HSSFWorkbook
     * @return HSSFCellStyle
     * @description
     * 设置cell中值的样式返回一个样式
     */
    private  HSSFCellStyle  setDefaultValueStyle(HSSFWorkbook wb){
        HSSFCellStyle style = wb.createCellStyle(); // 样式对象
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
        Font font = wb.createFont();
        font.setFontHeightInPoints((short)12);
        font.setFontName("Courier New");
        style.setFont(font);
        return style;
    }

    /**
     *
     * @param wb HSSFWorkbook
     * @return HSSFCellStyle
     * @description
     * 设置execel列表的标题样式返回HSSFCellStyle
     */
    private  HSSFCellStyle setDefaultTitleStyle(HSSFWorkbook wb){
        HSSFCellStyle style = wb.createCellStyle(); // 样式对象
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
        style.setBorderBottom((short) 10);
        style.setBorderLeft((short) 10);
        style.setBorderRight((short) 10);
        style.setBorderTop((short) 10);
        //设置标题字体格式
        Font font = wb.createFont();
        //设置字体样式
        font.setItalic(true);
        font.setFontHeightInPoints((short)16);   //--->设置字体大小
        font.setFontName("Courier New");   //---》设置字体，是什么类型例如：宋体
        style.setFont(font);
        return style;
    }
}
