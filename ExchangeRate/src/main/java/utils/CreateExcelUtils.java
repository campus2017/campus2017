package utils;

import bean.RateData;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.Region;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by libo on 2017/6/12.
 *
 * 根据给定的数据生成关于汇率的excel表
 */
public class CreateExcelUtils {

    private static final String[] excelHeader = {"日期", "美元", "欧元", "港币"};

    public static boolean createExcelFile(String path, List<RateData> data){
        //创建workbook
        HSSFWorkbook wb = new HSSFWorkbook();

        //创建sheet
        HSSFSheet sheet = wb.createSheet("人民币汇率中间价");

        //创建行row:添加表头0行
        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);                //居中

        //创建标题
        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 3));  //合并单元格
        HSSFCell cell = row.createCell(0);                      //第一个单元格
        cell.setCellValue("人民币汇率中间价（单位：人民币/100外币）");       //设定标题
        cell.setCellStyle(style);                                       //内容居中

        //设置表头header
        row = sheet.createRow(1);
        for (int i=0; i<excelHeader.length; ++i){
            HSSFCell headerCell = row.createCell(i);
            headerCell.setCellValue(excelHeader[i]);                          //设定标题
            headerCell.setCellStyle(style);                     //内容居中
        }

        //插入数据
        for (int j=0; j<data.size(); ++j){
            RateData rowData = data.get(j);
            row = sheet.createRow(j+2);
            row.createCell(0).setCellValue(rowData.getDate());
            row.createCell(1).setCellValue(rowData.getDollar());
            row.createCell(2).setCellValue(rowData.getEuro());
            row.createCell(3).setCellValue(rowData.getHkDollar());
        }

        //保存文件
        try {
            FileOutputStream fout = new FileOutputStream(path);
            wb.write(fout);
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
