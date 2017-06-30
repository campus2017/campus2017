package me.woostam;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by woo on 6/30.
 */

@Service
public class CharacterCounterService {
    public List<Word> getTopN(String text, int N) {
        Map<Character, Integer> mp = new HashMap<>();
        for (int i = 0; i < text.length(); ++i) {
            char c = text.charAt(i);
            if (mp.get(c) == null) {
                mp.put(c,1);
            } else {
                mp.put(c,mp.get(c) + 1);
            }
        }

        List<Map.Entry<Character, Integer>> entryList = new ArrayList<>(mp.entrySet());

        Collections.sort(entryList, new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                int v1 = o1.getValue();
                int v2 = o2.getValue();
                return v2 - v1;
            }
        });

        List<Word> list = new ArrayList<>(N);
        int max = entryList.size() < N ? entryList.size() : N;
        for (int i = 0; i < max; ++i) {
            Word word = new Word();
            word.setWord(entryList.get(i).getKey().toString());
            word.setCnt(entryList.get(i).getValue());
            list.add(word);
        }
        return list;
    }

    public TextInfo Count(String text) {
        TextInfo textInfo = new TextInfo();
        int digitCount = 0;
        int enCount = 0;
        int cnCount = 0;
        int punCount = 0;

        for (int i = 0; i < text.length(); ++i) {
            char c = text.charAt(i);
            if (Character.isDigit(c)) {
                ++ digitCount;
            } else if (isCn(c)) {
                ++ cnCount;
            } else if (isEn(c)) {
                ++ enCount;
            } else if (isPun(c)) {
                ++ punCount;
            }
        }
        textInfo.setCnCount(cnCount);
        textInfo.setDigitCount(digitCount);
        textInfo.setEnCount(enCount);
        textInfo.setPunCount(punCount);
        return textInfo;
    }

    private boolean isCn(char c) {
        Character.UnicodeScript sc = Character.UnicodeScript.of(c);
        if (sc == Character.UnicodeScript.HAN) {
            return true;
        }
        return false;
    }

    private boolean isEn(char c) {
        if ((c >= 65 && c <= 90) || (c >= 97 && c <= 122)){
            return true;
        }
        return false;
    }

    private boolean isPun(char c) {
        Pattern pattern = Pattern.compile("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");
        Matcher matcher = pattern.matcher(String.valueOf(c));
        if (matcher.matches()) {
            return true;
        }
        return false;
    }
}
