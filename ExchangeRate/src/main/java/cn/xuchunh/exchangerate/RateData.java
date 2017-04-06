package cn.xuchunh.exchangerate;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

import java.util.List;

/**
 * Created on 2017/4/6.
 *
 * 简单汇率数据类，只实现表头和数据的设置，其他数据一致性检查、数据添加等暂未实现
 *
 * @author XCH
 */
public class RateData {

    // TODO: 2017/4/6 数据一致性检查、数据的添加删除、表头的添加删除、改变可直接操作headers和data的行为

    private List<String> headers;

    private Multimap<String, Double> data;

    public RateData() {
        headers = Lists.newArrayList();
        data = HashMultimap.create();
    }

    public RateData(List<String> headers, Multimap<String, Double> data) {
        this.headers = headers;
        this.data = data;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public Multimap<String, Double> getData() {
        return data;
    }

    public void setData(Multimap<String, Double> data) {
        this.data = data;
    }
}
