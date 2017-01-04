package com.zhenghan.qunar.executor;

import com.zhenghan.qunar.RateClient;

/**
 * Created by Administrator on 2016/11/15.
 * 用来获取Executor。
 */
public class ExecutorFactory {
    public static Executor getSingleThreadInstanceOf(){
        return new SingleExecutor(new RateClient());
    }
}
