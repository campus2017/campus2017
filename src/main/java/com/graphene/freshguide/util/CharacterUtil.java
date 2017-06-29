package com.graphene.freshguide.util;

/**
 * @author Mingxin Wang
 * @since 2017-06-15 下午9:42
 */
public final class CharacterUtil {

  public static boolean isChinese(char c) {
    return Character.UnicodeScript.of(c) == Character.UnicodeScript.HAN;
  }
}
