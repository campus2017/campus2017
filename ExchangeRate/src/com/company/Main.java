package com.company;

import crawler.ExchangeRateCrawler;
import crawler.ExchangeRateCrawlerRule;
import utils.Utils;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //input path
        System.out.println("请输入数据文件需要存储的路径(文件后缀为xls)");
        Scanner in = new Scanner(System.in);
        String inStr = in.nextLine();

        while(!Utils.IsRightPath(inStr)){
            System.out.println("请输入正确的存储路径");
            inStr = in.nextLine();
        }

        System.out.println("正在导出数据...");

        ExchangeRateCrawler crawler = new ExchangeRateCrawler();

        //get crawler rule
        ExchangeRateCrawlerRule rule = Utils.GetCrawlerRule();

        //extract data
        crawler.Extract(rule);

        //export
        crawler.Export(inStr);

        System.out.println("导出成功");
    }

}
