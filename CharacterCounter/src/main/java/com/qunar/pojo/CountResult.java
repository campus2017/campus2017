package com.qunar.pojo;

/**
 * Created by ZC on 2017/6/21.
 */
public class CountResult {

    private String word1;
    private String word2;
    private String word3;
    private Integer time1;
    private Integer time2;
    private Integer time3;
    private int enWord;
    private int cnWord;
    private int digit;
    private int punctuation;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWord1() {
        return word1;
    }

    public void setWord1(String word1) {
        this.word1 = word1;
    }

    public String getWord2() {
        return word2;
    }

    public void setWord2(String word2) {
        this.word2 = word2;
    }

    public String getWord3() {
        return word3;
    }

    public void setWord3(String word3) {
        this.word3 = word3;
    }

    public Integer getTime1() {
        return time1;
    }

    public void setTime1(Integer time1) {
        this.time1 = time1;
    }

    public Integer getTime2() {
        return time2;
    }

    public void setTime2(Integer time2) {
        this.time2 = time2;
    }

    public Integer getTime3() {
        return time3;
    }

    public void setTime3(Integer time3) {
        this.time3 = time3;
    }

    public int getEnWord() {
        return enWord;
    }

    public void setEnWord(int enWord) {
        this.enWord = enWord;
    }

    public int getCnWord() {
        return cnWord;
    }

    public void setCnWord(int cnWord) {
        this.cnWord = cnWord;
    }

    public int getDigit() {
        return digit;
    }

    public void setDigit(int digit) {
        this.digit = digit;
    }

    public int getPunctuation() {
        return punctuation;
    }

    public void setPunctuation(int punctuation) {
        this.punctuation = punctuation;
    }


}

