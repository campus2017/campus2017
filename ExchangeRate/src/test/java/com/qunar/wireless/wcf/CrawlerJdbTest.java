package com.qunar.wireless.wcf;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by wcf on 2016-12-23.
 */
public class CrawlerJdbTest {
    ICrawler crawler = null;
    @Before
    public void setUp() throws Exception {
        crawler = new CrawlerJdb();
    }

    @After
    public void tearDown() throws Exception {
        crawler.close();
    }

    @Test
    public void getHtml1() throws Exception {
        String url1 = "http://www.kuaiyilicai.com/bank/rmbfx/b-safe.html";
        String html = null;

        html = crawler.getHtml(url1);
        System.out.println("=========================================================");
        System.out.println("第一个url返回的页面:");
        System.out.println(html);
        System.out.println("=========================================================");
        assertEquals(crawler.getStatus(),200);

    }

    @Test
    public void getHtml2() throws Exception {
        String url2 = "http://www.pbc.gov.cn/zhengcehuobisi/125207/125217/125925/index.html";

        String html = null;

        html = crawler.getHtml(url2);
        System.out.println("=========================================================");
        System.out.println("第二个url返回的页面:");
        System.out.println(html);
        System.out.println("=========================================================");
        assertEquals(crawler.getStatus(),200);
    }

}