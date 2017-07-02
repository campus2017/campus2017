package com.qunar.kefa;

import com.qunar.er.vo.Line;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by Kevin on 2017/6/24.
 */
class SpiderTask implements Callable<Map.Entry<CurrencyEnum, Map<Date, Line>>> {
    private Spider spider;

    public SpiderTask(Spider spider) {
        this.spider = spider;
    }

    @Override
    public Map.Entry<CurrencyEnum, Map<Date, Line>> call() throws Exception {
        return this.spider.getData();
    }
}
