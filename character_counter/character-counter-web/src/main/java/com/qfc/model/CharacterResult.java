package com.qfc.model;

import java.util.ArrayList;

/**
 * Created by honglin.li on 2017/7/3.
 */
public class CharacterResult {

    private int english_num;
    private int digit_num;
    private int chinese_character_num;
    private int punctuation_num;

    private ArrayList<CharacterInfo> maxRateCharater;

    public CharacterResult() {
        maxRateCharater = new ArrayList<CharacterInfo>();
    }

    public int getEnglish_num() {
        return english_num;
    }

    public void setEnglish_num(int english_num) {
        this.english_num = english_num;
    }

    @Override
    public String toString() {
        return "CharacterResult{" +
                "english_num=" + english_num +
                ", digit_num=" + digit_num +
                ", chinese_character_num=" + chinese_character_num +
                ", punctuation_num=" + punctuation_num +
                ", maxRateCharater=" + maxRateCharater +
                "," + maxRateCharater.size() +
                '}';
    }

    public int getDigit_num() {
        return digit_num;
    }

    public void setDigit_num(int digit_num) {
        this.digit_num = digit_num;
    }

    public int getChinese_character_num() {
        return chinese_character_num;
    }

    public void setChinese_character_num(int chinese_character_num) {
        this.chinese_character_num = chinese_character_num;
    }

    public int getPunctuation_num() {
        return punctuation_num;
    }

    public void setPunctuation_num(int punctuation_num) {
        this.punctuation_num = punctuation_num;
    }

    public ArrayList<CharacterInfo> getMaxRateCharater() {
        return maxRateCharater;
    }

    public void setMaxRateCharater(ArrayList<CharacterInfo> maxRateCharater) {
        this.maxRateCharater = maxRateCharater;
    }

    public void autoIncrementDigitNum() {
        this.digit_num++;
    }

    public void autoIncrementpuncNum() {
        this.punctuation_num++;
    }

    public void autoIncrementEnglishNum() {
       this.english_num++;
    }

    public void autoIncrementChineseCharacterNum() {
        this.chinese_character_num++;
    }

    public void copyMaxCharater(ArrayList<CharacterInfo> charInfos) {
        int size = charInfos.size();

//        System.out.println(charInfos);
        for (int i = 0; i < Math.min(3, size); i++) {
            maxRateCharater.add(charInfos.get(i));
        }
    }
}
