package me.woostam;

/**
 * Created by woo on 6/30.
 */
public class TextInfo {
    private String text;
    private int enCount;
    private int cnCount;
    private int digitCount;
    private int punCount;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getEnCount() {
        return enCount;
    }

    public void setEnCount(int enCount) {
        this.enCount = enCount;
    }

    public int getCnCount() {
        return cnCount;
    }

    public void setCnCount(int cnCount) {
        this.cnCount = cnCount;
    }

    public int getDigitCount() {
        return digitCount;
    }

    public void setDigitCount(int digitCount) {
        this.digitCount = digitCount;
    }

    public int getPunCount() {
        return punCount;
    }

    public void setPunCount(int punCount) {
        this.punCount = punCount;
    }
}
