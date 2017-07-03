package com.qunar.QA;

/**
 * @author  Nicole
 * @Time  2017/7/
 * @Description  通过获取的汇率数据建立表格
 */

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.*;

public class CreateExcel{

    public static void createExcl(String[][] s1,String[][] s2,String[][] s3){

        WritableWorkbook wwb = null;
        OutputStream os = null;

        // 在目录./excl下创建excel文件：ExchangeRate.xls
        try{
            File f=new File("./excel");
            if(!f.exists()){
                f.mkdirs();
            }
            String filePath = "./excel/ExchangeRate.xls";
            File file = new File(filePath);
            file.createNewFile();
            os = new FileOutputStream(filePath);
            wwb = Workbook.createWorkbook(os);
            WritableSheet sheet = wwb.createSheet("30天内RMB汇率表", 0);

            // 表格第一列为日期
            Label label = new Label(0,0,"日期");
            sheet.addCell(label);
            for (int i = 1; i <= s1[0].length; i++) {
                label = new Label(0 , i , s1[0][i-1]);
                sheet.addCell(label);
            }

            // 表格第二列为1美元对人民币的换算
            label = new Label ( 1, 0 , "1美元对人民币");
            sheet.addCell(label);
            for (int i = 1; i <= s1[1].length; i++) {
                label = new Label(1 , i , s1[1][i-1]);
                sheet.addCell(label);
            }

            // 表格第三列为1欧元对人民币的换算
            label = new Label ( 2, 0 ,"1欧元对人民币");
            sheet.addCell(label);
            for (int i = 1; i <= s2[1].length; i++) {
                label = new Label(2 , i , s2[1][i-1]);
                sheet.addCell(label);
            }

            // 表格第四列为1港币对人民币的换算
            label = new Label ( 3 , 0 , "1港币对人民币");
            sheet.addCell(label);
            for (int i = 1; i <= s3[1].length; i++) {
                label = new Label(3 , i , s3[1][i-1]);
                sheet.addCell(label);
            }

            wwb.write();
        } catch(FileNotFoundException e){
            System.out.println("文件没找到");
        }  catch (WriteException e) {
            System.out.println("输入异常");
        }
        catch (Exception exception) {
            exception.printStackTrace();
        } finally{
            if(wwb != null)
                try {
                    wwb.close();
                } catch (WriteException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if(os != null)
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}