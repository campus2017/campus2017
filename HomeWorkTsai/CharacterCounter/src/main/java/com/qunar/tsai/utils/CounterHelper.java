package com.qunar.tsai.utils;

import com.qunar.tsai.model.CountBean;

import java.util.*;

/**
 * Created by joeTsai on 2017/6/25.
 */

public class CounterHelper {

    public static void countCharacter(String str, CountBean bean) {

        Map<Character, Integer> charCountMap = new HashMap<>();

        int enChar = 0;
        int num = 0;
        int chChar = 0;
        int punctuation = 0;

        for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);

                if (isEnChar(c)) { //英文字符的判定
                    enChar++;
                } else if (isChChar(c)) { //中文字符的判定
                    chChar++;
                } else if (isNum(c)) {
                    num++;
                } else if (isChPunctuation(c) || isEnPunctuation(c)) {
                    punctuation++;
                }
                if(c != ' '){
                    if (charCountMap.containsKey(c)) {
                        charCountMap.put(c, charCountMap.get(c) + 1);
                    } else {
                        charCountMap.put(c, 1);
                    }
                }

        }

        List<Map.Entry<Character, Integer>> entryList = new ArrayList<>(charCountMap.entrySet());
        entryList.sort(new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        bean.setEnChar(enChar);
        bean.setChChar(chChar);
        bean.setNum(num);
        bean.setPunctuation(punctuation);

        bean.setTopChar1(entryList.get(0).getKey());
        bean.setTopCount1(entryList.get(0).getValue());

        bean.setTopChar2(entryList.get(1).getKey());
        bean.setTopCount2(entryList.get(1).getValue());

        bean.setTopChar3(entryList.get(2).getKey());
        bean.setTopCount3(entryList.get(2).getValue());
    }


    /**
     * 判定字符是否为中文字符
     *
     * @param c
     * @return
     */
    private static boolean isChChar(char c) {
        Character.UnicodeScript sc = Character.UnicodeScript.of(c);
        return sc == Character.UnicodeScript.HAN;
    }

    /**
     * 判定字符是否为数字
     *
     * @param c
     * @return
     */
    private static boolean isNum(char c) {
        return c >= '0' && c <= '9';
    }

    /**
     * 判定是否为英文字符
     *
     * @param c
     * @return
     */
    private static boolean isEnChar(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }

    /**
     * 判定是否为中文标点
     *
     * @param c
     * @return
     */
    private static boolean isChPunctuation(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS
                || ub == Character.UnicodeBlock.VERTICAL_FORMS;
    }

    /**
     * 判定是否为英文标点
     *
     * @param c
     * @return
     */
    private static boolean isEnPunctuation(char c) {
        return (0x21 <= c && c <= 0x22)
                || (c == 0x27 || c == 0x2C)
                || (c == 0x2E || c == 0x3A)
                || (c == 0x3B || c == 0x3F);
    }

}
