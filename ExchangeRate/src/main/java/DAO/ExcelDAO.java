package DAO;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellType;

import java.io.*;

/**
 * Created by Leon on 2017/4/24.
 */
public class ExcelDAO {

    private HSSFWorkbook mWorkBook = new HSSFWorkbook();


    private HSSFSheet getOrCreateSheet(HSSFWorkbook workbook, String sheetName) {
        HSSFSheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            sheet = workbook.createSheet(sheetName);
        }
        return sheet;
    }

    private HSSFRow getOrCreateRow(HSSFSheet sheet, int i) {
        HSSFRow row = sheet.getRow(i);
        if(row == null) {
            row = sheet.createRow(i);
        }
        return row;
    }

    public void setVal(String sheetName, int iRow, int iCol, String val) {
        HSSFSheet sheet = getOrCreateSheet(mWorkBook, sheetName);
        HSSFRow row = getOrCreateRow(sheet, iRow);
        HSSFCell cell = row.createCell(iCol, CellType.NUMERIC);
        cell.setCellValue(val);
    }

    public void setVal(String sheetName, int iRow, int iCol, Double val) {
        HSSFSheet sheet = getOrCreateSheet(mWorkBook, sheetName);
        HSSFRow row = getOrCreateRow(sheet, iRow);
        HSSFCell cell = row.createCell(iCol, CellType.STRING);
        cell.setCellValue(val);
    }


    public void exportToExcelFile(String filePath) {


        File file = new File(filePath);
        File dir = file.getParentFile();
        if(!dir.exists()) {
            dir.mkdirs();
        }

        try (FileOutputStream fw = new FileOutputStream(filePath);
             BufferedOutputStream bw = new BufferedOutputStream(fw)) {

            mWorkBook.write(bw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
