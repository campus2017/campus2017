package com.zhenghan.qunar;

import com.google.common.io.Files;
import com.zhenghan.qunar.executor.ExecelConfig;
import com.zhenghan.qunar.executor.Executor;
import com.zhenghan.qunar.executor.ExecutorFactory;

/**
 * Author: 郑含
 * Date: 2016/12/10
 * Time: 23:08
 */
public class Main {
    public static void main(String[] args) {
        Executor executor = ExecutorFactory.getSingleThreadInstanceOf();
        try {
            executor.executeGetRateBean();
            ExecelConfig config = new ExecelConfig();
            config.setExecelTableName("rate汇率execel");
            config.setPath("d:\\name");
            executor.executePoiExecel(config);
        }catch (Exception e){
            e.printStackTrace();
        }
        executor.close();
    }
}
