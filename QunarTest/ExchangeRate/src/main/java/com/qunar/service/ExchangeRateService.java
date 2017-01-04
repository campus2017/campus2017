package com.qunar.service;

import com.qunar.meta.ExchangeRate;

import java.util.List;
import java.util.Map;

/**
 * 汇率查询服务
 * Created by 张竣伟 on 2017/1/3.
 */
public class ExchangeRateService {


    /**
     * 查询服务
     *
     * @param filePath 存储文件路径
     */
    public void getExchangeRate(String filePath) {
        Map<String, List<ExchangeRate>> exchangeRateMap = new DataSpiderService().getExchangeRate();
        ExcelOutputService.output(exchangeRateMap, filePath);
    }
}
