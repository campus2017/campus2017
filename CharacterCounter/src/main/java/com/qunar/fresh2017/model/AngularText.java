package com.qunar.fresh2017.model;

/**
 * Created by 曹 on 2017.5.17.
 */
public class AngularText {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "AngularText{" +
                "text='" + text + '\'' +
                '}';
    }
}
