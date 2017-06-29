package com.graphene.freshguide.model;

/**
 * @author Mingxin Wang
 * @since 2017-06-15 下午9:37
 */
public class EnglishLetter implements Matcher {

  @Override
  public boolean match(char c) {
    return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z';
  }
}
