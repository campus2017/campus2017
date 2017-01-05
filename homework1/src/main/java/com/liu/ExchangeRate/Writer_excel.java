package com.liu.ExchangeRate;

import jxl.*;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.File;

/*
 *@author:liudan
 *@description: 生成excel工具类，传入二维数组类型的数据，生成excel表
 */
public class Writer_excel {

    public void outputExcel(String context[][]) {

        //标题行
        String title[] = {"时间", "1美元对人民币", "1欧元对人民币", "1港元对人民币"};
        //内容
       /* String context1[][]={{"2016年","6.8",""7.3,"0.89"} };*/

        //操作执行
        try {
            //t.xls为要新建的文件名
            WritableWorkbook book = Workbook.createWorkbook(new File("E:\\test\\t3.xls"));
            //生成名为“第一页”的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("第一页", 0);

            //写入内容
            for (int i = 0; i < 4; i++)  //title
                sheet.addCell(new Label(i, 0, title[i]));
            for (int i = 0; i < context.length; i++)  //context
            {
                for (int j = 0; j < 4; j++) {
                    sheet.addCell(new Label(j, i + 1, context[i][j]));
                }
            }

            //写入数据
            book.write();
            //关闭文件
            book.close();
            System.out.println("finish");
        } catch (Exception e) {
        }
    }
}