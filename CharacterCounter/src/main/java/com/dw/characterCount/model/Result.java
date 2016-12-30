package com.dw.characterCount.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by DW on 2016/12/27.
 */
@Component
@Scope("prototype")
public class Result {
    //英文字母的数量
    private int eng;
    //中文汉子的数量
    private int chn;
    //数字的数量
    private int num;
    //标点符号的数量
    private int pun;
    //按序存放出现最多的几个字符
    private List<Character> ch_list;
    //按序存放出现最多的字符次数
    private List<Integer> val_list;

    public int getEng() {
        return eng;
    }

    public void setEng(int eng) {
        this.eng = eng;
    }

    public int getChn() {
        return chn;
    }

    public void setChn(int chn) {
        this.chn = chn;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getPun() {
        return pun;
    }

    public void setPun(int pun) {
        this.pun = pun;
    }

    public List<Character> getCh_list() {
        return ch_list;
    }

    public void setCh_list(List<Character> ch_list) {
        this.ch_list = ch_list;
    }

    public List<Integer> getVal_list() {
        return val_list;
    }

    public void setVal_list(List<Integer> val_list) {
        this.val_list = val_list;
    }

    public void setAll(int[] arr){
        this.eng = arr[0];
        this.chn = arr[1];
        this.num = arr[2];
        this.pun = arr[3];
    }
}
