package com.qunar.wireless.wcf;

/**
 * Created by wcf on 2016-12-23.
 */
public interface ICrawler {
    String getHtml(String url);
    void close();
    int getStatus();
}