package com.qunar.dujia.model;

/**
 * Created by tianyiren on 16-12-22.
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
