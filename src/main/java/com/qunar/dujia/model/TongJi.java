package com.qunar.dujia.model;

/**
 * Created by tianyiren on 16-12-22.
 */
public class TongJi {
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
        return "TongJi{" +
                "english=" + english +
                ", chinese=" + chinese +
                ", biaodian=" + biaodian +
                ", number=" + number +
                '}';
    }
}
