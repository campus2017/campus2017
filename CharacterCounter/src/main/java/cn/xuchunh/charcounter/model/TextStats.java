package cn.xuchunh.charcounter.model;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * Created on 2017/6/12.
 *
 * @author XCH
 */
public class TextStats implements Serializable {

    // 字母
    private int letterCount;

    // 数字
    private int numberCount;

    // 中文
    private int chineseCount;

    // 标点
    private int punctuationCount;

    //字符数多的前几名
    private LinkedHashMap<Character, Integer> top;

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

    public int getChineseCount() {
        return chineseCount;
    }

    public void setChineseCount(int chineseCount) {
        this.chineseCount = chineseCount;
    }

    public int getPunctuationCount() {
        return punctuationCount;
    }

    public void setPunctuationCount(int punctuationCount) {
        this.punctuationCount = punctuationCount;
    }

    public LinkedHashMap<Character, Integer> getTop() {
        return top;
    }

    public void setTop(LinkedHashMap<Character, Integer> top) {
        this.top = top;
    }

    @Override
    public String toString() {
        return "TextStats{" +
                "letterCount=" + letterCount +
                ", numberCount=" + numberCount +
                ", chineseCount=" + chineseCount +
                ", punctuationCount=" + punctuationCount +
                ", top=" + top +
                '}';
    }
}
