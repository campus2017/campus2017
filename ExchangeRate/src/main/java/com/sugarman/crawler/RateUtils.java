package com.sugarman.crawler;

import jxl.Workbook;
import jxl.write.*;
import jxl.write.Number;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by SugarMan on 2017/1/17.
 */
public class RateUtils {

    public final static String URL = "http://www.chinamoney.com.cn/fe-c/historyParity.do";
    private Document mDocument;

    public RateUtils() throws IOException {
        mDocument = Jsoup.connect(URL).post();
    }

    /**
     * 爬取中国人民银行30天汇率
     * @param rateArr 汇率币种集合
     * @return 爬取数据
     * @throws IOException
     */
    public static HashMap<String, List<RateBean>> getRateByArr(String[] rateArr) throws IOException {

        if (rateArr == null || rateArr.length == 0)
            return null;
        // 初始化
        HashMap<String, List<RateBean>> results = new HashMap<String, List<RateBean>>();

        Document document = Jsoup.connect(URL).post();
        Element body = document.body();
        Element table = body.getElementsByTag("table").last();
        Elements rows = table.getElementsByTag("tr");
        Elements title = rows.first().getElementsByTag("td");
        // 通过名称找到相应的Num序列号
        int[] rateNums = new int[rateArr.length];
        for (int i = 0; i < rateArr.length; ++i) {
            for (int j = 0; j < title.size(); ++j) {
                if (title.get(j).text().equals(rateArr[i])) {
                    rateNums[i] = j;
                    break;
                }
            }
        }
        // 通过Num序列号找到内容
        for (int i = 0; i < rateArr.length; ++i) {
            List<RateBean> rateList = new ArrayList<RateBean>();
            for (int j = 1; j < rows.size(); ++j) {
                Elements tds = rows.get(j).getElementsByTag("td");
                String date = tds.get(0).getElementsByTag("div").first().text();
                double rate = Double.parseDouble(tds.get(rateNums[i]).text());
                rateList.add(new RateBean(date, rate));
            }
            results.put(rateArr[i], rateList);
        }
        return results;
    }

    /**
     * 保存Excel表格
     * @param dir 表格保存路径
     * @param content 需要保存的数据
     * @throws IOException
     * @throws WriteException
     */
    public static void SaveXml(String dir, HashMap<String, List<RateBean>> content) throws IOException,WriteException {
        // 检查路径合法性
        if (!checkDir(dir))
            return;
        File file = new File(dir + "\\ExchangeRate.xls");
        WritableWorkbook wwb = Workbook.createWorkbook(file);
        WritableSheet sheet = wwb.createSheet("汇率", 0);
        sheet.addCell(new Label(0,0,"日期"));
        Iterator iterator  = content.entrySet().iterator();
        int num = 0;
        while (iterator.hasNext()){
            ++num;
            Map.Entry entry = (Map.Entry) iterator .next();
            String key = (String)entry.getKey();
            sheet.addCell(new Label(num,0,key));
            List<RateBean> value = (List<RateBean>)entry.getValue();
            for (int i=0;i<value.size();++i){
                RateBean rate = value.get(i);
                sheet.addCell(new Number(num,i+1,rate.getRate()));
                sheet.addCell(new Label(0,i+1,rate.getDate()));
            }
        }
        wwb.write();
        wwb.close();
        System.out.println("保存成功");
    }

    /**
     * 检查路径合法性
     * @param dir 路径
     * @return 是否合法
     */
    private static boolean checkDir(String dir) {
        if (dir == null)
            return false;
        File file = new File(dir);
        if (file.exists()) {
            file.mkdir();
            return true;
        }else {
            return false;
        }
    }

}
