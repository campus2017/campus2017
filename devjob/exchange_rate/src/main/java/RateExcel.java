import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.Rate;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by honglin.li on 2017/6/29.
 */
public class RateExcel {

    private Workbook workbook;
    private Sheet sheet;
    private String excel_name;
    private short cur_row = 0;

    public RateExcel(String excel_name) throws IOException {
        //创建excel工作簿
        workbook = new HSSFWorkbook();
        sheet = workbook.createSheet("new sheet");
        this.excel_name = excel_name;

    }

    public void flushExcel() throws IOException {

        System.out.println("flush");
        //创建一个文件 命名为workbook.xls
        FileOutputStream fileOut = new FileOutputStream(excel_name);
        // 把上面创建的工作簿输出到文件中
        workbook.write(fileOut);
        //关闭输出流
        fileOut.close();
    }

    public void addExcelContent(ArrayList<ArrayList<String>> content) {

        for (ArrayList<String> arrstr : content) {
            Row row = sheet.createRow(cur_row++);
            int i = 0;
            for (String v : arrstr) {
                row.createCell(i++).setCellValue(v);
            }
        }
    }

    //使用POI读入excel工作簿文件
    public static void readWorkBook() throws Exception {
    }

}
