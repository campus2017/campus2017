package com.renhao.model;

import java.util.List;

public class CountResult {
    private int alphabet;       // 字母
    private int chinese;        // 汉字
    private int number;         // 数字
    private int punctuation;    // 标点符号
    private List<ChineseCountPair> chineseList;

    public CountResult() {}

    public void setAlphabet(int alphabet) {
        this.alphabet = alphabet;
    }

    public void setChinese(int chinese) {
        this.chinese = chinese;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPunctuation(int punctuation) {
        this.punctuation = punctuation;
    }

    public void setChineseList(List<ChineseCountPair> chineseList) {
        this.chineseList = chineseList;
    }

    public int getAlphabet() {
        return alphabet;
    }

    public int getChinese() {
        return chinese;
    }

    public int getNumber() {
        return number;
    }

    public int getPunctuation() {
        return punctuation;
    }

    public List<ChineseCountPair> getChineseList() {
        return chineseList;
    }
}

