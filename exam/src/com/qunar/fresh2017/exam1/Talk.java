package com.qunar.fresh2017.exam1;

/**
 * Created by muhongfen on 17/4/14.
 */
public class Talk implements Comparable<Talk> {
    private String name;
    private String time;
    private String context;

    public Talk(String name, String time, String context) {
        this.name = name;
        this.time = time;
        this.context = context;
    }
    public String getContext(){
        return this.context;
    }
    public String getName(){
        return this.name;
    }

    @Override
    public int compareTo(Talk t) {
        if (this.time.compareTo(t.time) > 0) {
            return 1;
        }
        else if (this.time.compareTo(t.time) < 0) {
            return -1;
        }
        else if (this.name.compareTo(t.name) > 0) {
            return 1;
        }
        else if (this.name.compareTo(t.name) < 0) {
            return -1;
        }
        return 0;
    }

}
