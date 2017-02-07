package com.qunar.flight.model;

import org.json.JSONString;

import java.io.Serializable;
import java.util.TreeMap;

/**
 * Created by nostalie.zhang on 2017/2/3.
 */
public class TableModel implements Serializable{
    private static final long serialVersionUID = -2453106991559728882L;
    private int number;
    private int EnglishCharacter;
    private int ChineseCharacter;
    private int punctuation;
    private TreeMap mostThree;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getEnglishCharacter() {
        return EnglishCharacter;
    }

    public void setEnglishCharacter(int englishCharacter) {
        EnglishCharacter = englishCharacter;
    }

    public int getChineseCharacter() {
        return ChineseCharacter;
    }

    public void setChineseCharacter(int chineseCharacter) {
        ChineseCharacter = chineseCharacter;
    }

    public int getPunctuation() {
        return punctuation;
    }

    public void setPunctuation(int punctuation) {
        this.punctuation = punctuation;
    }

    public TreeMap getMostThree() {
        return mostThree;
    }

    public void setMostThree(TreeMap mostThree) {
        this.mostThree = mostThree;
    }

    @Override
    public String toString() {
        return "TableModel{" +
                "number=" + number +
                ", EnglishCharacter=" + EnglishCharacter +
                ", ChineseCharacter=" + ChineseCharacter +
                ", punctuation=" + punctuation +
                ", mostThree=" + mostThree +
                '}';
    }

}
