package com.example.demo.domain;

/**
 * Created by WangWeiyi on 2017/5/31 0031.
 */
public class CountBean {
    //中文字符
    int cnChar;
    //英文字符
    int enChar;
    //标点
    int puncChar;
    //数字
    int digitChar;
    //其他字符
    int otherChar;

    public CountBean(){
        this.cnChar = 0;
        this.enChar = 0;
        this.digitChar = 0;
        this.puncChar = 0;
        this.otherChar = 0;
    }



    public void cnIncrement(){
        this.cnChar ++;
    }
    public void enIncrement(){
        this.enChar ++;
    }

    public void digitIncrement(){
        this.digitChar ++;
    }

    public void puncIncrement(){
        this.puncChar ++;
    }
    public void otherIncrement(){
        this.otherChar++;
    }

    public int getCnChar() {
        return cnChar;
    }

    public void setCnChar(int cnChar) {
        this.cnChar = cnChar;
    }

    public int getEnChar() {
        return enChar;
    }

    public void setEnChar(int enChar) {
        this.enChar = enChar;
    }

    public int getPuncChar() {
        return puncChar;
    }

    public void setPuncChar(int puncChar) {
        this.puncChar = puncChar;
    }

    public int getDigitChar() {
        return digitChar;
    }

    public void setDigitChar(int digitChar) {
        this.digitChar = digitChar;
    }

    public int getOtherChar() {
        return otherChar;
    }

    public void setOtherChar(int otherChar) {
        this.otherChar = otherChar;
    }
}
