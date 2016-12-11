package main;

import getData.ExtractService;
import getData.Rule;
import ExportToExcel.ExportToExcel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/8.
 */
public class Main {
    public static void main(String[] args) {
        Rule r=new Rule("http://www.chinamoney.com.cn/fe-c/historyParity.do");
        List<String> currencies= Arrays.asList("USD/CNY,EUR/CNY,HKD/CNY".split(","));
        ExtractService es=new ExtractService();
        Map<String,Map<String, String>> data=es.extract(r,currencies);
        List<String> titles=new ArrayList<String>(currencies);
        titles.add(0,"日期");
        ExportToExcel ete=new ExportToExcel();
        ete.outPut(titles,data);
    }
}
