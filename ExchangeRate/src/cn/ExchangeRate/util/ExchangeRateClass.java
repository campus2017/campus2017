package cn.ExchangeRate.util;

import java.io.IOException;

/**
 * Created by 朱潇翔 on 2017/1/23.
 */
public class ExchangeRateClass {
    /*
    * 需求：使用HttpClient爬取传智播客官方网站的数据
    * */
    public static void main(String[] args) {
        //创建HttpClient对象
//        HttpClient httpClient = new DefaultHttpClient();
//
//        //设置响应时间，设置传智源码时间，设置代理服务器
//        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 50000); //链接超时
//        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 50000);
//        //设置代理服务器，防止反爬虫
//        //httpClient.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, new HttpHost("222.175.22.222", 8998));
//
//        //爬虫URL大部分都是get请求，创建get请求对象
//        HttpGet httpGet = new HttpGet("http://www.itcast.cn");
//        //向传智播客的官方网站发送请求，获取网页源码
//        HttpResponse response = httpClient.execute(httpGet);
//
//        //使用EntityUtils工具类将网页源码转为字符串
//        String content = EntityUtils.toString(response.getEntity(), "utf-8");
//        System.out.println(content);

        String htmlURL = "http://www.chinamoney.com.cn/fe-c/historyParity.do";
        GetRate getRate = new GetRate(htmlURL);
        try {
            getRate.putRateToExcel("C:\\Users\\朱潇翔\\Desktop"); //保存到桌面
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
