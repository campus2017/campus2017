package com.qunar.hotel.campus2017.exchangeRate;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by fangming.yi on 2017/01/14.
 */
public class ExchangeRate {
    private List<String> day = new ArrayList<String>();   //存放日期数据
    private List<String> usd = new ArrayList<String>();   //存放美元汇率
    private List<String> eur = new ArrayList<String>();   //存放欧元汇率
    private List<String> hkd = new ArrayList<String>();   //存放港币汇率

    //生成excel
    private void genExcel(String path) throws WriteException {
        if(day==null||usd==null||eur==null||hkd==null){
            return;
        }
        Date cur = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(cur);
        if(path.matches(".*\\\\")){
            path+=date+".xls";
        }
        else{
            path+="\\"+date+".xls";
        }
        WritableWorkbook workbook = null;
        try {
            workbook = Workbook.createWorkbook(new File(path));
        } catch (IOException e) {
            System.out.println("输入路径有误！");
            System.exit(-1);
        }
        //生成第一页工作表
        WritableSheet sheet = workbook.createSheet("exchange_rate",0);
        //生成第一行 标题
        Label head_day = new Label(0,0,"日期");
        Label head_usd = new Label(1,0,"USD/CNY");
        Label head_eur = new Label(2,0,"EUR/CNY");
        Label head_hdk = new Label(3,0,"HKD/CNY");
        //将第一行添加到工作表中
        sheet.addCell(head_day);
        sheet.addCell(head_usd);
        sheet.addCell(head_eur);
        sheet.addCell(head_hdk);
        //将爬取得到的数据写进单元格
        int len = day.size();
        for(int i=0;i<len;i++){
            Label content_day = new Label(0,i+1,day.get(i));
            Label content_usd = new Label(1,i+1,usd.get(i));
            Label content_eur = new Label(2,i+1,eur.get(i));
            Label content_hkd = new Label(3,i+1,hkd.get(i));
            sheet.addCell(content_day);
            sheet.addCell(content_usd);
            sheet.addCell(content_eur);
            sheet.addCell(content_hkd);
        }
        System.out.println("generateExcel success!");
        try {
            workbook.write();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //从网上爬取汇率数据
    private void getData() throws IOException, WriteException {
        Document doc = Jsoup.parse(new URL("http://www.chinamoney.com.cn/fe-c/historyParity.do").openStream(), "UTF-8", "http://www.chinamoney.com.cn/fe-c/historyParity.do");
        Elements tables = doc.select("table");
        //获得汇率数据的所有行
        Elements trs = tables.get(2).select("tr");
        for(int i=1;i<trs.size();i++){
            Elements tds = trs.get(i).select("td");
            String date = tds.get(0).select("div").text();
            String dollar = tds.get(1).text();
            String euro = tds.get(2).text();
            String hongkd = tds.get(4).text();
            day.add(date);
            usd.add(dollar);
            eur.add(euro);
            hkd.add(hongkd);
        }
    }

    public static void main(String[] args) throws WriteException, IOException {
        ExchangeRate exchangeRate = new ExchangeRate();
        System.out.println("请选择要导出文件的保存路径：");
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.showOpenDialog(null);
        String path = chooser.getSelectedFile().getPath();
        if(path==null||path.trim().equals("")){
            System.out.println("未选择路径！");
            System.exit(-1);
        }
        System.out.println("你选择的路径为："+path);
        exchangeRate.getData();
        exchangeRate.genExcel(path);
    }
}
