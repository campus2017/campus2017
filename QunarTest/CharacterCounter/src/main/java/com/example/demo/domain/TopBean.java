package com.example.demo.domain;

/**
 * Created by WangWeiyi on 2017/6/28 0028.
 */
public class TopBean implements Comparable<TopBean>{

    char ch;

    int num;

    public char getCh() {
        return ch;
    }

    public void setCh(char ch) {
        this.ch = ch;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public int compareTo(TopBean o) {
        return this.getNum() - o.getNum();
    }
}
