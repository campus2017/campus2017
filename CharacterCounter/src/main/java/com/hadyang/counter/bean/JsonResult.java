package com.hadyang.counter.bean;

import java.util.List;

/**
 * Created by HadYang on 14/12/2016.
 */
public class JsonResult {
    public int letterCount;
    public int numCount;
    public int chineseCount;
    public int punctuationCount;

    public List<CountData> top;


    @Override
    public String toString() {
        return "JsonResult{" +
                "letterCount=" + letterCount +
                ", numCount=" + numCount +
                ", chineseCount=" + chineseCount +
                ", punctuationCount=" + punctuationCount +
                ", top=" + top +
                '}';
    }
}
