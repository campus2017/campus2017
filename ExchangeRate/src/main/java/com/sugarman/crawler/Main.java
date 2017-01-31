package com.sugarman.crawler;

import jxl.write.WriteException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Created by SugarMan on 2017/1/17.
 */
public class Main {

    public static void main(String[] args){
        System.out.println("请输入文件路径:");
        Scanner scanner = new Scanner(System.in);
        String dir = scanner.nextLine();
        String[] moneys = new String[]{"USD/CNY","EUR/CNY","HKD/CNY"};
        try {
            System.out.println("数据爬取中……");
            HashMap<String, List<RateBean>> map = RateUtils.getRateByArr(moneys);
            System.out.println("数据爬取完毕……");
            System.out.println("Excel表格存储中……");
            RateUtils.SaveXml(dir,map);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }
        System.out.println("保存路径：" + dir + "\\ExchangeRate.xls");
    }

}
