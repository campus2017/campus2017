package com.springmvc.CountBean;

/**
 * Created by gcy0904 on 2017/1/24.
 */
public class EveryCountBean {
    private String ChinaChar;
    private String EnglishChar;
    private String Num;
    private String punctuation;

    public EveryCountBean() {
    }

    public EveryCountBean(String chinaChar, String englishChar, String num, String punctuation) {
        ChinaChar = chinaChar;
        EnglishChar = englishChar;
        Num = num;
        this.punctuation = punctuation;
    }

    public void setChinaChar(String chinaChar) {
        ChinaChar = chinaChar;
    }

    public void setEnglishChar(String englishChar) {
        EnglishChar = englishChar;
    }

    public void setNum(String num) {
        Num = num;
    }

    public void setPunctuation(String punctuation) {
        this.punctuation = punctuation;
    }

    public String getChinaChar() {
        return ChinaChar;
    }

    public String getEnglishChar() {
        return EnglishChar;
    }

    public String getNum() {
        return Num;
    }

    public String getPunctuation() {
        return punctuation;
    }
}
