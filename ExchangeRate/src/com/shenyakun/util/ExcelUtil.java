package com.shenyakun.util;

import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.shenyakun.pojo.Info;
/**
 * excel工具类
 * @author 
 *
 */
public class ExcelUtil {
	/**
	 * 将数据导入到excel表中
	 * @param infoList
	 * @throws Exception
	 */
	public static void insertData2Excel(List<Info> infoList,String excelLocal) throws Exception{
		System.out.println("==================正在写入数据....=========================");
		Workbook wb=new HSSFWorkbook(); // 定义一个新的工作簿
		Sheet sheet=wb.createSheet("银行间外汇市场人民币汇率中间价");  // 创建第一个Sheet页
		sheet.setDefaultColumnWidth(18);//列宽
		if(null!=infoList){
			FileOutputStream fileOut=new FileOutputStream(excelLocal);
			Row row=null;
			//标题
			Row header=sheet.createRow(0);
			header.createCell(0).setCellValue("日期");  // 给单元格设置值 日期
			header.createCell(1).setCellValue("1美元对人民币");  // 给单元格设置值 美元
			header.createCell(2).setCellValue("1欧元对人民币");  // 给单元格设置值 欧元
			header.createCell(3).setCellValue("1港元对人民币");  // 给单元格设置值 港元
			//标题字体，加粗
			Font font = wb.createFont();
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			CellStyle style = wb.createCellStyle();
			style.setFont(font);
			sheet.getRow(0).getCell(0).setCellStyle(style);
			sheet.getRow(0).getCell(1).setCellStyle(style);
			sheet.getRow(0).getCell(2).setCellStyle(style);
			sheet.getRow(0).getCell(3).setCellStyle(style);
			//数据
			for (int i = 0; i < infoList.size(); i++) {
				row=sheet.createRow(i+1); // 创建一个行
				//数据
				row.createCell(0).setCellValue(infoList.get(i).getDate());  // 给单元格设置值 日期
				row.createCell(1).setCellValue(infoList.get(i).getMy());  // 给单元格设置值 美元
				row.createCell(2).setCellValue(infoList.get(i).getOy());  // 给单元格设置值 欧元
				row.createCell(3).setCellValue(infoList.get(i).getGy());  // 给单元格设置值 港元
			}
			wb.write(fileOut);
			fileOut.close();
		}
		
	}
}
