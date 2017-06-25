package com.charactercounter.model;

import java.util.Map;

/**
 * Created by canda on 6/19/17.
 */
public class CounterResult {
    private String inputString;
    private int letterCount;
    private int numberCount;
    private int wordOfCnCount;
    private int punctutionCount;
    private Character firstFrequencyKey;
    private Integer firstFrequencyValue;
    private Character secondFrequencyKey;
    private Integer secondFrequencyValue;
    private Character thirdFrequencyKey;
    private Integer thirdFrequencyValue;


    public String getInputString() {
        return inputString;
    }

    public void setInputString(String inputString) {
        this.inputString = inputString;
    }

    public int getLetterCount() {
        return letterCount;
    }

    public void setLetterCount(int letterCount) {
        this.letterCount = letterCount;
    }

    public int getNumberCount() {
        return numberCount;
    }

    public void setNumberCount(int numberCount) {
        this.numberCount = numberCount;
    }

    public int getWordOfCnCount() {
        return wordOfCnCount;
    }

    public void setWordOfCnCount(int wordOfCnCount) {
        this.wordOfCnCount = wordOfCnCount;
    }

    public int getPunctutionCount() {
        return punctutionCount;
    }

    public void setPunctutionCount(int punctutionCount) {
        this.punctutionCount = punctutionCount;
    }

    public Character getFirstFrequencyKey() {
        return firstFrequencyKey;
    }

    public void setFirstFrequencyKey(Character firstFrequencyKey) {
        this.firstFrequencyKey = firstFrequencyKey;
    }

    public Integer getFirstFrequencyValue() {
        return firstFrequencyValue;
    }

    public void setFirstFrequencyValue(Integer firstFrequencyValue) {
        this.firstFrequencyValue = firstFrequencyValue;
    }

    public Character getSecondFrequencyKey() {
        return secondFrequencyKey;
    }

    public void setSecondFrequencyKey(Character secondFrequencyKey) {
        this.secondFrequencyKey = secondFrequencyKey;
    }

    public Integer getSecondFrequencyValue() {
        return secondFrequencyValue;
    }

    public void setSecondFrequencyValue(Integer secondFrequencyValue) {
        this.secondFrequencyValue = secondFrequencyValue;
    }

    public Character getThirdFrequencyKey() {
        return thirdFrequencyKey;
    }

    public void setThirdFrequencyKey(Character thirdFrequencyKey) {
        this.thirdFrequencyKey = thirdFrequencyKey;
    }

    public Integer getThirdFrequencyValue() {
        return thirdFrequencyValue;
    }

    public void setThirdFrequencyValue(Integer thirdFrequencyValue) {
        this.thirdFrequencyValue = thirdFrequencyValue;
    }
}
