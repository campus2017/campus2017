import java.io.File;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExchangeRate {

	static String[] arrayDate;
	static String[] usRate;
	static String[] euroRate;
	static String[] hkRate;

	static String[] title = new String[] { "日期", "美元", "欧元", "港元" };

	public static void main(String[] args) {
		arrayDate = getData("http://www.kuaiyilicai.com/huilv/d-boc-usd.html", 0);
		usRate = getData("http://www.kuaiyilicai.com/huilv/d-boc-usd.html", usRate);
		euroRate = getData("http://www.kuaiyilicai.com/huilv/d-boc-eur.html", euroRate);
		hkRate = getData("http://www.kuaiyilicai.com/huilv/d-boc-hkd.html", hkRate);
		initExcel();
	}

	
	/**
	 * 初始化Excel,设置title,完成填充
	 */
	public static void initExcel() {
		try {
			WritableWorkbook wwb = Workbook.createWorkbook(new File("/home/mml/桌面/excel.xlsx"));
			WritableSheet ws = wwb.createSheet("汇率数据", 0);
			Label data;
			for (int i = 0; i < title.length; i++) {
				data = new Label(i, 0, title[i]);
				ws.addCell(data);
			}
			for (int i = 1; i < arrayDate.length; i++) {
				data = new Label(0, i, arrayDate[i]);
				ws.addCell(data);
			}
			addDataToExcel(ws, usRate, 1);
			addDataToExcel(ws, euroRate, 2);
			addDataToExcel(ws, hkRate, 3);
			wwb.write();
			wwb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 填充汇率数据
	 */
	public static void addDataToExcel(WritableSheet ws, String[] rate, int index) {
		Label data;
		for (int i = 1; i < rate.length; i++) {
			data = new Label(index, i, rate[i]);
			try {
				ws.addCell(data);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取日期数据
	 */
	public static String[] getData(String url, int index) {
		arrayDate = new String[32];
		try {
			Document data = Jsoup.connect(url).get();
			Elements elements = data.select("table").select("tr");
			for (int i = 2; i < elements.size(); i++) {
				Elements tds = elements.get(i).select("td");
				arrayDate[i - 2] = tds.get(index).text();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return arrayDate;
	}

	/**
	 * 获取汇率数据
	 */
	public static String[] getData(String url, String[] rate) {
		rate = new String[32];
		try {
			Document data = Jsoup.connect(url).get();
			Elements elements = data.select("table").select("tr");
			for (int i = 2; i < elements.size(); i++) {
				Elements tds = elements.get(i).select("td");
				rate[i - 2] = tds.get(5).text();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rate;
	}

}
