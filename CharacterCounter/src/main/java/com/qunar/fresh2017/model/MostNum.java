package com.qunar.fresh2017.model;

/**
 * Created by 曹 on 2017.5.17.
 */
public class MostNum {
    private String name;
    private Integer num;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "MostNum{" +
                "name='" + name + '\'' +
                ", num=" + num +
                '}';
    }
}
