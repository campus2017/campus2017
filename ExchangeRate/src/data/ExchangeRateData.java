package data;

import util.HtmlAnalyze;
import util.StringSplit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/21.
 */
public class ExchangeRateData {

    private static String url1 = "http://www.pbc.gov.cn/zhengcehuobisi/125207/125217/125925/17105/index1.html";
    private static String url2 = "http://www.pbc.gov.cn/zhengcehuobisi/125207/125217/125925/17105/index2.html";

    public static List<String> getExchangeRateData() throws IOException {

        ArrayList<String> al1;
        ArrayList<String> al2;
        List<String> list = new ArrayList<>();

        al1 = HtmlAnalyze.getItems(url1,HtmlAnalyze.INDEX_1);//获取第一页的20个items
        al2 = HtmlAnalyze.getItems(url2,HtmlAnalyze.INDEX_2);//获取第二页的10个items

        for (int i = 0; i < al1.size(); i++) {
            getRateData(al1, list, i);//第一页的20个
        }

        for (int i = 0; i < al2.size(); i++) {
            getRateData(al2, list, i);//第一页的10个
        }

        return list;
    }

    private static void getRateData(ArrayList<String> al, List<String> list, int i) throws IOException {
        String everyDayUrl = StringSplit.getEveryDayUrl(al.get(i));//每一天汇率中间价的网址
        String everyDayRate = HtmlAnalyze.getEveryDayRate(everyDayUrl);//每天 人民币对各国汇率中间价
        String rateData = StringSplit.getExchangeRateData(everyDayRate);//人民币对美元，欧元，港币的中间价
        list.add(rateData);
    }

}
