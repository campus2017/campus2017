package com.qunar.vo;

/**
 * Created by yaoguoli on 2017/6/15.
 */
public class ImportClassVo {
    private String className;//类名
    private int num;//出现的次数

    public String getClassName(){
        return className;
    }

    public void setClassName(String className){
        this.className = className;
    }

    public int getNum(){
        return num;
    }

    public void setNum(int num){
        this.num = num;
    }
}
