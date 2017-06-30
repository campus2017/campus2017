package com.qunar.homework.service;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.util.Map;

/**
 * Created by deep on 2017/6/15.
 */
@Service
public class HomeService {


    public Map<String, Integer> countWords(Reader reader) throws IOException {


        Map<String, Integer> result = Maps.newHashMap();

        result.put("number", 0);
        result.put("ascii", 0);
        result.put("chinese", 0);
        result.put("punctuation",0);

        while (true) {
            int word = reader.read();
            if (word == -1) {
                break;
            }

            if (word >= 0x0030 && word <= 0x0039) {
                result.put("number", result.get("number") + 1);
            } else if ((word >= 0x0041 && word <= 0x005A) || (word >= 0x0061 && word <= 0x007A)) {
                result.put("ascii", result.get("ascii") + 1);
            } else if (word >= 0x4e00 && word <= 0x9fa5) {
                result.put("chinese", result.get("chinese") + 1);
            } else {
                result.put("punctuation", result.get("punctuation") + 1);
            }

        }

        return result;
    }


}
