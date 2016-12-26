package com.qunar.dujia.model;

/**
 * Created by tianyiren on 16-12-22.
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
