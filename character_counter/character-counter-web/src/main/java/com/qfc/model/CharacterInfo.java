package com.qfc.model;

/**
 * Created by honglin.li on 2017/7/3.
 */
public class CharacterInfo {
    private Character c;
    private int cnt;

    public Character getC() {
        return c;
    }

    public void setC(Character c) {
        this.c = c;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public CharacterInfo(Character c, int cnt) {
        this.c = c;
        this.cnt = cnt;
    }

    public CharacterInfo() {
    }

    @Override
    public String toString() {
        return "CharacterInfo{" +
                "c=" + c +
                ", cnt=" + cnt +
                '}';
    }
}
