package org.qunar.campus.controller;

import org.qunar.campus.entity.RateBean;
import org.qunar.campus.util.ExportExcelTool;
import org.qunar.campus.util.HtmlParseTool;
import org.qunar.campus.util.HttpClientTool;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghe on 2017/7/4.
 */
public class ExchangeRateController {
    public static void main(String[] args) throws IOException {
        String response1 = HttpClientTool.doPost("http://www.pbc.gov.cn/zhengcehuobisi/125207/125217/125925/17105/index1.html", null);
        List<String> htmlText4String1 = HtmlParseTool.htmlText4reqUrl(response1,"http://www.pbc.gov.cn");

        String response2 = HttpClientTool.doPost("http://www.pbc.gov.cn/zhengcehuobisi/125207/125217/125925/17105/index2.html", null);
        List<String> htmlText4String2 = HtmlParseTool.htmlText4reqUrl(response2,"http://www.pbc.gov.cn");

        htmlText4String1.addAll(htmlText4String2);


        List<RateBean> reqUtl4Content = null;
        try {
            reqUtl4Content = HtmlParseTool.reqUtl4Content(htmlText4String1);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        ExportExcelTool.Export(reqUtl4Content);
    }
}
