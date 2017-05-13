package com.qunar.util;

import com.qunar.vo.RateVo;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by DELL on 2017/5/2.
 * Description:将list中保存的汇率信息输出到excel文件中
 */
public class ExcelUtil {
	//static Logger logger = Logger.getLogger(ExcelUtil.class);
	public static void OutPutToExcel(List<RateVo> list) throws IOException {
		//logger.info("开始写入excel文件");
		//第一步：创建一个webbook，对应一个excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		//第二步：在excel表中创建一个sheet
		HSSFSheet sheet = wb.createSheet("人民币汇率中间价");
		//第四步：在sheet中添加第一行，作为表头
		HSSFRow row = sheet.createRow(0);
		//第五步：设置一种单元格格式，水平居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		//第六步：创建单元格，表头信息：日期，美元，欧元，港币
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("日期");
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue("美元");
		cell.setCellStyle(style);
		cell = row.createCell(2);
		cell.setCellValue("欧元");
		cell.setCellStyle(style);
		cell = row.createCell(3);
		cell.setCellValue("港币");
		cell.setCellStyle(style);
		//向表中添加内容
		for(int i=0;i<list.size();i++){
			row = sheet.createRow(i+1);
			RateVo rv = list.get(i);
			row.createCell(0).setCellValue(rv.getDate());
			row.createCell(1).setCellValue(rv.getDollar());
			row.createCell(2).setCellValue(rv.getEuro());
			row.createCell(3).setCellValue(rv.getHkdollar());
		}

		try {
			FileOutputStream fileOutputStream = new FileOutputStream("OutPutFileFolder\\人民币汇率中间价.xls");
			//if(fileOutputStream.)
			wb.write(fileOutputStream);
			//logger.info("写入excel文件完成");
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
