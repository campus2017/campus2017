package com.qunar.wireless.mlx;

/**
 * Created by mlx on 2016-12-23.
 */
public interface ICrawler {
    String getHtml(String url);
    void close();
    int getStatus();
}