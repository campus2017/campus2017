package com.zhenghan.qunar.executor;

import com.zhenghan.qunar.RateClient;

import java.io.IOException;

/**
 * Created by Administrator on 2016/11/15.
 * Executor抽象类
 */
public abstract class Executor {
    protected RateClient client = null;
    /**
     * 主机地址
     */
    protected String host = "http://www.pbc.gov.cn";
    /**
     * 主机uri
     */
    protected String uri = "/zhengcehuobisi/125207/125217/125925/index.html";

    /**
     * 需要爬取的天数
     */
    protected  int days =30;
    public Executor(RateClient rateClient) {
        this.client = rateClient;
    }

    /**
     * 用户直接调用该方法自动完成后会关闭client
     */
    public  void executeGetRateBean(){
        doExecuteGetRateBean();
        try {
            client.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 子类重写该方法调用
     * executeGetRateBean会直接关闭客户端的连接
     */
    protected abstract void doExecuteGetRateBean();

    /**
     * 客户端直接执行poi生成Execel的方法
     */
    public abstract void executePoiExecel(ExecelConfig confi);

    /**
     * 该方法将关闭客户端的连接池如果还需要使用请不要进行关闭
     */
    public void close(){
        RateClient.shutdownPooling();
    }
}
