/**
 * Created by luvslu on 2017/1/3.
 */
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class ExcelWrite {
    public static void main(String[] args){
        Map<String, String[]> rates = new HashMap<>();
        String[] r = new String[3];
        r[0] = "123.433";
        r[1] = "13.433";
        r[2] = "1.33";
        rates.put("2017-01-01",r);
        rates.put("2017-01-02",r);
        rates.put("2017-01-03",r);
        rates.put("2017-01-04",r);

        excelExp(".//result.xls", rates);
    }

    public static void excelExp(String filePath, Map<String, String[]> rates){
        Workbook wb = null;
        OutputStream out = null;
        try{
            wb = new HSSFWorkbook();
            Sheet sheet = wb.createSheet("exchangeRate");
            sheet.setColumnWidth(0, 18 * 256);
            sheet.setColumnWidth(1, 15 * 256);
            sheet.setColumnWidth(2, 15 * 256);
            sheet.setColumnWidth(3, 15 * 256);
            Row r = sheet.createRow(0);
            r.createCell(0).setCellValue("日期");
            r.createCell(1).setCellValue("美元");
            r.createCell(2).setCellValue("欧元");
            r.createCell(3).setCellValue("港元");
            out = new FileOutputStream(filePath);

            //写入30天的货币中间价
            int i = 1;
            for(Map.Entry<String, String[]> entry: rates.entrySet()){
                Row temp = sheet.createRow(i);
                temp.createCell(0).setCellValue(entry.getKey());
                int j = 1;
                for (String item: entry.getValue()) {
                    temp.createCell(j++).setCellValue(item);
                }
                i = i + 1;
            }
            wb.write(out);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                out.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
