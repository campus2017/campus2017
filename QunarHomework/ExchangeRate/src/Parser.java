import org.apache.poi.hssf.usermodel.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * To calculate the exchange rate of RMB
 * @version 1  2017-01-01
 * @author Abby
 */

public class Parser {
	private final String commonPath=".table-responsive"; //表格
	private final String date="td";//日期
	private final String money="td:eq(2)";//1CNY=？
	public List<Item> list;
	public ArrayList<List<Item>> arr;

	//解析网页
	public boolean parse(String url) throws Exception{
		Document doc;
		try {
			doc = Jsoup.parse(new URL(url),40000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return true;
		} 
		Element commonElement=doc.select(commonPath).get(0);
		Elements tableElement=commonElement.select("tr");

		//建立新对象
		for(Element element:tableElement){
			Item item=new Item();
			String dateStr=element.select(date).get(0).text().trim();
			String moneyStr=element.select(money).get(0).text().trim();
			moneyStr=moneyStr.substring(0, moneyStr.length()-4);
			item.setDate(dateStr);
			item.setMoney(moneyStr);
			list.add(item);
		}
		return true;
	}
	//抓取页面
	public static void main(String[] args) throws Exception {
		Parser parser=new Parser();
		parser.arr=new ArrayList<List<Item>>();
		String[] currency={"USD","EUR","HKD"};

		for(int i=0;i<currency.length;i++){
			parser.list=new LinkedList<Item>();
			String url="http://cn.exchange-rates.org/history/"+currency[i]+"/CNY/T";
			boolean p = parser.parse(url);
			if(p==true)
			{
				if (parser.list.size() > 30) {
					parser.list = parser.list.subList(0, 30);//选取最近30天
				} else {
					System.out.println("网络环境不好！");
				}
				parser.arr.add(parser.list);
			}
			else
				System.out.println("网页分析错误！");

		}


		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("汇率表");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("日期");
		cell.setCellStyle(style);
		cell = row.createCell((short) 1);
		cell.setCellValue("美元");
		cell.setCellStyle(style);
		cell = row.createCell((short) 2);
		cell.setCellValue("欧元");
		cell.setCellStyle(style);
		cell = row.createCell((short) 3);
		cell.setCellValue("港币");
		cell.setCellStyle(style);


		for (int i = 0; i < parser.list.size(); i++)
		{
			row = sheet.createRow((int) i + 1);

			// 第四步，创建单元格，并设置值
			row.createCell((short) 0).setCellValue(parser.arr.get(0).get(i).getDate());
			row.createCell((short) 1).setCellValue(parser.arr.get(0).get(i).getMoney());
			row.createCell((short) 2).setCellValue(parser.arr.get(1).get(i).getMoney());
			row.createCell((short) 3).setCellValue(parser.arr.get(2).get(i).getMoney());
		}
		// 第六步，将文件存到指定位置
		try
		{
			FileOutputStream fout = new FileOutputStream("D:\\IdeaProjects/ExchangeRate/Result/Rates.xls");
			wb.write(fout);
			fout.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("分析完成！");
	}
}

