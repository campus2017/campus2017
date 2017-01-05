import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by woo on 1/5.
 */
public class ExcelPipeline implements Pipeline {

    private static List<String> dates = new ArrayList<String>();
    private static List<String> usdRates = new ArrayList<String>();
    private static List<String> eurRates = new ArrayList<String>();
    private static List<String> hkdRates = new ArrayList<String>();

    @Override
    public void process(ResultItems resultItems, Task task) {
        FileOutputStream fileOut = null;
        Workbook wb = null;
        try {
            wb = new XSSFWorkbook();
            Sheet sheet = wb.createSheet("rate");

            Row rowDate = sheet.createRow(0);
            rowDate.createCell(0).setCellValue("Date");
            Row rowUsd = sheet.createRow(1);
            rowUsd.createCell(0).setCellValue("Usd");
            Row rowEur = sheet.createRow(2);
            rowEur.createCell(0).setCellValue("Eur");
            Row rowHkd = sheet.createRow(3);
            rowHkd.createCell(0).setCellValue("Hkd");

            fileOut = new FileOutputStream("Rate.xlsx");
            wb.write(fileOut);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                fileOut.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
            String tempStr = entry.getValue().toString();
            tempStr = tempStr.substring(1, tempStr.length() - 1);
            String key = entry.getKey();
            String[] arr = tempStr.split(",");
            int i = 1;
            for (String str : arr) {
                if (key.equals("date")) {
                    dates.add(str);
                } else if (key.equals("rateUsd")) {
                    usdRates.add(str);
                } else if (key.equals("rateEur")) {
                    eurRates.add(str);
                } else if (key.equals("rateHkd")) {
                    hkdRates.add(str);
                }
            }
        }

        Sheet sheet = wb.getSheet("rate");

        Row rowDate = sheet.getRow(0);
        int i = 1;
        for (String str : dates) {
            rowDate.createCell(i).setCellValue(str);
            ++i;
        }

        Row rowUsd = sheet.getRow(1);
        i = 1;
        for (String str : usdRates) {
            rowUsd.createCell(i).setCellValue(str);
            ++i;
        }

        Row rowEur = sheet.getRow(2);
       i = 1;
        for (String str : eurRates) {
            rowEur.createCell(i).setCellValue(str);
            ++i;
        }

        Row rowHkd = sheet.getRow(3);
        i = 1;
        for (String str : hkdRates) {
            rowHkd.createCell(i).setCellValue(str);
            ++i;
        }

        try {
            fileOut = new FileOutputStream("Rate.xlsx");
            wb.write(fileOut);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}