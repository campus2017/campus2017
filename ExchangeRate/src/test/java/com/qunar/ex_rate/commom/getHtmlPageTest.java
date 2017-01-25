package com.qunar.ex_rate.commom;

import org.junit.Test;

/**
 * Created by gcy0904 on 2017/1/20.
 */
public class getHtmlPageTest {

    @Test
    public void testGetHtml() throws Exception {
        String url = "http://www.kuaiyilicai.com/huilv/d-safe-hkd.html";
        String url_str = getHtmlPage.getHtml(url);
        System.out.print(url_str);
    }
}