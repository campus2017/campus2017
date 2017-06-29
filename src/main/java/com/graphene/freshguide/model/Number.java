package com.graphene.freshguide.model;

/**
 * @author Mingxin Wang
 * @since 2017-06-15 下午9:39
 */
public class Number implements Matcher {

  @Override
  public boolean match(char c) {
    return c >= '0' && c <= '9';
  }
}
