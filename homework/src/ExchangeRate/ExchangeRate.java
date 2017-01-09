package ExchangeRate;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ExchangeRate {
    /**
     * 二、分析从今天开始过去 30 天时间里，中国人民银行公布的人民币汇率中间价，得到人民币对美元、欧元、
     * 港币的汇率，形成 excel 文件输出。汇率数据找相关的数据源，自己爬数据分析。（作业命名：ExchangeRate）
     */

    public static void main(String[] args) {
        String today = DateUtil.getNow();
        String preMonth = DateUtil.format(DateUtil.addDay(new Date(), -30));
        String url = "http://www.safe.gov.cn/AppStructured/view/project_RMBQuery.action?projectBean.startDate=" + preMonth +
                "&projectBean.endDate=" + today + "&queryYN=true";
        try {
            Document doc = Jsoup.connect(url).post();
            Element table = doc.getElementById("InfoTable");
            Element tbody = table.child(0);
            List<Rate> list = new ArrayList<>();
            for (int i = 1; i < tbody.children().size(); i++) {
                Element tr = tbody.child(i);
                System.out.println(tr.child(0).html());
                Rate rate = new Rate();
                rate.setDate(tr.child(0).html().replaceAll("&nbsp;", ""));
                rate.setUsd(Double.parseDouble(tr.child(1).html().replaceAll("&nbsp;", ""))/100);
                rate.setEur(Double.parseDouble(tr.child(2).html().replaceAll("&nbsp;", ""))/100);
                rate.setHkd(Double.parseDouble(tr.child(4).html().replaceAll("&nbsp;", ""))/100);
                list.add(rate);
            }
            writeExcel(list);
        } catch (Exception e) {
            System.out.println("呀，出错了");
            e.printStackTrace();
        }
    }

    private static void writeExcel(List<Rate> list) throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();
        int row = 0;
        HSSFRow header = sheet.createRow(row++);
        HSSFCell cell = header.createCell(0);
        cell.setCellValue("日期");
        cell = header.createCell(1);
        cell.setCellValue("美元");
        cell = header.createCell(2);
        cell.setCellValue("欧元");
        cell = header.createCell(3);
        cell.setCellValue("港币");

        for (Rate rate : list) {
            HSSFRow r = sheet.createRow(row++);
            int col = 0;
            cell = r.createCell(col++);
            cell.setCellValue(rate.getDate());
            cell = r.createCell(col++);
            cell.setCellValue(rate.getUsd());
            cell = r.createCell(col++);
            cell.setCellValue(rate.getEur());
            cell = r.createCell(col++);
            cell.setCellValue(rate.getHkd());
        }

        OutputStream out = new FileOutputStream(new File("过去一月汇率.xls"));
        wb.write(out);
        out.flush();
        out.close();
    }
}
