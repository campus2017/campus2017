package com.qunar.ExchangeRate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.htmlparser.Parser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;


class ExchangeRate {
	 static Double dol = 0.0;
	 static Double eng = 0.0;
	 static Double hon = 0.0;
	
	/**************************getPage 获取到目的网页**********************/
	public static String getPage(String url) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		WebClient webClient = new WebClient();
		//htmlunit 对css和javascript的支持不好，所以请关闭
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setCssEnabled(false);
		//获取页面
		HtmlPage page = webClient.getPage(url);
		//以xml的形式获取响应文本
		String pageXml = page.asXml();  
		return pageXml;
		
	}
	/***************************jsoupToPage 解析中国人民银行汇率首页**********************/
	private static List<String> jsoupToPage(String pageXml) {
		List<String> list = new ArrayList<String>();
		Document doc = Jsoup.parse(pageXml);
    	System.out.println("********jsoup******");
        Element content = doc.getElementById("r_con");	
        Elements links = content.getElementsByTag("a");
        for(int i = 0;i<20;i++){
        	Element link = links.get(i);
        	String linkHref = link.attr("href");
        	String linkText = link.text();
        	System.out.println(linkHref);
        	list.add("http://www.pbc.gov.cn"+linkHref);
        }
        return list;      
	}
	/***************************exchangeRate 得到汇率**********************/
	private static void exchangeRate(String str) {
		String[] temparray = str.split("，"); //将文字用,分割
        String doltemp = temparray[1];
        String date = doltemp.substring(0, 10); //日期
        doltemp = doltemp.split("：")[1];  //对美元的汇率
        String engtemp = temparray[2];    //对欧元的汇率
        String hontemp = temparray[4];    //对港币的汇率
        dol = dol
        		+ Double.parseDouble(doltemp.substring(7,
                        doltemp.length() - 1));
        eng = eng
                + Double.parseDouble(engtemp.substring(7,
                        engtemp.length() - 1));
       hon = hon
                + Double.parseDouble(hontemp.substring(7,
                        hontemp.length() - 1));
       
       
	}
	/***************************average 得到汇率平均值**********************/
	private static void average() {
		DecimalFormat df = new DecimalFormat("######0.00");  //对数字进行格式化
		   dol = dol / 20;
		   eng = eng / 20;
		   hon = hon / 20;
		   System.out.println(df.format(dol));   //分别得到平均值
		   System.out.println(df.format(eng));
		   System.out.println(df.format(hon));
	}
	
	/***************************jsoupToChildPage 解析中国人民银行20天内对外汇率**********************/
	private static void jsoupToChildPage(List<String> list, int i)
			throws MalformedURLException, IOException {
		String xml = getPage(list.get(i));
		Document doc = Jsoup.parse(xml);
		Element content = doc.getElementById("zoom");	
		Elements links = content.getElementsByTag("p");	
		String str = links.text();   //得到p标签中的文本内容 
		System.out.println(str);
		exchangeRate(str);
		
	}
	/***************************excel 记录人民币汇率数据**********************/
	public static void excel(){
		WritableWorkbook wwb = null;  //和操作excel相关
        OutputStream os = null;      //输出流
        DecimalFormat df = new DecimalFormat("######0.00");  //对数字进行格式化
        try {
            String[] title = { "1美元对人民币", "1欧元元对人民币", "1港币对人民币" };
            String filePath = "F:\\qunar1.xls";  //excel文件保存的地址
            File file = new File(filePath);  
                file.createNewFile();
            os = new FileOutputStream(filePath);
            wwb = Workbook.createWorkbook(os);             //创建一个工作薄
            WritableSheet sheet = wwb.createSheet("sheet1", 0); //创建一个工作表
            Label label = new Label(0,0,"30天内RMB汇率中间价");   //为工作表添加一个标签
            sheet.addCell(label);      
            for (int i = 0; i < title.length; i++) {
                label = new Label(1 , i , title[i]);
                sheet.addCell(label);
            }
            label = new Label ( 2, 0 , df.format(dol));
            sheet.addCell(label);
            label = new Label ( 2, 1 , df.format(eng));
            sheet.addCell(label);
            label = new Label ( 2 , 2 , df.format(hon));
            sheet.addCell(label);
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
	
	
	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		String url = "http://www.pbc.gov.cn/zhengcehuobisi/125207/125217/125925/index.html";
		String pageXml = getPage(url);
		//使用jsoup解析.xml文档，可以获取到需要的链接
		List<String> list = new ArrayList<String>();
		list = jsoupToPage(pageXml);  
		
        for(int i = 0;i<list.size();i++) {
        	jsoupToChildPage(list, i);
        } 
        average();
        excel();    
    }
}


