package com.baobao.domain;
import com.google.common.collect.ComparisonChain;
/**
 * Created by gzx on 17-1-2.
 */
public class Entry implements Comparable<Entry> {
    private String str;
    private int cnt;
    // guava api。按照cnt从小到达排列，相等时，按照str从小到大排列
    public int compareTo(Entry o) {
        return ComparisonChain.start().compare(this.cnt, o.cnt).compare(this.str, o.str).result();
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public void increment(){
        cnt++;
    }
}