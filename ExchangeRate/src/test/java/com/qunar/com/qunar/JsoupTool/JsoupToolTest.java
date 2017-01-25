package com.qunar.com.qunar.JsoupTool;

import com.qunar.ex_rate.commom.getHtmlPage;
import org.junit.Test;

/**
 * Created by gcy0904 on 2017/1/20.
 */
public class JsoupToolTest {

    @Test
    public void testHtmlParse() throws Exception {
        String url_str = "http://www.kuaiyilicai.com/huilv/d-safe-hkd.html";
        String html = getHtmlPage.getHtml(url_str);

        //≤‚ ‘
        JsoupTool.HtmlParse(html);
    }
}