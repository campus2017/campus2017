package com.cn.edu.java;
import jxl.Workbook;
import jxl.format.VerticalAlignment;
import jxl.write.*;

import java.io.File;
import java.io.IOException;

/**
 * Created by ASUS on 2017/1/31.
 */
public class ExcelWrite {
    private String path;
    private WritableSheet sheet;
    private WritableWorkbook workbook;
    ExcelWrite(String path){
        this.path = path;
    }
    public void createExcel(){
        try{
            workbook = Workbook.createWorkbook(new File(path));//创建工作薄
            sheet = workbook.createSheet("第一页",0);//创建新的一页
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void save() throws Exception{
        workbook.write();
        workbook.close();
    }
    public void createRowCol(int row,int col){
        sheet.insertRow(row);
        sheet.insertRow(col);
    }

    public void fillLable(int row,int col,Object value){

        //创建要显示的内容，即创建一个单元格（列坐标，行坐标，内容）
        Label label = new Label(col,row,(String)value);
        try{
            WritableCellFormat cellFormat = new WritableCellFormat();//生成一个单元格样式控制对象
            cellFormat.setAlignment(jxl.format.Alignment.LEFT);//单元格中的内容水平方向靠左
            cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);//单元格中的内容垂直方向居中
            cellFormat.setWrap(true);//设置自动换行
            cellFormat.setShrinkToFit(true);
            label.setCellFormat(cellFormat);
            this.sheet.addCell(label);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
