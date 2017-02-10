package qunar;

import java.io.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

import jxl.*;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import jxl.write.Number;
import jxl.write.Boolean;

public class ExchangeRate {
	static double[] rate = new double[3];

	public static void main(String[] args) throws IOException, WriteException {
		System.out.println("the begin");
		writeHeadExcel();
		System.out.println("the end");
	}

	public static void readHtml(String date) throws IOException {
		Document doc = Jsoup.connect("http://www.kuaiyilicai.com/bank/rmbfx/b-safe.html?querydate=" + date).get();
		Elements trs = doc.select("table").select("tr");
		for (int i = 0; i < 4; i++) {
			Elements tds = trs.get(i).select("td");
			for (int j = 0; j < tds.size(); j++) {
				String text = tds.get(j).text();
				if (j == 1) {
					rate[i - 1] = Double.parseDouble(text);
				}
				// System.out.println(text);
			}
		}
		// System.out.println(doc.title());
	}

	public static void writeHeadExcel() throws WriteException, IOException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date lastdate = new Date();

		File fileWrite = new File("f:/exchangerate.xls");
		fileWrite.createNewFile();
		OutputStream os = new FileOutputStream(fileWrite);

		WritableWorkbook workbook = Workbook.createWorkbook(os);
		WritableSheet sheet = workbook.createSheet("汇率", 0);
		Label riqi = new Label(0, 0, "日期");
		sheet.addCell(riqi);
		Label gang = new Label(1, 0, "港币");
		sheet.addCell(gang);
		Label mei = new Label(2, 0, "美元");
		sheet.addCell(mei);
		Label ou = new Label(3, 0, "欧元");
		sheet.addCell(ou);

		for (int i = 0; i < 30; i++) {
			lastdate = getBeforeDay(lastdate);
			String date = sdf.format(lastdate);
			readHtml(date);
			Label ldate = new Label(0, i + 1, date);
			sheet.addCell(ldate);
			Label lhkd = new Label(1, i + 1, String.valueOf(rate[0]));
			sheet.addCell(lhkd);
			Label lusd = new Label(2, i + 1, String.valueOf(rate[1]));
			sheet.addCell(lusd);
			Label leur = new Label(3, i + 1, String.valueOf(rate[2]));
			sheet.addCell(leur);
		}
		workbook.write();
		workbook.close();
		os.close();

	}

	public static Date getBeforeDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		return date;
	}

}
