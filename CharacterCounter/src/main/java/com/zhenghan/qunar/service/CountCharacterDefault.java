package com.zhenghan.qunar.service;


import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zhenghan.qunar.po.CharacterStream;
import com.zhenghan.qunar.util.HeapSorted;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Author: 郑含
 * Date: 2016/12/13
 * Time: 11:09
 */
@Service("countCharacter")
public class CountCharacterDefault implements CountCharacter{
    public List<Map.Entry<String,Integer>> sort(CharacterStream characterStream, int top) {
        Map<String,Integer> maps = characterStream.countEveryWords();
        List<Map.Entry<String,Integer>> lists =Lists.newLinkedList(maps.entrySet());
        return HeapSorted.heapSort(lists, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue()-o2.getValue();
            }
        },top);
    }

    public Map<String, Integer> classify(CharacterStream characterStream) {
        Map<String,Integer> map = Maps.newHashMap();
        Map<CharacterStream.CharacterClassify,Integer> characterMap = characterStream.countTypesWords();
        for(Map.Entry<CharacterStream.CharacterClassify,Integer> entry: characterMap.entrySet()){
            map.put(entry.getKey().name(),entry.getValue());
        }
        return map;
    }
}
