package com.graphene.freshguide.service;

import java.util.Map;

import org.junit.Test;

import com.graphene.freshguide.model.CounterResp;

/**
 * @author congyou.wu
 * @since 2017-06-15 下午10:50
 */
public class CountServiceImplTest {

  @Test
  public void count() throws Exception {
    String text = "Hello, 今天的天气真好呢！";
    CountService countService = new CountServiceImpl();
    Map<Character, CounterResp> respMap = countService.count(text);
    respMap.forEach((k, v) -> System.out.println(k + ", v=" + v));
  }
}
