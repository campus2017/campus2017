package com.qunar.crawer.common;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by zhuyin on 16-11-16.
 */
public class Writer2Excel {
    public static void write(Map<String,List<ContentBean>> forWrite,String path){
        WritableWorkbook writable = null;
        File file = new File(path);

        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            writable = Workbook.createWorkbook(file);
            WritableSheet sheet = writable.createSheet("test",0);
            Label date = new Label(0,0,"DATE");

            sheet.addCell(date);

            int j=1;

            for(Map.Entry<String,List<ContentBean>> entry:forWrite.entrySet()){
                String country = entry.getKey();
                List<ContentBean> data = entry.getValue();

                Label con = new Label(0,j,country);
                sheet.addCell(con);

                for(int i=0;i<data.size();i++){
                    Label dateData = new Label(i+1,0,data.get(i).getDate());
                    Label price = new Label(i+1,j,data.get(i).getPrice()+"");
                    if(j==1)
                        sheet.addCell(dateData);
                    sheet.addCell(price);
                }
                j++;
            }

            writable.write();
            writable.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
