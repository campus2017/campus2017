package com.qunar.www;

import org.apache.poi.hssf.usermodel.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ZC on 2017/6/30.
 */
public class ExchangeRate {
    public static void main(String[] args) {
       if(args.length==0||"".equals(args[0])){
            System.out.println("请添加解析后存放目录参数");
            return;
        }
        //爬取数据
        Map<Date, double[]> map = getData();
        //参数为输出的目录
        File file = new File(args[0]);
        //创建excel
        createExcel(map,file);
    }
        private static Map<Date, double[]> getData() {
            //两个链接，分别是第一页和第二页
            Document doc1 = null;
            Document doc2 = null;
            try {
                doc1 = Jsoup.connect("http://www.pbc.gov.cn/zhengcehuobisi/125207/125217/125925/index.html").timeout(15000).post();
                doc2 = Jsoup.connect("http://www.pbc.gov.cn/zhengcehuobisi/125207/125217/125925/17105/index2.html").timeout(15000).post();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("连接失败："+e.getMessage().toString());
            }
            Date date = new Date();
            Date dBefore = null;
            //解析得到的map，value分别为美元，欧元，港币的汇率
            Map<Date, double[]> map = new TreeMap<>();
            SimpleDateFormat sf = new SimpleDateFormat("yyyy年M月d日");
            for (int i = 1; i < 31; i++) {
                Calendar calendar = Calendar.getInstance(); //得到日历
                calendar.setTime(date);//把当前时间赋给日历
                calendar.add(Calendar.DATE, -i);  //设置为前i天
                int weekday = calendar.get(Calendar.DAY_OF_WEEK) - 1;//判断是否为周末，因为这个里面没有周末的数据
                if (weekday == 0 || weekday == 6) {
                } else {
                    dBefore = calendar.getTime();   //得到前一天的时间
                    String dateString = sf.format(dBefore);
                    Elements eles = doc1.select("a[title=" + dateString + "中国外汇交易中心受权公布人民币汇率中间价公告]");
                    if (eles.isEmpty()) {
                        eles = doc2.select("a[title=" + dateString + "中国外汇交易中心受权公布人民币汇率中间价公告]");
                    }
                    String urlSuffix = eles.get(0).attr("href");
                    String url = "http://www.pbc.gov.cn" + urlSuffix;
                    //这一天的汇率信息网页
                    Document doc3 = null;
                    try {
                        doc3 = Jsoup.connect(url).timeout(15000).post();
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("连接失败："+e.getMessage().toString());
                    }
                    //得到这一天的汇率信息
                    Elements elems = doc3.select("div#zoom");
                    String content = elems.get(0).child(0).html();
                    // 获取到汇率
                    int index1 = content.indexOf("1美元对人民币") + 7;
                    int index2 = content.indexOf("1欧元对人民币") + 7;
                    int index3 = content.indexOf("1港元对人民币") + 7;
                    int indexa = content.indexOf("元", index1);
                    int indexb = content.indexOf("元", index2);
                    int indexc = content.indexOf("元", index3);
                    double dollar = Double.valueOf(content.substring(index1, indexa));
                    double euro = Double.valueOf(content.substring(index2, indexb));
                    double hk = Double.valueOf(content.substring(index3, indexc));
                    double[] d = new double[3];
                    d[0] = dollar;
                    d[1] = euro;
                    d[2] = hk;
                    map.put(dBefore, d);
                }
            }
            return map;
        }
        private static void createExcel(Map<Date, double[]> map,File file) {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy年M月d日");
            if(!file.exists()){
                file.mkdirs() ;
            }
            File filenew = new File(file.getAbsoluteFile()+"/ExchangeRate.xls");
            // 第一步，创建一个webbook，对应一个Excel文件
            HSSFWorkbook wb = new HSSFWorkbook();
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet sheet = wb.createSheet("table");
            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
            HSSFRow row = sheet.createRow(0);
            // 第四步，创建单元格，并设置值表头 设置表头居中
            HSSFCellStyle style = wb.createCellStyle();
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

            HSSFCell cell = row.createCell((short) 0);
            cell.setCellValue("日期");
            cell.setCellStyle(style);
            cell = row.createCell((short) 1);
            cell.setCellValue("美元汇率");
            cell.setCellStyle(style);
            cell = row.createCell((short) 2);
            cell.setCellValue("欧元汇率");
            cell.setCellStyle(style);
            cell = row.createCell((short) 3);
            cell.setCellValue("港币汇率");
            cell.setCellStyle(style);

            // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
            Set<Map.Entry<Date, double[]>> entrySet = map.entrySet();
            int i =0;
            for (Map.Entry<Date, double[]> en :entrySet
                 ) {
                row = sheet.createRow(i + 1);
                Date date = en.getKey();
                row.createCell((short) 0).setCellValue(sf.format(date));
                double[] ds = en.getValue();
                row.createCell((short) 1).setCellValue(ds[0]);
                row.createCell((short) 2).setCellValue(ds[1]);
                row.createCell((short) 3).setCellValue(ds[2]);
                i++;
            }
            // 第六步，将文件存到指定位置
            FileOutputStream fout = null;
            try
            {
                 fout = new FileOutputStream(filenew);
                wb.write(fout);
                fout.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }finally {
                try {
                    fout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
