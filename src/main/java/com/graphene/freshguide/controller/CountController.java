package com.graphene.freshguide.controller;

import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.graphene.freshguide.model.CounterResp;
import com.graphene.freshguide.model.Resp;
import com.graphene.freshguide.model.SymbolType;
import com.graphene.freshguide.service.CountService;

/**
 * @author Mingxin Wang
 * @since 2017-06-15 下午11:03
 */
@Controller
@RequestMapping(value = "/count")
public class CountController {

  @Resource
  private CountService countService;

  @RequestMapping(value = "", method = RequestMethod.POST)
  @ResponseBody
  public Resp count(@RequestBody JSONObject params) {
    String text = params.getString("text");
    Map<SymbolType, Integer> countMap = Maps.newEnumMap(SymbolType.class);
    for (SymbolType type : SymbolType.values()) {
      countMap.put(type, 0);
    }
    Map<Character, CounterResp> respMap = countService.count(text);
    respMap.forEach((k, v) -> countMap.merge(v.getType(), v.getCount(), (a, b) -> a + b));
    Map<String, Integer> statMap = Maps.newHashMap();
    countMap.forEach((k, v) -> statMap.put(format(k), v));
    return Resp.ok(ImmutableMap
        .<String, Object>builder()
        .put("stats", statMap)
        .put("tops", respMap.values()
            .stream()
            .sorted((a, b) -> a.getCount().equals(b.getCount())
                ? a.getCharacter() - b.getCharacter()
                : b.getCount() - a.getCount())
            .limit(3)
            .collect(Collectors.toList()))
        .build());
  }

  private String format(SymbolType type) {
    switch (type) {
      case ENGLISH_LETTER:
        return "english_letter";
      case NUMBER:
        return "number";
      case PUNCTUATION:
        return "punctuation";
      case CHINESE_CHARACTER:
        return "chinese_character";
      default:
        throw new RuntimeException("unsupported symbol type");
    }
  }
}
