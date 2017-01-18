
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.*;

/**
 * Created by zdl on 2017/1/10.
 * 参考＠wwy941122作业
 */
public class ExchangeRate {

    /**
     * @param path 保存路径
     * */
    public static void save(Map<String, List<Double>> data, String path) {
        File dir = new File(path);
        if (!dir.exists()) {
           dir.mkdirs();

        } else if (!dir.isDirectory()) {
            System.err.println("invalid path, need a directory!");
            return ;
        }

        HSSFWorkbook hwb = new HSSFWorkbook();
        HSSFSheet hSheet = hwb.createSheet("人民币汇率中间价");
        HSSFRow row1 = hSheet.createRow(0);

        row1.createCell(0).setCellValue("日期");
        row1.createCell(1).setCellValue("美元汇率");
        row1.createCell(2).setCellValue("欧元汇率");
        row1.createCell(3).setCellValue("港元汇率");

        int nrow = 1;
        for(String date : data.keySet()) {
            HSSFRow row = hSheet.createRow(nrow++);
            row.createCell(0).setCellValue(date);
            row.createCell(1).setCellValue(data.get(date).get(0));
            row.createCell(2).setCellValue(data.get(date).get(1));
            row.createCell(3).setCellValue(data.get(date).get(2));
        }
        hSheet.setColumnWidth(0, 15 * 256);
        FileOutputStream fout = null;
        try{
            fout = new FileOutputStream(path + "ExchangeRate.xls");
            hwb.write(fout);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param url 爬取链接地址
     * */
    public static Map<String, List<Double>> craw(String url) {
        //设置日期
        DateFormat df = DateFormat.getDateInstance();
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        String end = df.format(c.getTime());
        c.add(Calendar.MONTH, -1);
        String start = df.format(c.getTime());
        // dopost
        Map<String, List<Double>> res = new LinkedHashMap<String, List<Double>>();
        try {
            Document doc = Jsoup.connect(url)
                    .data("projectBean.startDate",start)
                    .data("projectBean.endDate", end)
                    .data("queryYN", "true")
                    .post();
            Element table = doc.body().getElementById("InfoTable");
            Elements rows = table.getElementsByTag("tr");
            for(int i = 1; i < rows.size(); ++i) {
                Elements tds = rows.get(i).getElementsByTag("td");
                String date = tds.get(0).text();
                res.put(date, new ArrayList<Double>());
                //得到的文本会出现NumberFormatException 如果直接parse处理的话
                String str = tds.get(1).text();
                str = str.substring(0, str.length()-1);
                res.get(date).add(Double.parseDouble(str));
                str = tds.get(2).text();
                str = str.substring(0, str.length()-1);
                res.get(date).add(Double.parseDouble(str));
                str = tds.get(4).text();
                str = str.substring(0, str.length()-1);
                res.get(date).add(Double.parseDouble(str));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(res);
        return res;
    }


    public static void main(String[] args) {
        ExchangeRate er = new ExchangeRate();
        Map<String, List<Double>> rates = er.craw("http://www.safe.gov.cn/AppStructured/view/project_RMBQuery.action");
        er.save(rates, "./");
    }
}
