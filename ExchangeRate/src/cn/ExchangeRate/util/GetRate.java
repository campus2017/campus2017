package cn.ExchangeRate.util;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 朱潇翔 on 2017/1/23.
 */
public class GetRate {
    private String htmlURL;
    private List<RateBean> list;

    public GetRate(String htmlURL) {
        this.htmlURL = htmlURL;
        this.list = new ArrayList<RateBean>();
        try {
            this.getRateFromHTML();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "网页打不开", "网页打不开",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    private void getRateFromHTML() throws IOException {
        //获取网页信息
        Document document = Jsoup.connect(this.htmlURL).get();
        Element ebody = document.body(); //解析body片段
        //获取到最深的table Tag，那里面存的是数据
        Element eTable = ebody.getElementsByTag("table").last();

        //获取eTable中的每一个tr Tag；Td中包含eTable中的每一个tr元素
        //一个tr为一行：tr：换行
        //一个td为一列：td：换列
        Elements eTr = eTable.getElementsByTag("tr");

        for(Element element : eTr) {
            //System.out.println(element);
            //或取每一行中的每一列
            Elements eTd = element.getElementsByTag("td");

            /*
            *
            *  <td class="dreport-row2-1" style="text-align: center; width:80px;">
            *     <div style="text-align: center; width:80px;">
            *        2017-01-23
            *     </div>
            *  </td>
            *  <td class="dreport-row2">6.8572</td>
			*  <td class="dreport-row2">7.3509</td>
			*  <td class="dreport-row2">6.0209</td>
			*  <td class="dreport-row2">0.88391</td>
			*  <td class="dreport-row2">8.4934</td>
			*  <td class="dreport-row2">5.1891</td>
			*  ...
            * */
            //eTd中的第一列为日期
            //eTd.get(0).getElementsByTag("div")：获取到以“div”为分隔的第一列的内容
            //.get(0).text()：获取到div中第一列的内容
            String date = eTd.get(0).getElementsByTag("div").get(0).text();
            //因为第一行为类型，为了简单，类型设为String
            String usd = eTd.get(1).text(); //美元
            String eur = eTd.get(2).text(); //欧元
            String hkd = eTd.get(4).text(); //港币
            this.list.add(new RateBean(date, usd, eur, hkd));
            //System.out.println(date);
        }
    }

    public void putRateToExcel(String filePath) throws IOException {
        //输出到Excel
        File file = new File(filePath);

        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        int i = 0;
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("人民币汇率中间价");
        for(RateBean rateBean : this.list) {
            /*
            * System.out.println(rateBean.getDate() + " " + rateBean.getUsdCNY() + " "
            * + rateBean.getEurCNY() + " " + rateBean.getHkdCNY());
            */
            HSSFRow row = hssfSheet.createRow(i++);
            row.createCell(0).setCellValue(rateBean.getDate());
            row.createCell(1).setCellValue(rateBean.getUsdCNY());
            row.createCell(2).setCellValue(rateBean.getEurCNY());
            row.createCell(3).setCellValue(rateBean.getHkdCNY());
        }
        hssfSheet.setColumnWidth(0, 15 * 256);

        FileOutputStream fileOutputStream = new FileOutputStream(filePath + "\\ExchangeRate.xls");
        hssfWorkbook.write(fileOutputStream);
        fileOutputStream.close();

    }

    /*
    *     public void putRateToDataBase() {
    *     //保存到数据库
    *
    *    }
    * */
}
