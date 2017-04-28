package com.qunar.campus2017;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Leviathan on 2017/4/27.
 */
public class WordCountService {

    private String text = null;

    private int mEnglish = 0;
    private int mNumeric = 0;
    private int mChinese = 0;
    private int mPunctuation = 0;

    private HashMultiset<Character> counter = HashMultiset.create();

    public WordCountService(String str) {
        this.text = str;
    }

    public HashMap<String, Integer> countChar() {
        char[] textCharArray = text.toCharArray();
        for (char c : textCharArray) {
            counter.add(c);
            if (isEnglish(c)) {
                mEnglish++;
            } else if (isNumeric(c)) {
                mNumeric++;
            } else if (isChinese(c)) {
                mChinese++;
            } else if (isChinesePunctuation(c) || isEnglishPunctuation(c)) {
                mPunctuation++;
            }
        }
        HashMap<String, Integer> map = new HashMap<>();
        map.put("english", mEnglish);
        map.put("numeric", mNumeric);
        map.put("chinese", mChinese);
        map.put("punctuation", mPunctuation);

        return map;
    }

    public ArrayList<CharCountEntry> getTopNChars(int topN) {
        FixedSizePriorityQueue<CharCountEntry> queue = new FixedSizePriorityQueue<>(topN);
        for (Multiset.Entry<Character> entry: counter.entrySet()) {
            CharCountEntry ccEntry = new CharCountEntry(entry.getElement(), entry.getCount());
            queue.add(ccEntry);
        }
        return queue.sortList();
    }


    private boolean isEnglish(char tmp) {
        return (tmp >= 'a' && tmp <= 'z') || (tmp >= 'A' && tmp <= 'Z');

    }

    private boolean isNumeric(char tmp) {
        return tmp >= '0' && tmp <= '9';
    }


    private boolean isChinese(char c) {
        Character.UnicodeScript sc = Character.UnicodeScript.of(c);
        if (sc == Character.UnicodeScript.HAN) {
            return true;
        }
        return false;
    }

    private boolean isChinesePunctuation(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS
                || ub == Character.UnicodeBlock.VERTICAL_FORMS) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isEnglishPunctuation(char ch) {
        return ((0x21 <= ch && ch <= 0x2F)
                || (0x3A <= ch && ch <= 0x40)
                || (0x5B <= ch && ch <= 0x60)
                || (0x7B <= ch && ch <= 0x7E));

    }

}
