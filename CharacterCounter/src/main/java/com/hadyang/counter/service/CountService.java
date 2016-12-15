package com.hadyang.counter.service;

import com.hadyang.counter.TopK;
import com.hadyang.counter.bean.CountData;
import com.hadyang.counter.bean.JsonResult;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by HadYang on 13/12/2016.
 */

@Service
public class CountService {
    public JsonResult count(String txt) {
        txt = txt.replaceAll("\\s*", "");
        System.out.println("receive from client : " + txt);

        JsonResult result = new JsonResult();

        HashMap<Character, Integer> num = new HashMap<>();

        for (int i = 0; i < txt.length(); i++) {
            char c = txt.charAt(i);

            //中文
            if ((c >= 0x4e00) && (c <= 0x9fbb)) {
                result.chineseCount++;
            } else if ((c >= 'a' && c <= 'z')
                    || (c >= 'A' && c <= 'Z')) {
                //英文字母
                result.letterCount++;
            } else if (c >= '0' && c <= '9') {
                //阿拉伯数字
                result.numCount++;
            } else {
                result.punctuationCount++;
            }

            if (num.containsKey(c))
                num.put(c, num.get(c) + 1);
            else {
                num.put(c, 1);
            }
        }

        List<CountData> countDatas = new ArrayList<>();
        for (Iterator<Character> iterator = num.keySet().iterator(); iterator.hasNext(); ) {
            Character next = iterator.next();
            countDatas.add(new CountData(next, num.get(next)));
        }

        List<CountData> topK = new TopK<CountData>().getTopK(countDatas, 3);
        Collections.sort(topK, new Comparator<CountData>() {
            @Override
            public int compare(CountData o1, CountData o2) {
                return o1.compareTo(o2);
            }
        });
        result.top = topK;

        System.out.println("Return to client " + result);
        return result;
    }
}
