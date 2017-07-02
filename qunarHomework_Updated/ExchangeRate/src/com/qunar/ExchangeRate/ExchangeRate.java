package com.qunar.ExchangeRate;

import java.io.IOException;
import java.io.FileOutputStream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * @author LiuYang[QA]
 */

public class ExchangeRate  {
    static int i=1;

    /**
     * @param date the date of exchange rate
     * @param dollar the exchange rate of US Dollar to China Yuan
     * @param euro the exchange rate of Euro to China Yuan
     * @param hkd the exchange rate of HK Dollar to China Yuan
     **/
    public static void excelOutput(String date,String dollar,String euro,String hkd,HSSFSheet sheet,
                                   HSSFWorkbook workbook,FileOutputStream out)throws IOException{

        HSSFRow row1 = sheet.createRow(i);                      //创建行,从0开始
        HSSFCell cell1 = row1.createCell(0);           //创建行的单元格,也是从0开始
        cell1.setCellValue(date);                               //设置单元格内容
        row1.createCell(1).setCellValue(dollar);       //设置单元格内容,重载
        row1.createCell(2).setCellValue(euro);         //设置单元格内容,重载
        row1.createCell(3).setCellValue(hkd);          //设置单元格内容,重载
        System.out.println("OK！第 "+i+" 个数据获取成功" );
        i++;
    }

    /**
     * @param s a part of URL, which will be connected with the other of URL to reach to the specified web page to get the data we need
     * */
    public static void readContent(String s,HSSFSheet sheet,HSSFWorkbook workbook,FileOutputStream out) throws IOException{
        String str,str1,str2,str3,str4;
        System.getProperties().setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        str="http://www.pbc.gov.cn"+s;
        webDriver.get(str);
        WebElement webElement = webDriver.findElement(By.xpath("//*[@id=\"zoom\"]/p[1]"));

        str =webElement.getText();
        str1=str.substring(str.indexOf("布")+2,str.indexOf("日")+1);
        str2=str.substring(str.indexOf("美")+6,str.indexOf("元",str.indexOf("元")+1));
        str3=str.substring(str.indexOf("欧")+6,str.indexOf("欧")+12);
        str4=str.substring(str.indexOf("港")+6,str.indexOf("港")+13);
        excelOutput(str1,str2,str3,str4,sheet,workbook,out);
        webDriver.close();
    }

    public static void main(String[] args) throws IOException{
        String str;
        System.getProperties().setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();

        String filePath="C:\\ExchangeRate.xls";
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("ExchangeRate");
        FileOutputStream out = new FileOutputStream(filePath);

        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("日期");
        row.createCell(1).setCellValue("1美元对人民币");
        row.createCell(2).setCellValue("1欧元对人民币");
        row.createCell(3).setCellValue("1港元对人民币");

        System.out.println("正在爬取数据，请稍等......");
        System.out.println("[ 文件将保存至："+filePath+" ]");

        webDriver.get("http://www.pbc.gov.cn"+"/zhengcehuobisi/125207/125217/125925/index.html");
        for (int j=1;j<=20;j++){
            WebElement webElement = webDriver.findElement(By.xpath("//*[@id=\"17105\"]/div[2]/div[1]/table/tbody/tr[2]/td/table["+j+"]/tbody/tr/td[2]/font/a"));
            str =webElement.getAttribute("outerHTML");
            str=str.substring(9,64);
            readContent(str,sheet,workbook,out);
        }
        webDriver.get("http://www.pbc.gov.cn"+"/zhengcehuobisi/125207/125217/125925/17105/index2.html");
        for (int j=1;j<=10;j++){
            WebElement webElement = webDriver.findElement(By.xpath("//*[@id=\"17105\"]/div[2]/div[1]/table/tbody/tr[2]/td/table["+j+"]/tbody/tr/td[2]/font/a"));
            str =webElement.getAttribute("outerHTML");
            str=str.substring(9,64);
            readContent(str,sheet,workbook,out);
        }
        webDriver.close();
        workbook.write(out);
        out.close();
        System.out.println("爬取结束!数据已保存至文件 "+filePath+" 中");
        System.out.println("<The End>");
    }
}