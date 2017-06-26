
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
/**
 * Created by Lee on 2017/6/26.
 */
public class Excute {
    public static void main(String[] args) {
        File  file = new File( "D:\\QUNAR\\rmbrates.xlsx");
        Rate exchangeRate = new Rate();
        List<List<RateImpl>> rates = exchangeRate.getRate();

        XSSFWorkbook workbook = Utils
                .getWorkbook(new String[]{"人民币vs美元汇率", "人民币vs港币汇率", "人民币vs欧元汇率"}, rates);
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(String.format("结果导出地址：%s", file.getAbsolutePath()));
    }
}
