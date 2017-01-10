package com.qunar;

import java.util.List;

/**
 * Qunar2017 Homework 2
 * 分析从今天开始过去30天时间里，中国人民银行公布的人民币汇率中间价，得到人民币对美元、欧元、港币的汇率，形成excel文件输出。
 * 汇率数据找相关的数据源，自己爬数据分析。
 *
 * Created by WanlongMa on 2016/12/21.
 *
 * 测试源文件：text-2.txt
 * 数据来源：中国人民银行货币政策司
 *          http://www.pbc.gov.cn/zhengcehuobisi/125207/125217/125925/index.html
 *
 */
public class Main extends BaseObject {

    public static final double DAYS = 30;

    public static void main(String[] args){

        // 抓取数据
        log("开始抓取 " + DAYS + " 天内的人民币汇率数据");
        RequestRate rr = new RequestRate();
        List<RateBean> list = rr.getRateBeans(rr.getRatePageUrlList(DAYS));
        log("数据抓取完成");

        // 写入文件
        log("开始写入Excel文件");
        if(ExcelHelper.createExcelAct("test-1.xls","人民币汇率表",list))
            log("文件写入完成");
        else
            log("文件写入失败");

    }

}
