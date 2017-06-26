
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.*;

import java.util.List;
/**
 * Created by Lee on 2017/6/26.
 */

public class Utils {
    public static XSSFWorkbook getWorkbook(String[] sheets, List<List<RateImpl>> data) {
                XSSFWorkbook workbook = new XSSFWorkbook();
        for (int i = 0; i < sheets.length; i++) {
            XSSFSheet sheet = workbook.createSheet(sheets[i]);

            XSSFCellStyle normalStyle = workbook.createCellStyle();
            normalStyle.setAlignment(HorizontalAlignment.CENTER);
            normalStyle.setWrapText(true);

            XSSFCell cell;
            XSSFRow row;

            for (int j = 0; j < data.get(i).size(); j++) {
                row = sheet.createRow(j + 1);

                cell = row.createCell(0);
                cell.setCellValue(data.get(i).get(j).date);
                cell.setCellStyle(normalStyle);

                cell = row.createCell(1);
                cell.setCellValue(data.get(i).get(j).value);
                cell.setCellStyle(normalStyle);
            }

            XSSFCellStyle topLineStyle = workbook.createCellStyle();
            topLineStyle.setAlignment(HorizontalAlignment.CENTER);
            topLineStyle.setWrapText(true);

            XSSFFont font = workbook.createFont();
            font.setBold(true);
            topLineStyle.setFont(font);

            row = sheet.createRow(0);
            cell = row.createCell(0);
            cell.setCellValue("日期");
            cell.setCellStyle(topLineStyle);


            cell = row.createCell(1);
            cell.setCellValue("汇率");
            cell.setCellStyle(topLineStyle);

            sheet.createFreezePane(0, 1, 0, 1);
        }
        return workbook;
    }
}
