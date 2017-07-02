package top.mineor;

/**
 * Created by Mineor on 2017/6/29.
 */

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ExcelUtils {

    /**
     * 导出到excel表格
     * @param titles 标题行
     * @param records 每行记录
     * @param excelName sheet名
     */
    public static void exportToExcel(List<String> titles,Map<String, Map<String, String>> records,String excelName) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        HSSFSheet sheet = workbook.createSheet(excelName);
        HSSFRow row = sheet.createRow(0);
        int colNum = titles.size();
        for (int i = 0; i < colNum; i++) {
            row.createCell(i).setCellValue(titles.get(i));
        }
        int rowNum = 1;
        for (String key : records.keySet()) {
            row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(key);
            for (int i = 1; i < colNum; i++) {
                row.createCell(i).setCellValue(records.get(key).get(titles.get(i)));
            }
            rowNum++;
        }

        String fileName = excelName + "_" + dateFormat.format(new Date())+".xls";
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(new File("./" + fileName));
            workbook.write(out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}