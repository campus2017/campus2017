package com.graphene.freshguide.model;

/**
 * @author Mingxin Wang
 * @since 2017-06-15 下午10:05
 */
public class Punctuation implements Matcher {

  @Override
  public boolean match(char c) {
    if (0x21 <= c && c <= 0x22) return true;
    if (c == 0x27 || c == 0x2C) return true;
    if (c == 0x2E || c == 0x3A) return true;
    if (c == 0x3B || c == 0x3F) return true;
    if (0x3001 <= c && c <= 0x3003) return true;
    if (0x301D <= c && c <= 0x301F) return true;
    if (0x2018 <= c && c <= 0x201F) return true;
    if (c == 0xFF01 || c == 0xFF02) return true;
    if (c == 0xFF07 || c == 0xFF0C) return true;
    if (c == 0xFF1A || c == 0xFF1B) return true;
    if (c == 0xFF1F || c == 0xFF61) return true;
    if (c == 0xFF0E) return true;
    if (c == 0xFF65) return true;
    return false;
  }
}
