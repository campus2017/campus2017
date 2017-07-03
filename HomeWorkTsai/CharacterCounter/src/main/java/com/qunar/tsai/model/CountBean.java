package com.qunar.tsai.model;

/**
 * Created by joeTsai on 2017/6/25.
 */

public class CountBean {

    private int enChar; //英文字母的数目；
    private int num; //数字的数目；
    private int chChar; //中文汉字的数目；
    private int punctuation; //标点符号的数目

    private Character topChar1; //出现频率第一的字；
    private int topCount1; //出现频率第一的次数；
    private Character topChar2; //出现频率第二的字；
    private int topCount2; //出现频率第二的次数；
    private Character topChar3; //出现频率第三的字；
    private int topCount3; //出现频率第三的次数；

    public int getEnChar() {
        return enChar;
    }

    public void setEnChar(int enChar) {
        this.enChar = enChar;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getChChar() {
        return chChar;
    }

    public void setChChar(int chChar) {
        this.chChar = chChar;
    }

    public int getPunctuation() {
        return punctuation;
    }

    public void setPunctuation(int punctuation) {
        this.punctuation = punctuation;
    }

    public Character getTopChar1() {
        return topChar1;
    }

    public void setTopChar1(Character topChar1) {
        this.topChar1 = topChar1;
    }

    public int getTopCount1() {
        return topCount1;
    }

    public void setTopCount1(int topCount1) {
        this.topCount1 = topCount1;
    }

    public Character getTopChar2() {
        return topChar2;
    }

    public void setTopChar2(Character topChar2) {
        this.topChar2 = topChar2;
    }

    public int getTopCount2() {
        return topCount2;
    }

    public void setTopCount2(int topCount2) {
        this.topCount2 = topCount2;
    }

    public Character getTopChar3() {
        return topChar3;
    }

    public void setTopChar3(Character topChar3) {
        this.topChar3 = topChar3;
    }

    public int getTopCount3() {
        return topCount3;
    }

    public void setTopCount3(int topCount3) {
        this.topCount3 = topCount3;
    }
}
