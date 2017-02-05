package com.qunar.bean;

/**
 * Created by zhuyin on 17-1-26.
 */
public class ResultJson {
    int englishCount = 0;
    int chineseCount = 0;
    int numCount = 0;
    int punctuationCount = 0;
    String top1 = "";
    String top2 = "";
    String top3 = "";
    int topN1 = 0;
    int topN2 = 0;
    int topN3 = 0;

    public int getEnglishCount() {
        return englishCount;
    }

    public void setEnglishCount(int englishCount) {
        this.englishCount = englishCount;
    }

    public int getChineseCount() {
        return chineseCount;
    }

    public void setChineseCount(int chineseCount) {
        this.chineseCount = chineseCount;
    }

    public int getNumCount() {
        return numCount;
    }

    public void setNumCount(int numCount) {
        this.numCount = numCount;
    }

    public int getPunctuationCount() {
        return punctuationCount;
    }

    public void setPunctuationCount(int punctuationCount) {
        this.punctuationCount = punctuationCount;
    }

    public String getTop1() {
        return top1;
    }

    public void setTop1(String top1) {
        this.top1 = top1;
    }

    public String getTop2() {
        return top2;
    }

    public void setTop2(String top2) {
        this.top2 = top2;
    }

    public String getTop3() {
        return top3;
    }

    public void setTop3(String top3) {
        this.top3 = top3;
    }

    public int getTopN1() {
        return topN1;
    }

    public void setTopN1(int topN1) {
        this.topN1 = topN1;
    }

    public int getTopN2() {
        return topN2;
    }

    public void setTopN2(int topN2) {
        this.topN2 = topN2;
    }

    public int getTopN3() {
        return topN3;
    }

    public void setTopN3(int topN3) {
        this.topN3 = topN3;
    }
}
