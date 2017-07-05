package com.qfc.service;

import com.qfc.model.CharacterInfo;
import com.qfc.model.CharacterResult;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by honglin.li on 2017/7/3.
 */
public class CountText {

    private static Pattern patPattern = Pattern.compile("^[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘;；：”“'。，、？]$");
    private static Pattern chineseCharacterPattern = Pattern.compile("[\u4e00-\u9fa5]");
    private static Pattern digitPattern = Pattern.compile("^[0-9]$");
    private static Pattern englishPattern = Pattern.compile("^[a-zA-Z]$");

    public static CharacterResult countText(String text) {

        System.out.println(text);
        CharacterResult characterResult = new CharacterResult();

        SortedMap<String, Integer> count_map = new TreeMap<String, Integer>();

        int len = text.length();
        for (int i = 0; i < len; i++) {

            String substring = text.substring(i, i + 1);

            if (digitPattern.matcher(substring).find()) {
                System.out.println(text.charAt(i));
                characterResult.autoIncrementDigitNum();
            }

            if (patPattern.matcher(substring).find()) {
                System.out.println(text.charAt(i));
                characterResult.autoIncrementpuncNum();
            }

            if (chineseCharacterPattern.matcher(substring).find()) {
                System.out.println(text.charAt(i));
                characterResult.autoIncrementChineseCharacterNum();
            }

            if (englishPattern.matcher(substring).find()) {
                System.out.println(text.charAt(i));
                characterResult.autoIncrementEnglishNum();
            }

            if (count_map.get(substring) == null) {
                count_map.put(substring, 1);
            } else {
                int cnt = count_map.get(substring);
                count_map.put(substring, cnt + 1);
            }

        }

        Iterator it = count_map.keySet().iterator();
        ArrayList<CharacterInfo> charInfos = new ArrayList<CharacterInfo>();

        while (it.hasNext()) {
            String key = (String) it.next();
//            System.out.println(key + " : " + count_map.get(key));
            CharacterInfo charInfo = new CharacterInfo(key.charAt(0), count_map.get(key));
            charInfos.add(charInfo);
        }
        Collections.sort(charInfos, (Comparator<? super CharacterInfo>) new SortByCnt());
        characterResult.copyMaxCharater(charInfos);

        return characterResult;
    }

    public static class SortByCnt implements Comparator {

        @Override
        public int compare(Object o1, Object o2) {

            CharacterInfo c1 = (CharacterInfo) o1;
            CharacterInfo c2 = (CharacterInfo) o2;

            if (c2.getCnt() == c1.getCnt()) {
//                字典序排序
               return c1.getC() - c2.getC();
            }
            return c2.getCnt() - c1.getCnt();
        }

    }

}
