package com.youthlin.qunar.fresh.charactercount.util;

/**
 * Created by youthlin.chen on 2016-12-9 009.
 * 字符工具
 */
public class CharacterUtil {
    /**
     * 根据UnicodeBlock方法判断中文标点符号
     *
     * @param c 要判断的字符
     * @return 是中文的标点符号则返回true
     */
    public static boolean isChinesePunctuation(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS
                || ub == Character.UnicodeBlock.VERTICAL_FORMS;
    }

    /**
     * 判断中文字符
     *
     * @param c 要判断的字符
     * @return 是中文汉字或中文符号则返回true
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION;

    }
}
