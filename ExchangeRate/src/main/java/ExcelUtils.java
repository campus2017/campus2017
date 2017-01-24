import com.google.common.collect.ArrayListMultimap;
import org.apache.poi.hssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by asus on 2016/12/28.
 */
public class ExcelUtils {
    public void exportexcel(ArrayListMultimap<String,String> data) throws IOException {
//       创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
//       创建HSSFSheet对象
        HSSFSheet sheet = wb.createSheet("人民币中间价");
        sheet.setColumnWidth(0,4500);
        sheet.setColumnWidth(1,4500);
        sheet.setColumnWidth(2,4500);
        sheet.setColumnWidth(3,4500);

        HSSFCellStyle titleStyle = wb.createCellStyle();
        HSSFCellStyle cellStyle = wb.createCellStyle();
        //        设置调色板
        HSSFPalette palette = wb.getCustomPalette();
//        设置字体样式
        HSSFFont font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short)12);
        cellStyle.setFont(font);
        titleStyle.setFont(font);
        titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        //添加绿色背景
        titleStyle.setFillForegroundColor(palette.findColor((byte)204,(byte)255,(byte)255).getIndex());

        int lengh =data.get("dates").size();
        List<String> dates = data.get("dates");
        List<String> usds = data.get("usds");
        List<String> eurs = data.get("eurs");
        List<String> hkds = data.get("hkds");

        for(int rowindex = 0;rowindex<lengh;rowindex++){
            HSSFRow row = sheet.createRow(rowindex);
            row.setHeight((short) 285);
//            创建HSSFCell对象,读入数据
            HSSFCell datecell = row.createCell(0);
            HSSFCell usdcell = row.createCell(1);
            HSSFCell eurcell = row.createCell(2);
            HSSFCell hkdcell = row.createCell(3);
            datecell.setCellValue(dates.get(rowindex));
            usdcell.setCellValue(usds.get(rowindex));
            eurcell.setCellValue(eurs.get(rowindex));
            hkdcell.setCellValue(hkds.get(rowindex));
            if (rowindex==0){
                datecell.setCellStyle(titleStyle);
                usdcell.setCellStyle(titleStyle);
                eurcell.setCellStyle(titleStyle);
                hkdcell.setCellStyle(titleStyle);
            }else {
                datecell.setCellStyle(cellStyle);
                usdcell.setCellStyle(cellStyle);
                eurcell.setCellStyle(cellStyle);
                hkdcell.setCellStyle(cellStyle);
            }
        }
//     输出Excel文件
       FileOutputStream output =new FileOutputStream("d:\\ 人民币中间价.xls");
       wb.write(output);
       output.flush();
   }
}
