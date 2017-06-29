package com.graphene.freshguide.service;

import java.util.Map;

import com.graphene.freshguide.model.CounterResp;

/**
 * @author Mingxin Wang
 * @since 2017-06-15 下午10:21
 */
public interface CountService {

  Map<Character, CounterResp> count(String text);
}
