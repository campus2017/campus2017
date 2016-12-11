package ExportToExcel;

import org.apache.poi.hssf.usermodel.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * function:将保存的中间价导出到excel中。
 * Created by Wang Yishu on 2016/12/11.
 */
public class ExportToExcel {
    public void outPut(List<String> titles,Map<String, Map<String,String>> records){

        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("人民币汇率中间价");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        int colNum = titles.size();
        HSSFCell cell;
        for (int i = 0; i < colNum; i++) {
            cell= row.createCell(i);
            cell.setCellValue(titles.get(i));
            cell.setCellStyle(style);
        }
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
        int rowNum = 1;
        for (String key : records.keySet()) {
            row = sheet.createRow(rowNum);

                row.createCell(0).setCellValue(key);
                for (int i = 1; i < colNum; i++) {
                    row.createCell(i).setCellValue(records.get(key).get(titles.get(i)));
                }
                rowNum++;
        }
        // 第六步，将文件存到指定位置
        FileOutputStream fout=null;
        try
        {
            fout= new FileOutputStream("E:/"+"人民币汇率中间价"+".xls");
            wb.write(fout);
            System.out.println("导出完成");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
