package com.dai.pojo;

/**
 * 单词
 */
public class Words {
    private String key;
    private Integer val;
    public Words(String s){
        key = s;
        val = 1;
    }

    public Words(String s,Integer v){
        key = s;
        val = v;
    }

    public void add(){
        val++;
    }

    public String getKey() {
        return key;
    }

    public Integer getVal() {
        return val;
    }
}

