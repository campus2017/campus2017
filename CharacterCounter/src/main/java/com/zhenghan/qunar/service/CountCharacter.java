package com.zhenghan.qunar.service;


import com.zhenghan.qunar.po.CharacterStream;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;

/**
 * Author: 郑含
 * Date: 2016/12/13
 * Time: 10:38
 */
public interface CountCharacter {
    public  List<Map.Entry<String,Integer>>  sort(CharacterStream characterStream, int top);
    public Map<String,Integer> classify(CharacterStream characterStream);
}
