package com.qunar.homework.juanpingl;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;


/**
 * Created by juanpingl on 17/2/12.
 * 二、分析从今天开始过去 30 天时间里，中国人民银行公布的人民币汇率中间价，得到人民币对美元、欧元、
 * 港币的汇率，形成 excel 文件输出。汇率数据找相关的数据源，自己爬数据分析。（作业命名：ExchangeRate）
 */
public class ExchangeRate 
{
	private DateTime endDay = DateTime.now();
	private DateTime startDay = endDay.minusDays(50);	// 周末没有数据
	private DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
	private static final String exchangeDataUrl = "http://www.safe.gov.cn/AppStructured/view/project_RMBQuery.action";
	private static final int excelColums = 4; 	// 日期		美元 	欧元 	港元
	private static final int[] Num = {0, 1, 2, 4}; 	// "日期		美元 	欧元 	港元" index
	private static final String excelFileName = "ExchangeRate.xls";

	/**
	 *
	 * @return
	 * @throws IOException
	 */
	private Document getExchangeData() throws IOException {
		Document doc = null;
		try {
			doc = Jsoup.connect(exchangeDataUrl)
					.data("projectBean.startDate", fmt.print(startDay))
					.data("projectBean.endDate", fmt.print(endDay))
					.timeout(5000)
					.get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc;
	}

	/**
	 *
	 * @return
	 * @throws IOException
	 */
	private WritableWorkbook createExcelWorkbook() throws IOException {
		File file = new File(excelFileName);
		if(file.exists()) {
			file.delete();
		}

		WritableWorkbook wwb = Workbook.createWorkbook(file);
		return wwb;
	}

	/**
	 *
	 * @param wwb
	 * @param doc
	 * @throws IOException
	 * @throws WriteException
	 */
	public void writeExchangeDataToExcel(WritableWorkbook wwb, Document doc) throws IOException, WriteException {
		// 创建 sheet
		WritableSheet ws = wwb.createSheet("过去30天内人民币对美元、欧元、港币汇率中间价", 0);

		// 获取表数据
		Element table = doc.getElementsByClass("list").first();
		Elements titles = table.getElementsByClass("table_head");
		Elements data = table.getElementsByClass("first");

		int j = 0;
		for (int i = 0; i < excelColums; i++) {
			j = 0;

			// 写入表头
			String title = titles.get(Num[i]).text();
			Label titleLabel = new Label(i, j, title);
			ws.addCell(titleLabel);
			j++;

			// 写入每天的人民币汇率中间价
			for (int k = 0; k < data.size(); k++) {
				Elements exchangeRates = data.get(k).getElementsByTag("td");
				String str = exchangeRates.get(Num[i]).text();
				Label dataLabel = new Label(i, j, str);
				ws.addCell(dataLabel);
				j++;
			}
		}

		wwb.write();
		wwb.close();
	}

	/**
	 *
	 * @param args
	 */
	public static void main( String[] args )
	{
		System.out.println("Begining ...");
		ExchangeRate er = new ExchangeRate();
		Document doc = null;
		try {
			doc = er.getExchangeData();
		} catch (IOException e) {
			System.out.println("Failed to get data" + e);
			System.exit(1);
		}

		WritableWorkbook wwb = null;
		try {
			wwb = er.createExcelWorkbook();
		} catch (IOException e) {
			System.out.println("Failed to create excel workbook.");
			e.printStackTrace();
			System.exit(1);
		}		

		try {
			er.writeExchangeDataToExcel(wwb, doc);
			System.out.println( "Excel file writed.");
		} catch (IOException e) {
			System.out.println( "Failed to write excel file.");
			e.printStackTrace();
		} catch (WriteException e) {
			System.out.println( "Failed to write excel file.");
			e.printStackTrace();
		}
	}
}
