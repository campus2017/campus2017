package com.qunar.kefa;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.qunar.er.vo.Line;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

/**
 * Created by kefa.zhang on 2017/6/15.
 */
public class Main {


    public static final int duration = 30;
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Set<SpiderTask> tasks = Sets.newHashSet();
        Date start = new Date();
        Map<Date, Line> res = Maps.newTreeMap();
        tasks.add(new SpiderTask(new Spider(CurrencyEnum.USD,start,duration))); // 异步爬取汇率信息
        tasks.add(new SpiderTask(new Spider(CurrencyEnum.EUR,start,duration)));
        tasks.add(new SpiderTask(new Spider(CurrencyEnum.HKD,start,duration)));

        List<Future<Map.Entry<CurrencyEnum, Map<Date, Line>>>> futures = executorService.invokeAll(tasks);
        for (Future<Map.Entry<CurrencyEnum, Map<Date, Line>>> future : futures) {
            Map.Entry<CurrencyEnum, Map<Date, Line>> resPart = null;
            try {
                resPart = future.get(3, TimeUnit.SECONDS);// 超时3秒
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            integrateMap(res,resPart);
        }
        boolean b = ExcelWriter.writeFile("exchange-rate\\res\\近30天汇率统计.xls", res); // 位于本工程本项目的res文件夹下
        System.out.println(b?"Success!":"Failed，remote site error！");
        executorService.shutdown();
    }

    private static void integrateMap(Map<Date,Line> res, Map.Entry<CurrencyEnum, Map<Date, Line>> resPart) {
        CurrencyEnum currency = resPart.getKey();
        Map<Date, Line> part = resPart.getValue();
        if(res.isEmpty()){
            res.putAll(part);
        }else {
            Set<Date> set = res.keySet();
            for (Date date : set) {
                Line partLine = part.get(date);
                if(currency == CurrencyEnum.EUR){
                    double reate = partLine.getToEU();
                    Line finalLine = res.get(date);
                    finalLine.setToEU(reate);
                }else if(currency == CurrencyEnum.USD){
                    double toUsRate = partLine.getToUS();
                    Line finalLine = res.get(date);
                    finalLine.setToUS(toUsRate);

                }else if(currency == CurrencyEnum.HKD){
                    double rate = partLine.getToHK();
                    Line finalLine = res.get(date);
                    finalLine.setToHK(rate);
                }
            }
        }
    }
}

