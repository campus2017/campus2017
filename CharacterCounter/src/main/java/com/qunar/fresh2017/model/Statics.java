package com.qunar.fresh2017.model;

/**
 * Created by 曹 on 2017.5.17.
 */
public class Statics {
    private Integer english;
    private Integer chinese;
    private Integer biaodian;
    private Integer number;

    public Integer getEnglish() {
        return english;
    }

    public void setEnglish(Integer english) {
        this.english = english;
    }

    public Integer getChinese() {
        return chinese;
    }

    public void setChinese(Integer chinese) {
        this.chinese = chinese;
    }

    public Integer getBiaodian() {
        return biaodian;
    }

    public void setBiaodian(Integer biaodian) {
        this.biaodian = biaodian;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Statics{" +
                "english=" + english +
                ", chinese=" + chinese +
                ", biaodian=" + biaodian +
                ", number=" + number +
                '}';
    }
}
