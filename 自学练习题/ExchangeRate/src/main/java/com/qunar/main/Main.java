package com.qunar.main;

import com.qunar.model.ERModel;
import com.qunar.parse.ERParse;
import com.qunar.util.ERUtils;
import com.qunar.util.WriteERToExcel;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 调用函数，分析HTML页面并将数据写入Excel文件中
 * Created by bmi-xiaoyu on 2017/6/8.
 */
public class Main {
    private  static List<ERModel> dollarER = new ArrayList<ERModel>();
    private  static List<ERModel> HKDER = new ArrayList<ERModel>();
    private  static List<ERModel> euroER = new ArrayList<ERModel>();

    public static void main(String[] args) throws IOException, WriteException, BiffException {
        //获取当天及30天前的日期
        String today = ERUtils.getToday();
        String dayBefore30 = ERUtils.getDayBefore30();

        File xlsFile = new File("最近30天人民币汇率.xls");
        if (!xlsFile.exists()) {
            xlsFile.createNewFile();
        }

        //解析并提取网页信息
        dollarER = ERParse.erParse("http://www.kuaiyilicai.com/huilv/d-boc-usd.html",
                "datefrom=" + dayBefore30 + "&dateto=" + today);
        HKDER = ERParse.erParse("http://www.kuaiyilicai.com/huilv/d-boc-hkd.html",
                "datefrom=" + dayBefore30 + "&dateto=" + today);
        euroER = ERParse.erParse("http://www.kuaiyilicai.com/huilv/d-boc-eur.html",
                "datefrom=" + dayBefore30 + "&dateto=" + today);

        //将信息写入到Excel文件
        try {
            WriteERToExcel.writeERToExcel(dollarER, xlsFile, "美元", 0);
            WriteERToExcel.appendERToExcel(euroER, xlsFile, "欧元", 1);
            WriteERToExcel.appendERToExcel(HKDER, xlsFile, "港币", 2);
        } catch (IOException e) {
            System.out.println("写入文件时发生异常：");
            e.printStackTrace();
        } catch (WriteException e) {
            System.out.println("写入文件时发生异常：");
            e.printStackTrace();
        }
    }
}
