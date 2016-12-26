package com.qunar.homework.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dayong.gao on 2016/12/12.
 */
public class CountInfo {

    private Integer enLetter;
    private Integer chLetter;
    private Integer num;
    private Integer punctuation;
    private List<Map.Entry<Character,Integer>> top3List;

    @Override public String toString() {
        return "CountInfo{" +
                "enLetter=" + enLetter +
                ", chLetter=" + chLetter +
                ", num=" + num +
                ", punctuation=" + punctuation +
                ", top3List=" + top3List +
                '}';
    }

    public CountInfo() {
        this.enLetter = 0;
        this.chLetter = 0;
        this.num = 0;
        this.punctuation = 0;
        top3List =new ArrayList<Map.Entry<Character,Integer>>();
    }

    public CountInfo(Integer en_letter, Integer ch_letter, Integer num, Integer punctuation) {
        this.enLetter = en_letter;
        this.chLetter = ch_letter;
        this.num = num;
        this.punctuation = punctuation;
        top3List =new ArrayList<Map.Entry<Character,Integer>>();
    }

    public Integer getEn_letter() {
        return enLetter;
    }

    public void setEn_letter(Integer en_letter) {
        this.enLetter = en_letter;
    }

    public Integer getCh_letter() {
        return chLetter;
    }

    public void setCh_letter(Integer ch_letter) {
        this.chLetter = ch_letter;
    }

    public List<Map.Entry<Character, Integer>> getTop3List() {
        return top3List;
    }

    public void setTop3List(List<Map.Entry<Character, Integer>> top3List) {
        this.top3List = top3List;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getPunctuation() {
        return punctuation;
    }

    public void setPunctuation(Integer punctuation) {
        this.punctuation = punctuation;
    }



}
