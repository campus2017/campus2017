package com.qunar.model;

import java.util.List;

/**
 * Created by xiazihao on 7/3/17.
 */
public class Result {
    private Data data;
    private List<Most> lisMostNum;

    public Result(Data data, List<Most> mostList) {
        this.data = data;
        this.lisMostNum = mostList;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public List<Most> getLisMostNum() {
        return lisMostNum;
    }

    public void setLisMostNum(List<Most> lisMostNum) {
        this.lisMostNum = lisMostNum;
    }
}
