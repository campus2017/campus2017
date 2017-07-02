import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by kefa.zhang on 2017/6/15.
 */
public class ET {
    public static void main(String[] args) throws IOException {


        FileOutputStream out = new FileOutputStream("workbook.xls");
        Workbook wb = new HSSFWorkbook();
        Sheet s = wb.createSheet();
        Row r = null;
        Cell c = null;
        wb.setSheetName(0, "sheet 1");

        int rownum;
        for (rownum =  0; rownum < 10; rownum++) {
            r = s.createRow(rownum);
            for (short cellnum = 0; cellnum < 10; cellnum ++) {
                c = r.createCell(cellnum);
                c.setCellValue(rownum+":"+cellnum);

            }
        }
        wb.write(out);
        out.close();

    }
}
