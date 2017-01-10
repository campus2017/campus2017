package com.qunar;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.File;
import java.util.List;


/**
 * Excel文件处理工具类
 * Created by WanlongMa on 2016/12/23.
 */
public class ExcelHelper {

    /**
     * 创建excel文件并写入数据
     * @param fileName
     *            文件名称
     * @param list
     *            导出内容数组
     * @param sheetName
     *            导出工作表的名称
     *            导出Excel的表头数组
     * @return
     */
    public static boolean createExcelAct(String fileName, String sheetName, List<RateBean> list) {

        String[] columns = {"序号","日期","美元","欧元","港币"};
        File file = new File(fileName);

        // 声明工作簿
        WritableWorkbook wwb;
        try {
            // 根据传进来的file对象创建可写入的Excel工作薄
            wwb = Workbook.createWorkbook(file);
            // 创建一个工作表
            WritableSheet ws = wwb.createSheet(sheetName, 0);
            // 创建单元格样式
            WritableCellFormat wcf = new WritableCellFormat();
            // 设置背景颜色
            wcf.setBackground(Colour.LIGHT_BLUE);
			// 单元格内容居中显示
            wcf.setAlignment(Alignment.CENTRE);

            // 判断一下表头数组是否有数据
            if (columns != null && columns.length > 0) {

                // 循环写入表头
                for (int i = 0; i < columns.length; i++) {
                    ws.addCell(new Label(i, 0, columns[i], wcf));
                }

                // 写入数据
                int i = 0;
                if (list != null && list.size() > 0) {
                    for (RateBean rb : list) {
                        ws.addCell(new Label(0, i + 1, (i+1) +""));
                        ws.addCell(new Label(1, i + 1, rb.getPubDate()));
                        ws.addCell(new Label(2, i + 1, rb.getRateDollar()+""));
                        ws.addCell(new Label(3, i + 1, rb.getRateEuro()+""));
                        ws.addCell(new Label(4, i + 1, rb.getRateHK()+""));
                        ++i;
                    }
                }
                // 写入Exel工作表
                wwb.write();
                // 关闭Excel工作薄对象
                wwb.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }

}
