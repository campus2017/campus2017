package org.hadyang.rate;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by AstaYang on 2016/11/13.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("请输入导出文件位置：");

        Scanner scanner = new Scanner(System.in);
        String path = scanner.next();

        File file = new File(path);
        if (file.isDirectory()) {
            file = new File(path + "人民币汇率中间价.xlsx");
        }

        ExchangeRate exchangeRate = new ExchangeRate();
        List<List<Rate>> rates = exchangeRate.getRate();

        XSSFWorkbook workbook = ExcelUtils
                .getWorkbook(new String[]{"人民币对美元汇率", "人民币对港币汇率", "人民币对欧元汇率"}, rates);

        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(String.format("文件已导出到：%s", file.getAbsolutePath()));
    }
}
