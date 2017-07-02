package com.qunar.homework.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Created by deep on 2017/6/15.
 */
@Service
public class HomeService {


    public Map<String, Object> countWords(Reader reader) throws IOException {

        Map<String, Object> result = Maps.newHashMap();
        Map<String, Integer> count = Maps.newHashMap();
        Map<String, Integer> top = Maps.newHashMap();

        count.put("number", 0);
        count.put("ascii", 0);
        count.put("chinese", 0);
        count.put("punctuation", 0);

        while (true) {
            int word = reader.read();
            if (word == -1) {
                break;
            }
            if (word >= 0x0030 && word <= 0x0039) {
                count.put("number", count.get("number") + 1);
            } else if ((word >= 0x0041 && word <= 0x005A) || (word >= 0x0061 && word <= 0x007A)) {
                count.put("ascii", count.get("ascii") + 1);
            } else if (word >= 0x4e00 && word <= 0x9fa5) {
                count.put("chinese", count.get("chinese") + 1);
            } else if (word == 13 || word == 10 || word == 32) {
                continue;
            } else {
                count.put("punctuation", count.get("punctuation") + 1);
            }
            int cur = top.get(String.valueOf((char) word)) == null ? 0 : top.get(String.valueOf((char) word));
            top.put(String.valueOf((char) word), cur + 1);

        }
        result.put("count", count);
        List<Map.Entry<String, Integer>> list = Lists.newArrayList(top.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        result.put("top", list.subList(0, list.size() < 3 ? list.size() : 3));
        return result;
    }


}
