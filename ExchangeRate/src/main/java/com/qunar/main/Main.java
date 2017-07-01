package com.qunar.main;

import com.qunar.entity.Rate;
import com.qunar.utils.DownHtml;
import com.qunar.utils.ExportToExcel;
import com.qunar.utils.ParseHtml;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by isc on 2017/1/10.
 */
public class Main {

    public static void main(String[]args) {

        String path = "G:\\人民币汇率统计表.xls";

        String urlstr1 = "http://www.pbc.gov.cn/zhengcehuobisi/125207/125217/125925/17105/index1.html";//只有20条,需要获取一月数据
        String urlstr2 = "http://www.pbc.gov.cn/zhengcehuobisi/125207/125217/125925/17105/index2.html";
        List<String> allUrls = new ArrayList<String>();//存储全部的url地址

        Optional<Document> documentOptional;
        Optional<Document> documentOptiona2;
        try {
            documentOptional = new DownHtml().connect(urlstr1);
            documentOptiona2 = new DownHtml().connect(urlstr2);

            List<String> list1 = new ParseHtml().getAllParseList(documentOptional.get());
            List<String> list2 = new ParseHtml().getAllParseList(documentOptiona2.get());

            allUrls.addAll(list1);
            allUrls.addAll(list2);
        }catch (Exception e){
            e.printStackTrace();
        }
        List<Rate> rateList = new ArrayList<Rate>();
        try {
            //获取数据
            for (int i = 0; i < allUrls.size(); i++) {
                urlstr1 = "http://www.pbc.gov.cn/" + allUrls.get(i);
                documentOptional = new DownHtml().connect(urlstr1);
                Rate rate = new ParseHtml().getRmbData(documentOptional.get()).get();
                rateList.add(rate);
            }
            //导出到xls
            ExportToExcel.out(rateList, path);
        }catch (Exception e) {
            ExportToExcel.out(rateList, "F:\\人民币汇率统计表.xls");
            e.printStackTrace();
        }
    }
}
