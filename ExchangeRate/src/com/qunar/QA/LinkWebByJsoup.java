package com.qunar.QA;

/**
 * @author  Nicole
 * @Time  2017/7/2
 * @Description 连接网页
 */

import java.io.IOException;

import java.net.SocketTimeoutException;

import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;

public class LinkWebByJsoup {

    public static Document linkWebByJsoup(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).timeout(5000).get();
        } catch (SocketTimeoutException e) {
            System.out.println("Socket连接超时");
        } catch (IOException e){
            e.printStackTrace();
        }
        return doc;
    }

}
