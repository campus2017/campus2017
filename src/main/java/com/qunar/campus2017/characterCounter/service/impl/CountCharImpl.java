package com.qunar.campus2017.characterCounter.service.impl;

import com.qunar.campus2017.characterCounter.service.CountChar;
import com.qunar.campus2017.characterCounter.util.Punctuation;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by chunming.xcm on 2017/2/24.
 */

@Service
public class CountCharImpl implements CountChar {
    private static Map<Character, Integer> addToMap(Map<Character, Integer> map, char ch){
        if (map.containsKey(ch))
            map.put(ch, map.get(ch) + 1);
        else
            map.put(ch, 1);
        return map;
    }

    public Map<String, Integer> count(String str) {
        int englishLet = 0;
        int number = 0;
        int chineseChar = 0;
        int punctuation = 0;
        char[] charArray = str.toCharArray();
        Map<Character, Integer> map = new TreeMap<Character, Integer>();
        for (int i = 0; i < str.length(); i++) {
            char temp = charArray[i];
            if (temp >= 'a' && temp <= 'z' || temp >= 'A' && temp <= 'Z'){
                englishLet++;
                addToMap(map, temp);
            }else if (temp >= '0' && temp <= '9'){
                number++;
                addToMap(map, temp);
            }else if (Character.toString(charArray[i]).matches("[\\u4E00-\\u9FA5]+")) { //至少匹配一个汉字
                chineseChar++;
                addToMap(map, temp);
            }else if (Punctuation.isPunctuation(temp)){
                punctuation++;
                addToMap(map, temp);
            }
        }

        List<Map.Entry<Character, Integer>> list = new ArrayList<Map.Entry<Character, Integer>>(
                map.entrySet());
        Comparator<Map.Entry<Character, Integer>> c1 = new Comparator<Map.Entry<Character, Integer>>() {
            public int compare(Map.Entry<Character, Integer> o1,
                               Map.Entry<Character, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        };

        Comparator<Map.Entry<Character, Integer>> c2 = new Comparator<Map.Entry<Character, Integer>>() {
            public int compare(Map.Entry<Character, Integer> o1,
                               Map.Entry<Character, Integer> o2) {
                return o1.getKey() - o2.getKey();
            }
        };
        Collections.sort(list, c2);
        Collections.sort(list, c1);

        Map<String, Integer> result = new HashMap<String, Integer>();

        result.put("englishLet", englishLet);
        result.put("number", number);
        result.put("chineseChar", chineseChar);
        result.put("punctuation", punctuation);
        for (int i = 0; i < 3 && i < list.size(); i++) {
            result.put(Character.toString(list.get(i).getKey()), list.get(i)
                    .getValue());
        }
        return result;
    }

}
