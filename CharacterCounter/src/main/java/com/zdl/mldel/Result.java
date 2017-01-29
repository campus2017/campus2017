package com.zdl.mldel;

/**
 * Created by zdl on 2017/1/29.
 */

public class Result {
    private int characters;
    private int numbers;
    private int chineses;
    private int punctuations;
    private String name1;
    private int value1;
    private String name2;
    private int value2;
    private String name3;
    private int value3;

    public void setCharacters(int characters) {
        this.characters = characters;
    }
    public void setNumbers(int numbers) {
        this.numbers = numbers;
    }

    public void setChineses(int chineses) {
        this.chineses = chineses;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public void setName3(String name3) {
        this.name3 = name3;
    }

    public void setPunctuations(int punctuations) {
        this.punctuations = punctuations;
    }

    public void setValue1(int value1) {
        this.value1 = value1;
    }

    public void setValue2(int value2) {
        this.value2 = value2;
    }

    public void setValue3(int value3) {
        this.value3 = value3;
    }
}
