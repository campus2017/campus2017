package com.zhenghan.qunar.bean;

import com.google.common.base.Preconditions;

import java.math.BigDecimal;
import java.util.Date;
import java.util.SortedMap;

/**
 * Created by Administrator on 2016/11/14.
 * 1美元对人民币6.8291元，1欧元对人民币7.3808元，
 * 100日元对人民币6.3835元，
 * 1港元对人民币0.88020元
 * ，1英镑对人民币8.5806元，
 * 1澳大利亚元对人民币5.1501元，
 * 1新西兰元对人民币4.8446元，
 * 1新加坡元对人民币4.8248元，
 * 1瑞士法郎对人民币6.8976元，
 * 1加拿大元对人民币5.0471元，
 * 人民币1元对0.63083林吉特，人民币1元对9.6424俄罗斯卢布, 人民币1元对2.0915南非兰特，人民币1元对171.01韩元，人民币1元对0.53783阿联酋迪拉姆，人民币1元对0.54915沙特里亚尔。
 */
public class RateBean implements Comparable{
    /**
     * 汇率日期
     */
    private Date date;

    /**
     * 汇率连接地址
     */
    private String url;

    /**
     * 存储不同的汇率对应价格
     * 对应1元
     */
    private SortedMap<String,BigDecimal> map;

    public RateBean(Date date, String url, SortedMap<String, BigDecimal> map) {
        this.date = date;
        this.url = url;
        this.map = map;
    }

    public RateBean() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public SortedMap<String, BigDecimal> getMap() {
        return map;
    }

    public void setMap(SortedMap<String, BigDecimal> map) {
        this.map = map;
    }

    public int compareTo(Object o) {
        Preconditions.checkArgument(o instanceof RateBean,"必须为RateBean类型参数");
        return date.compareTo(((RateBean)o).getDate());
    }
    @Override
    public String toString() {
        return "RateBean{" +
                "date=" + date +
                ", url='" + url + '\'' +
                ", map=" + map +
                '}';
    }


}
