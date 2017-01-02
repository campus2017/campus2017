package leeyang.practice.qunar;


import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;


import java.io.File;
import java.util.List;


/**
 * 写数据到excel
 */

public class WriteExcel {
    public static void Write (List<DataRate> inputList, String path)
            throws WriteException {
        File file = new File (path);
        WritableWorkbook book;
        try {
            book = Workbook.createWorkbook(file);
            WritableSheet sheet = book.createSheet("first sheet", 0);
            sheet.addCell(new Label(0, 0, "DATE"));
            sheet.addCell(new Label(1, 0, "UAS"));
            sheet.addCell(new Label(2, 0, "EU"));
            sheet.addCell(new Label(3, 0, "HK"));
            for (int i = 0; i < inputList.size(); i++) {
                sheet.addCell(new Label(0, i + 1, inputList.get(i).getDate()));
                sheet.addCell(new Number(1, i + 1, inputList.get(i).getUsaPrice()));
                sheet.addCell(new Number(2, i + 1, inputList.get(i).getEuPrice()));
                sheet.addCell(new Number(3, i + 1, inputList.get(i).getHkPrice()));

            }
            book.write();
            book.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
