package qunar.yaozhu;

/**
*分析从今天开始过去30天时间里，中国人民银行公布的人民币汇率中间价，得到人民币对美元、欧元、港币的汇率，形成excel
*文件输出。汇率数据找相关的数据源，自己爬数据分析。（作业命名：ExchangeRate）
*数据来源:http://www.safe.gov.cn/wps/portal/sy/tjsj_hlzjj_inquire
*/
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class Main {

	private static final String EXCEL_FILE = "D:\\人民币汇率.xls";

	public static void main(String[] args) {
		Date end = new Date();

		// 30天前的日期
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -30);
		Date start = calendar.getTime();

		List<String[]> datas = getRmbExchangeRate(start, end);
		writeRmbExchangeRateToExcelFile(datas, EXCEL_FILE);
	}

	private static List<String[]> getRmbExchangeRate(Date start, Date end) {
		List<String[]> datas = new ArrayList<>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = sdf.format(start);
		String endDate = sdf.format(end);

		String url = "http://www.safe.gov.cn/AppStructured/view/project_RMBQuery.action?queryYN=true&projectBean.startDate="
				+ startDate + "&projectBean.endDate=" + endDate;
		String html = getWebPageSourceCode(url);

		// 使用正则提取网页源码中的汇率数据
		String part = "&nbsp;</td><td [^>]* >([\\d\\.]+)";
		Pattern ptn = Pattern.compile("(\\d{4}-\\d{2}-\\d{2})" + part + part + part + part);
		Matcher mt = ptn.matcher(html);

		while (mt.find()) {
			// mt.group(4)是日元,故略去
			String[] data = { mt.group(1), mt.group(2), mt.group(3), mt.group(5) };
			datas.add(data);
		}

		return datas;
	}

	// 通过url获取网页html源码
	private static String getWebPageSourceCode(String url) {
		StringBuilder content = new StringBuilder();

		try (InputStream in = new URL(url).openStream();
				InputStreamReader inReader = new InputStreamReader(in);
				BufferedReader reader = new BufferedReader(inReader)) {

			String line = null;
			while ((line = reader.readLine()) != null) {
				if (line.trim().length() != 0) {
					content.append(line.trim());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return content.toString();
	}

	// 将汇率数据写到excel文件
	private static void writeRmbExchangeRateToExcelFile(List<String[]> datas, String filePath) {
		try {
			File file = new File(filePath);

			if (file.exists()) {
				file.delete();
			}

			WritableWorkbook book = Workbook.createWorkbook(file);
			WritableSheet sheet = book.createSheet("sheet1", 0);

			// 写入表头
			sheet.addCell(new Label(0, 0, "日期"));
			sheet.addCell(new Label(1, 0, "美元"));
			sheet.addCell(new Label(2, 0, "欧元"));
			sheet.addCell(new Label(3, 0, "港元"));

			int row = 1;

			// 写入每一行
			for (String[] data : datas) {
				// 写入每一列
				for (int col = 0; col < data.length; col++) {
					sheet.addCell(new Label(col, row, data[col]));
				}

				row++;
			}

			book.write();
			book.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
