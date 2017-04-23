package com.qunar.muhongfen.util;


import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * Created by muhongfen on 17/1/9.
 */
public class CharacterUtil {
    //统计各种字符类型的数量
    public static JSONObject CountNum(String str) {
        str = StringUtils.deleteWhitespace(str);
        String E1 = "[\u4e00-\u9fa5]";// 中文
        String E2 = "[a-zA-Z]";// 英文
        String E3 = "[0-9]";// 数字
        int chineseCount = 0;
        int englishCount = 0;
        int numberCount = 0;

        String temp;
        for (int i = 0; i < str.length(); i++) {
            temp = String.valueOf(str.charAt(i));
            if (temp.matches(E1)) {
                chineseCount++;
            }
            if (temp.matches(E2)) {
                englishCount++;
            }
            if (temp.matches(E3)) {
                numberCount++;
            }
        }

        JSONObject params = new JSONObject();
        params.put("中文汉字", chineseCount);
        params.put("英文字母", englishCount);
        params.put("数字", numberCount);
        params.put("中英文标点符号", (str.length() - (chineseCount + englishCount + numberCount)));


        return params;
    }

    //返回出现次数最多的前三个字符
    public static JSONObject maxThreeCounts(String str) {
        if (str == null) {
            throw new NullPointerException("string is null");
        }
        str = StringUtils.deleteWhitespace(str);
        //str.replaceAll("\\s*", "");
        HashMap<Character, Integer> characterCount = (HashMap<Character, Integer>) getCharactersCount(str);
        if (characterCount == null) {
            return null;
        }
        List<Map.Entry<Character, Integer>> listData = new ArrayList<Map.Entry<Character, Integer>>(characterCount.entrySet());
        //按照value值排序
        Collections.sort(listData, new Comparator<Map.Entry<Character, Integer>>() {
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                if ((o2.getValue() - o1.getValue()) > 0)
                    return 1;
                else if ((o2.getValue() - o1.getValue()) == 0) {
                    if ((o2.getKey() - o1.getKey()) > 0)
                        return 1;
                    return 0;
                } else
                    return -1;
            }
        });
        JSONObject params = new JSONObject();
        for (int i = 0; i < 3; i++) {
            params.put(i, listData.get(i).getKey() + ": " + listData.get(i).getValue());
        }
        return params;
    }

    //统计字符出现次数
    private static Map<Character, Integer> getCharactersCount(String str) {
        Map<Character, Integer> characterCounts = new HashMap<Character, Integer>();
        char[] chs = str.toCharArray();
        for (char ch : chs) {
            if (characterCounts.containsKey(ch)) {
                characterCounts.put(ch, characterCounts.get(ch) + 1);
            } else {
                characterCounts.put(ch, 1);
            }
        }
        return characterCounts;

    }
}

