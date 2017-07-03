package charactercounter.service.impl;

import charactercounter.Entity.CharacterCount;
import charactercounter.Entity.CharacterCounterResult;
import charactercounter.service.CharacterCounterService;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by admin on 2017/2/6.
 */
@Service
public class CharacterCounterServiceImpl implements CharacterCounterService{
    public CharacterCounterResult count(String str) {
        int chinese = 0;
        int english = 0;
        int number = 0;
        int punctuation = 0;
        Map<Character, Integer> counter = new HashMap<Character, Integer>();

        // 遍历字符串统计数量
        for (int i=0; i<str.length(); ++i) {
            char ch = str.charAt(i);
            if (isBasicLatin(ch)) {
                if (Character.isLetter(ch)) {
                    ++english;
                } else if (Character.isDigit(ch)) {
                    ++number;
                } else {
                    ++punctuation;
                }
            } else if (isChinese(ch)) {
                ++chinese;
            } else if (isPunctuation(ch)){
                ++punctuation;
            }

            if (!counter.containsKey(ch)) {
                counter.put(ch, 1);
            } else {
                Integer count = counter.get(ch);
                counter.put(ch, count+1);
            }
        }

        // 排序查找最大的三个
        ArrayList<CharacterCount> arr = new ArrayList<CharacterCount>();
        for (Character ch : counter.keySet()) {
            CharacterCount cc = new CharacterCount();
            cc.setCh(ch);
            cc.setCount(counter.get(ch));
            arr.add(cc);
        }
        Collections.sort(arr);
        CharacterCount[] top3;
        if (arr.size()>3) {
            top3 = arr.subList(0, 3).toArray(new CharacterCount[3]);
        } else {
            top3 = arr.toArray(new CharacterCount[arr.size()]);
        }

        // 返回结果
        CharacterCounterResult re = new CharacterCounterResult();
        re.setChinese(chinese);
        re.setEnglish(english);
        re.setNumber(number);
        re.setPunctuation(punctuation);
        re.setTop3(top3);
        return re;
    }

    private boolean isBasicLatin(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.BASIC_LATIN;
    }

    private boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_COMPATIBILITY
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B;
    }

    private boolean isPunctuation(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION;
    }
}

