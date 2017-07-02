package com.qunar.kefa;

/**
 * 汇率枚举
 * Created by KevinZhang on 2017/6/26.
 */
public enum CurrencyEnum {
    USD("美元","http://www.kuaiyilicai.com/huilv/d-boc-usd.html"),
    EUR("欧元","http://www.kuaiyilicai.com/huilv/d-boc-eur.html"),
    HKD("港币","http://www.kuaiyilicai.com/huilv/d-boc-hkd.html");

    private String url;
    private String name;

    CurrencyEnum( String name,String url) {
        this.url = url;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }
}
