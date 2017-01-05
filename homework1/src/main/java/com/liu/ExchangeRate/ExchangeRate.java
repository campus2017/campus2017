package com.liu.ExchangeRate;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by liudan on 2017/1/3.
 */

public class ExchangeRate {
    public static fuction_unit fu = new fuction_unit();
    public static String datatime = "";//时间
    public static String RMBToDollar = "";//人民币与美元汇率
    public static String RMBToEuro = "";//人民币与欧元汇率
    public static String RMBToHKD = "";//人民币与港币汇率
    public static List<String> urls = new ArrayList<String>();
    public static List<String> dataList = new ArrayList<String>();
    public static String[][] allData=null;
    public static String[] data=null;
    public static List<String[]> allDataList=new ArrayList<String[]>();


    public static void main(String[] args) throws Exception {

        //使用WebDriver爬取数据
        System.setProperty("webdriver.chrome.driver", "D:/workspace/spider/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("window-size=1024,768");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        WebDriver driver = new ChromeDriver(capabilities);
        //生成Excel工具类
        Writer_excel we=new Writer_excel();
        //汇率数据来源为中国人民银行货币政策司的数据，选择2016年12月1日-2016年12月30日之间的汇率，节假日无数据。
        String url1 = "http://www.pbc.gov.cn/zhengcehuobisi/125207/125217/125925/17105/index";
        String url2 = ".html";
        String url = "";
        for (int i = 1; i < 3; i++) {
            url = url1 + i + url2;
            System.out.println(url);
            driver.get(url);
            String page = driver.getPageSource();
            try {
                getallurl(page);//获得一级页面中12月份包含汇率数据的url地址
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        for (String urlData : urls) {
            driver.get(urlData);
            String pageData = driver.getPageSource();
            getallimformation(pageData);//获取二级页面中12月汇率数据详细信息，包含时间、人民币对美元、欧元，港币汇率
            Object[] temp = allDataList.toArray();
            allData = new String[temp.length][];
            for(int i=0;i<temp.length;i++){
                allData[i] = (String[])temp[i];
            }
        }
        System.out.println("allData"+ Arrays.deepToString(allData));
        we.outputExcel(allData);//调用Excel工具类，生成excel文件
    }

    /*
     *@author: liudan
     *@description: 获取二级页面中12月汇率数据详细信息，包含时间、人民币对美元、欧元，港币汇率
     *@param: String pageData
     *@returnParam: void
     */
    public static void getallimformation(String pageData) {

        String[] lines = pageData.split("\n");
        for (String line : lines) {
            if (line.contains("<p>中国人民银行授权中国外汇交易中心公布")) {
                datatime = fu.regexFind("<p>中国人民银行授权中国外汇交易中心公布，(.+)银行间外汇市场人民币汇率中间价", line);
                RMBToDollar = fu.regexFind("1美元对人民币(.+)元，1欧元", line);
                RMBToEuro = fu.regexFind("1欧元对人民币(.+)元，100日元", line);
                RMBToHKD = fu.regexFind("1港元对人民币(.+)元，1英镑", line);
                System.out.println("datatime"+datatime);
                dataList.add(datatime);
                dataList.add(RMBToDollar);
                dataList.add(RMBToEuro);
                dataList.add(RMBToHKD);
                data = dataList.toArray(new String[dataList.size()]);
                dataList.clear();
                allDataList.add(data);
            }
        }
    }

    /*
     *@author:liudan
     *@description: 获得一级页面中12月份包含汇率数据的url地址
     *@param: String page
     *@returnParam: void
     */
    public static void getallurl(String page) {
        String[] lines = page.split("\n");
        for (String line : lines) {
            if (line.contains("<a href=\"/zhengcehuobisi/125207/")&&line.contains("2016年12月")) {
                String url = "http://www.pbc.gov.cn" + fu.regexFind("<a href=\"(.+)\" onclick=\"void", line);
                urls.add(url);
            }
        }
    }

}