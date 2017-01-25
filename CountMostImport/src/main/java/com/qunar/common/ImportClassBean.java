package com.qunar.common;

/**
 * Created by gcy0904 on 2017/1/20.
 */
public class ImportClassBean {
    private String ClassName;
    private int count;

    public ImportClassBean() {

    }

    public ImportClassBean(String className, int count) {
        ClassName = className;
        this.count = count;
    }

    @Override
    public String toString() {
        return "ImportClassBean{" +
                "ClassName='" + ClassName + '\'' +
                ", count=" + count +
                '}';
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getClassName() {
        return ClassName;
    }

    public int getCount() {
        return count;
    }
}
