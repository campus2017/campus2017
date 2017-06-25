package com.JimmyWang.springmvc;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CharacterCounterService {


    public List<Word> getTop3(String text) {
        Map<Character, Integer> map = new HashMap();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (map.get(c) == null) {
                map.put(c, 1);
            } else {
                map.put(c, map.get(c) + 1);
            }
        }

        List<Map.Entry<Character, Integer>> entryList = new ArrayList<Map.Entry<Character, Integer>>(map.entrySet());
        //降序排序
        Collections.sort(entryList, new Comparator<Map.Entry<Character, Integer>>() {
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                int v1 = o1.getValue();
                int v2 = o2.getValue();
                return v2 - v1;
            }
        });
        List<Word> list = new ArrayList<Word>(3);
        int max = entryList.size() < 3 ? entryList.size() : 3;
        for (int i = 0; i < max; i++) {
            Word word = new Word();
            word.setStr(entryList.get(i).getKey().toString());
            word.setCount(entryList.get(i).getValue());
            list.add(word);
        }
        return list;
    }

    public Word Count(String text) {
        Word word = new Word();
        int digitCount = 0;
        int enCount = 0;
        int chCount = 0;
        int punCOunt = 0;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isDigit(c)) {
                digitCount++;
            } else if (isEn(c)) {
                enCount++;
            } else if (isChinese(c)) {
                chCount++;
            } else if (isPun(c)) {
                punCOunt++;
            }
        }
        word.setEnCount(enCount);
        word.setDigitCount(digitCount);
        word.setCnCount(chCount);
        word.setPunCount(punCOunt);
        return word;
    }

    private boolean isEn(char c) {
        if ((c >= 65 && c <= 90) || (c >= 97 && c <= 122)){
            return true;
        }
        return false;
    }

    private boolean isPun(char c) {
        Pattern pattern = Pattern.compile("\\pP");
        Matcher matcher = pattern.matcher(String.valueOf(c));
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    private boolean isChinese(char c) {
        boolean result = false;
        if (c >= 19968 && c <= 171941) {
            result = true;
        }
        return result;
    }
}