package com.graphene.freshguide.model;

import com.graphene.freshguide.util.CharacterUtil;

/**
 * @author Mingxin Wang
 * @since 2017-06-15 下午9:40
 */
public class ChineseCharacter implements Matcher {

  @Override
  public boolean match(char c) {
    return CharacterUtil.isChinese(c);
  }
}
