package com.qunar.flight.service;

import junit.framework.TestCase;
import org.junit.Test;

public class CounterServiceTest extends TestCase {

    @Test
    public void testContentCounter(){
        String content = "zhangjiangg";
        CounterService contentService = new CounterService();
        contentService.contentCounter(content);
    }
}