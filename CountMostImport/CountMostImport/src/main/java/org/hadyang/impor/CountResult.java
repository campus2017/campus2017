package org.hadyang.impor;

/**
 * Created by AstaYang on 2016/11/13.
 */
public class CountResult implements Comparable<CountResult> {
    String name;
    int count;

    public CountResult(String name, int count) {
        this.name = name;
        this.count = count;
    }

    @Override
    public int compareTo(CountResult o) {
        return count - o.count;
    }

    @Override
    public String toString() {
        return "CountResult{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}
