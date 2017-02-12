package com.springmvc.CountBean;

/**
 * Created by gcy0904 on 2017/1/24.
 */
public class CharCountBean {
    private String CharName;
    private int CharCount;

    public CharCountBean() {
    }

    public CharCountBean(String charName, int charCount) {
        CharName = charName;
        CharCount = charCount;
    }

    public String getCharName() {
        return CharName;
    }

    public void setCharName(String charName) {
        CharName = charName;
    }

    public int getCharCount() {
        return CharCount;
    }

    public void setCharCount(int charCount) {
        CharCount = charCount;
    }
}
