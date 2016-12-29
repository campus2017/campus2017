package com.qunar.wireless.wcf;

import java.util.Map;

/**
 * Created by wcf on 2016-12-23.
 */
public interface IHtmlParser {
    // 解析出 hkd, usd, eur 对应的每日汇率页面的 url
    Map<String,String> getDataUrl(String html);

    // 解析出 每天对应的汇率
    Map<String,String> getRate(String html);
}