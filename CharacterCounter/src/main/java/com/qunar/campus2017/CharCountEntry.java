package com.qunar.campus2017;

/**
 * Created by Leviathan on 2017/4/28.
 */
public class CharCountEntry implements Comparable<CharCountEntry> {

    private Character mChar;
    private Integer mCount;

    public CharCountEntry(Character mChar, Integer mCount) {
        this.mChar = mChar;
        this.mCount = mCount;
    }

    @Override
    public int compareTo(CharCountEntry o) {
        return this.mCount.compareTo(o.mCount);
    }

    public Character getmChar() {
        return mChar;
    }

    public Integer getmCount() {
        return mCount;
    }
}
