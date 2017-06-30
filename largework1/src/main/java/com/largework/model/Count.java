package com.largework.model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by liudan on 2017/6/16.
 */
public class Count {

    public Count(int englishWordNum, int chineseWordNum, int numberNum, int punctuationNum, LinkedHashMap map) {
        this.englishWordNum = englishWordNum;
        this.chineseWordNum = chineseWordNum;
        this.numberNum = numberNum;
        this.punctuationNum = punctuationNum;
        this.map = map;
    }

    public Count(){

    }

    public int getEnglishWordNum() {
        return englishWordNum;
    }

    public void setEnglishWordNum(int englishWordNum) {
        this.englishWordNum = englishWordNum;
    }

    public int getChineseWordNum() {
        return chineseWordNum;
    }

    public void setChineseWordNum(int chineseWordNum) {
        this.chineseWordNum = chineseWordNum;
    }

    public int getNumberNum() {
        return numberNum;
    }

    public void setNumberNum(int numberNum) {
        this.numberNum = numberNum;
    }

    public int getPunctuationNum() {
        return punctuationNum;
    }

    public void setPunctuationNum(int punctuationNum) {
        this.punctuationNum = punctuationNum;
    }

    private int englishWordNum;
    private int chineseWordNum;
    private int numberNum;
    private int punctuationNum;

    public LinkedHashMap getMap() {
        return map;
    }

    public void setMap(LinkedHashMap map) {
        this.map = map;
    }

    private LinkedHashMap map=new LinkedHashMap<String,Integer>();

}
