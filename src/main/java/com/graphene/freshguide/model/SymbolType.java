package com.graphene.freshguide.model;

/**
 * @author Mingxin Wang
 * @since 2017-06-15 下午9:32
 */
public enum SymbolType {

  ENGLISH_LETTER(0, "english letter", new EnglishLetter()),
  CHINESE_CHARACTER(1, "chinese character", new ChineseCharacter()),
  PUNCTUATION(2, "punctuation", new Punctuation()),
  NUMBER(3, "number", new Number()),;

  public int code;
  public String desc;
  public Matcher matcher;

  SymbolType(int code, String desc, Matcher matcher) {
    this.code = code;
    this.desc = desc;
    this.matcher = matcher;
  }
}
