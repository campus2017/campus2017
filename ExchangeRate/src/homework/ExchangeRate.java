package homework;

/**
 * Created by jiye on 2017/2/12.
 */

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.SocketTimeoutException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExchangeRate {

    public static Document getDataByJsoup(String url) {
        Document doc2 = null;
        try {
            doc2 = Jsoup.connect(url).timeout(5000).get();
        } catch (SocketTimeoutException e) {
            System.out.println("Socket连接超时");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc2;
    }

    /*
    比较两个字符串类型的日期的大小
     */
    public static int compare_date(String DATE1, String DATE2) {


        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public static void getThirtyAvg() {
        List<String> list = new ArrayList<String>();
        Document document1 = ExchangeRate
                .getDataByJsoup("http://www.chinamoney.com.cn/fe-c/historyParity.do");
        Elements elements = null;
        try {
            elements = document1.getElementsByTag("td");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //获取当前日期的前三十天日期
        String preDate = sdf.format(new Date(d.getTime() - 10 * 24 * 60 * 60 * 1000 - 20 * 24 * 60 * 60 * 1000));

        //得到td标签的下标，开始获取数据
        int index = 28;
        do {
            for (int i = 1; i < 6; i++) {
                if (i != 4) {
                    list.add(elements.get(index).text());
                }
                index++;
            }
            index += 19;
            if (index >= elements.size()) {
                break;
            }
        } while (compare_date(elements.get(index).text(), preDate) > 0);//判断当前日期是否已在需要查找的日期之前
        WritableWorkbook wwb = null;
        OutputStream os = null;
        try {
            String[] title = {"1美元对人民币", "1欧元元对人民币", "1港币对人民币"};
            String filePath = "./excl/JXL.xls";
            File file = new File(filePath);
            file.createNewFile();
            os = new FileOutputStream(filePath);
            //根据路径生成excel文件
            wwb = Workbook.createWorkbook(os);
            //创建表格
            WritableSheet sheet = wwb.createSheet("sheet1", 0);
            Label label = new Label(0, 0, "30天内RMB汇率中间价");
            sheet.addCell(label);
            for (int i = 0; i < title.length; i++) {
                label = new Label(i + 1, 0, title[i]);
                sheet.addCell(label);
            }

            for (int i = 1; i < list.size() / 4 + 1; i++) {
                for (int j = 0; j < 4; j++) {
                    label = new Label(j, i, list.get((i - 1) * 4 + j));
                    sheet.addCell(label);
                }
            }
            wwb.write();
        } catch (FileNotFoundException e) {
            System.out.println("文件没找到");
        } catch (WriteException e) {
            System.out.println("输入异常");
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (wwb != null)
                try {
                    wwb.close();
                } catch (WriteException e) {

                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (os != null)
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public static void main(String[] args) {
        //ExchangeRate.getThirtyAvg();
        getThirtyAvg();
    }
}