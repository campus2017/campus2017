package com.renhao.model;

import java.text.Collator;
import java.util.Locale;

public class ChineseCountPair implements Comparable<ChineseCountPair> {

    private String chinese;
    private int count;

    public ChineseCountPair(String chinese, int count) {
        this.chinese = chinese;
        this.count = count;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getChinese() {
        return chinese;
    }

    public int getCount() {
        return count;
    }

    public int compareTo(ChineseCountPair object) {
        if (object.getCount() == this.getCount()) {
            Collator instance = Collator.getInstance(Locale.CHINA);
            return instance.compare(this.getChinese(), object.getChinese());
        } else {
            return object.getCount() - this.getCount();
        }
    }
}