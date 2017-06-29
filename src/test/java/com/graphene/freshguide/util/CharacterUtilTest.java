package com.graphene.freshguide.util;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

import com.google.common.collect.ImmutableMap;

/**
 * @author congyou.wu
 * @since 2017-06-15 下午9:54
 */
public class CharacterUtilTest {

  @Test
  public void isChinese() throws Exception {
    Map<String, Boolean> expectedMap = ImmutableMap
        .<String, Boolean>builder()
        .put("Hello", false)
        .put("你好", true)
        .put("㐀㐂㐄", true)
        .put("にほんご", false)
        .put("조선어", false)
        .build();
    expectedMap.forEach((k, v) -> {
      char[] chars = k.toCharArray();
      for (char aChar : chars) {
        assertEquals(CharacterUtil.isChinese(aChar), v);
      }
    });
  }
}
