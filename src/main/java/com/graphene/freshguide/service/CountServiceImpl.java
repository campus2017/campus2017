package com.graphene.freshguide.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.graphene.freshguide.model.CounterResp;
import com.graphene.freshguide.model.SymbolType;

/**
 * @author Mingxin Wang
 * @since 2017-06-15 下午10:22
 */
@Service("countService")
public class CountServiceImpl implements CountService {

  @Override
  public Map<Character, CounterResp> count(String text) {
    char[] chars = text.toCharArray();
    Map<Character, CounterResp> typeMap = Maps.newHashMap();
    for (char ch : chars) {
      for (SymbolType type : SymbolType.values()) {
        if (type.matcher.match(ch)) {
          CounterResp resp = typeMap.computeIfAbsent(ch, c -> CounterResp
              .builder()
              .character(c)
              .type(type)
              .count(0)
              .build());
          resp.incrCount();
          break;
        }
      }
    }
    return typeMap;
  }
}
