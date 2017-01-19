package com.dj.rate;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author DJ
 * 对网页中的税率进行解析
 * 1.需要将网页中的相关信息进行爬取
 * 2.采用jsoup对爬取的网页中的相关信息进行解析
 * 3.将爬取的信息用Rate对象进行封装
 * 4.创建execl文档
 * 5.将创建的对象写入到excel文档中
 */

public class ParseRate {

static ArrayList<Rate> list=null;
		
public static void main(String[] args) {
	
	List<ArrayList<Rate>> lis=new ArrayList<ArrayList<Rate>>();
	String[] country={"USD","EUR","HKD"};
	for(int i=0;i<country.length;i++){
		String url="http://cn.exchange-rates.org/history/"+country[i]+"/CNY/T";
		if(!parse(url).isEmpty()){
		   if(list.size()>30)
		    list.subList(0,31);	
		   //System.out.println("******"+list.size());
		    lis.add(list);
			System.out.println("信息抓取成功");
		}else{
			System.out.println("输入的网址有误");
		}
	}	
	Scanner scanner=new Scanner(System.in);
	System.out.println("请输入文件存放的位置");
	String path=scanner.nextLine();
	storeExcel(lis,path);	
	System.out.println("文件保存成功");
}

public static List<Rate> parse(String url){
	      list=new ArrayList<Rate>();
	try{
	Document  document=Jsoup.parse(new URL(url),5000);
	Element tbody=document.select("tbody").get(0);//获取到整个tbody标签
	Elements trs=tbody.getElementsByTag("tr");
	for(Element tr:trs){
		Rate rate=new Rate();
		Elements tds=tr.getElementsByTag("td");
		String tr_date=tds.get(0).text().trim();
		System.out.println(tr_date);
		Double tr_money=Double.parseDouble(tds.get(2).text().trim().substring(1,6));
		System.out.println(tr_money);
		rate.setDate(tr_date);
		rate.setMoney(tr_money);
		list.add(rate);	
	  }
	}catch(Exception e){
		e.printStackTrace();
		return null;
	}		
	return list;	
}
 
//将信息存储在excel文件中
@SuppressWarnings("deprecation")
private static void storeExcel(List<ArrayList<Rate>> lis, String path) {
	FileOutputStream fos=null;
try {
	HSSFWorkbook wb = new HSSFWorkbook();//将抓取的信息存入到excel文档中
	HSSFSheet sheet = wb.createSheet("货币汇率表");//创建一个excel的sheet
	HSSFRow row = sheet.createRow((int) 0);
	HSSFCellStyle style = wb.createCellStyle();
	style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //创建一个居中格式
	HSSFCell cell = row.createCell((short) 0);//设置第一列标题
	
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
	
	for(int i=0;i<31;i++){//实际获取的信息较多
		row=sheet.createRow((int)i+1);//创建了一行
		row.createCell(0).setCellValue(lis.get(0).get(i).getDate());//获取到日期
		row.createCell(1).setCellValue(lis.get(0).get(i).getMoney());//获取到汇率
		row.createCell(2).setCellValue(lis.get(1).get(i).getMoney());
		row.createCell(3).setCellValue(lis.get(2).get(i).getMoney());		
	}	
	  fos=new FileOutputStream(path + "\\ExchangeRates.xls");
	  wb.write(fos);//将信息写入到文件中
	}catch (FileNotFoundException e){
		 e.printStackTrace();
	}catch(IOException e){
		 e.printStackTrace();
	}finally{
		try {
			if(fos!=null)
			fos.close();
		} catch (IOException e) {
		e.printStackTrace();
	  }
	}	
  }
}
