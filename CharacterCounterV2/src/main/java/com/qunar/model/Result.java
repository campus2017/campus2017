package com.qunar.model;

import java.util.List;

/**
 * Created by xiazihao on 7/3/17.
 */
public class Result {
    private Data data;
    private List<Most> mostList;

    public Result(Data data, List<Most> mostList) {
        this.data = data;
        this.mostList = mostList;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public List<Most> getMostList() {
        return mostList;
    }

    public void setMostList(List<Most> mostList) {
        this.mostList = mostList;
    }
}
