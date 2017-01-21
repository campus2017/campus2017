package ExchangeRate;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 功能：写数据到excel表
 * Created by yangyening on 2016/12/27.
 */
public class WriteDataToExcel {
    public static void write(List<RateBean> rateBeanList, String path){
        WritableWorkbook writable = null;
        File file = new File(path);
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            writable = Workbook.createWorkbook(file);
            WritableSheet sheet = writable.createSheet("ExchangeRate",0);//创建可写入的工作表
            sheet.setColumnView(0,12);
            String[] headData=new String[]{"DATE","USD/CNY","EUR/CNY","HKD/CNY"};
            for(int i=0;i<headData.length;i++){
                Label label=new Label(i,0,headData[i]);
                sheet.addCell(label);
            }
            for(int i=0;i<rateBeanList.size();i++){
                Label contentDate = new Label(0,i+1,rateBeanList.get(i).getDate());
                Label contentUsd = new Label(1,i+1,rateBeanList.get(i).getUsdPrice()+"");
                Label contentEur = new Label(2,i+1,rateBeanList.get(i).getEurPrice()+"");
                Label contentHkd = new Label(3,i+1,rateBeanList.get(i).getHkdPrice()+"");
                sheet.addCell(contentDate);
                sheet.addCell(contentUsd);
                sheet.addCell(contentEur);
                sheet.addCell(contentHkd);
            }

            writable.write();
            writable.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
