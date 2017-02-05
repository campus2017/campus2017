package com.qunar.hw.character_counter.bean;

import java.math.BigDecimal;

/**
 * Created by runsheng.zhou on 2017/2/5.
 */
public class StatisticsInfo {
    private long letters;
    private long numbers;
    private long chineseChars;
    private long punctuations;

    public long getLetters() {
        return letters;
    }

    public void setLetters(long letters) {
        this.letters = letters;
    }

    public long getNumbers() {
        return numbers;
    }

    public void setNumbers(long numbers) {
        this.numbers = numbers;
    }

    public long getChineseChars() {
        return chineseChars;
    }

    public void setChineseChars(long chineseChars) {
        this.chineseChars = chineseChars;
    }

    public long getPunctuations() {
        return punctuations;
    }

    public void setPunctuations(long punctuations) {
        this.punctuations = punctuations;
    }
}
