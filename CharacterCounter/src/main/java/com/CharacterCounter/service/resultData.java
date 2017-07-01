package com.CharacterCounter.service;

import java.util.*;

import  com.CharacterCounter.data.*;
/**
 * Created by apple on 17/6/19.
 */
public class resultData {

    public int getEn_ch_count() {
        return en_ch_count;
    }

    public void setEn_ch_count(int en_ch_count) {
        this.en_ch_count = en_ch_count;
    }

    public int en_ch_count=0;

    public int getNumber_count() {
        return number_count;
    }

    public void setNumber_count(int number_count) {
        this.number_count = number_count;
    }

    public   int number_count=0;

    public int getChinese_ch_count() {
        return chinese_ch_count;
    }

    public void setChinese_ch_count(int chinese_ch_count) {
        this.chinese_ch_count = chinese_ch_count;
    }

    public   int chinese_ch_count=0;

    public int getPunctuation_count() {
        return punctuation_count;
    }

    public void setPunctuation_count(int punctuation_count) {
        this.punctuation_count = punctuation_count;
    }

    public int punctuation_count=0;

    public List<count> getTop_tree() {
        return top_tree;
    }

    public void setTop_tree(List<count> top_tree) {
        this.top_tree = top_tree;
    }

    public List<count> top_tree;
    public resultData()
    {
        top_tree=new ArrayList<>(3);
    }
}
