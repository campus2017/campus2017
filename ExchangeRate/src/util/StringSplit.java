package util;

/**
 * Created by Administrator on 2016/11/21.
 */
public class StringSplit {

    public static String getEveryDayUrl(String string){

        String url = string.split("href=\"")[1].split("\" onclick")[0];

        return "http://www.pbc.gov.cn" + url;
    }

    public static String getExchangeRateData(String string){
        String s1 = string.trim().substring(22);
        String date = s1.split("银行间外汇市场人民币汇率中间价为：")[0];
        String s3 = s1.split("银行间外汇市场人民币汇率中间价为：")[1];
        String s4 = s3.split("1英镑对人民币")[0];
        String[] s5 = s4.split("元，");

        String rateData = date;
        for (int i = 0; i < s5.length; i++) {
            if (i != 2){
                String s6 = s5[i].split("人民币")[1];
                rateData = rateData + " " + s6;
            }
        }
        return rateData;
    }

}
