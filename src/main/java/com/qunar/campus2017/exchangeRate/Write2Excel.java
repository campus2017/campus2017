package com.qunar.campus2017.exchangeRate;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by chunming.xcm on 2017/1/10.
 */
public class Write2Excel {
    /**
     * 写表格方法
     * @param content key:货币, value:内容
     * @param filePath src/main/resources/人民币汇率中间价.xls
     */
    public static void write(Map<String, List<ExchangeRateBean>> content, String filePath) {
        File file = new File(filePath);
        try {
            if(file.exists()) {
                file.delete();
            }
            file.createNewFile();
            WritableWorkbook writableWorkbook = Workbook.createWorkbook(file);
            WritableSheet writableSheet = writableWorkbook.createSheet("汇率中间价", 0);
            try {
                Label date = new Label(0, 0, "日期");
                writableSheet.addCell(date);
                int i = 1;
                for(Map.Entry<String, List<ExchangeRateBean>> entry : content.entrySet()) {
                    //加入货币
                    Label ccy = new Label(i, 0, entry.getKey());
                    writableSheet.addCell(ccy);
                    //加入内容
                    List<ExchangeRateBean> exchangeRateBeans = entry.getValue();
                    int len = exchangeRateBeans.size();
                    for(int j = 1; j < len + 1; j++) {
                        Label lDate = new Label(0, j, exchangeRateBeans.get(j - 1).getDate());
                        Label lValue = new Label(i, j, exchangeRateBeans.get(j - 1).getValue());
                        if("".equals(writableSheet.getCell(0, j).getContents())) {
                            writableSheet.addCell(lDate);
                        }
                        writableSheet.addCell(lValue);
                    }
                    i++;
                }
                writableWorkbook.write();
                writableWorkbook.close();
            } catch (WriteException e){
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
