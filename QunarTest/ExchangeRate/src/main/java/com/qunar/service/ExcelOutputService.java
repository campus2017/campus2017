package com.qunar.service;

import com.qunar.meta.ExchangeRate;
import jxl.Workbook;
import jxl.write.*;
import jxl.write.Number;
import jxl.write.biff.RowsExceededException;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Excel输出服务
 * Created by 张竣伟 on 2017/1/3.
 */
public class ExcelOutputService {

    public static void output(Map<String, List<ExchangeRate>> exchangeRateMap, String outputFile) {
        try {
            File output = new File(outputFile);
            if (output.exists()) {
                output.delete();
            }
            WritableWorkbook wwb = Workbook.createWorkbook(output);
            WritableSheet sheet = wwb.createSheet("ExchangeRate", 0);

            Set<String> keyset = exchangeRateMap.keySet();
            int i = 0;
            int j = 0;
            for (String key : keyset) {
                j++;
                Label title = new Label(j, 0, key);
                sheet.addCell(title);
                List<ExchangeRate> rates = exchangeRateMap.get(key);
                for (int k = 0; k < rates.size(); k++) {
                    jxl.write.Number rate = new Number(j, k + 1, rates.get(k).getRate());
                    sheet.addCell(rate);
                    if (i == 0) {
                        Label date = new Label(0, k + 1, rates.get(k).getDate());
                        sheet.addCell(date);
                    }
                }
                i++;
            }

            wwb.write();
            wwb.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (RowsExceededException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }

}
