package com.hadyang.counter.bean;

/**
 * Created by HadYang on 14/12/2016.
 */
public class CountData implements Comparable<CountData> {
    public Character c;
    public Integer count;

    public CountData(Character c, Integer count) {
        this.c = c;
        this.count = count;
    }

    @Override
    public int compareTo(CountData o) {
        if (this.count > o.count)
            return 1;
        else if (this.count < o.count) {
            return -1;
        } else {
            return this.c.compareTo(o.c);
        }
    }
}
