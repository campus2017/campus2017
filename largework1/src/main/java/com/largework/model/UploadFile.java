package com.largework.model;

/**
 * Created by liudan on 2017/6/17.
 */
public class UploadFile {
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private String filename;//文件名字
    private String text;//文件内容
}
