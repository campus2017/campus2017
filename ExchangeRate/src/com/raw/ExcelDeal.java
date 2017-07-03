package com.raw;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by chenli on 2017/3/9.
 */
public class ExcelDeal{
    HashMap<String, ArrayList<NationRate>> result = new HashMap<String, ArrayList<NationRate>>();
    public String excelName = null;
    public ExcelDeal(HashMap<String, ArrayList<NationRate>> result, String excelName){
        this.result = result;
        this.excelName = excelName;
    }
    public void readToExcel(){
        try{
            FileOutputStream output = new FileOutputStream(excelName);
            WritableWorkbook workbook = Workbook.createWorkbook(output);
            WritableSheet sheet = workbook.createSheet("rates", 0);
            Iterator it = result.entrySet().iterator();
            int col = 1;
            while(it.hasNext()){       //3个国家
                Map.Entry<String, ArrayList<NationRate>> map = (Map.Entry<String, ArrayList<NationRate>>)it.next();
                ArrayList<NationRate> oneNation = map.getValue();
                Label one = new Label(col, 0, map.getKey());
                sheet.addCell(one);
                for(int row=oneNation.size(); row>0; row--){    //日期
                    if(col == 1){
                         Label rate = new Label(0, row, oneNation.get(row-1).getDate());
                        sheet.addCell(rate);
                    }
                    Label rate = new Label(col, row, oneNation.get(row-1).getRate());
                    sheet.addCell(rate);
                }
                col++;
            }
            workbook.write();
            workbook.close();
            output.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}