package cn.xuchunh.charcounter.util;

/**
 * Created on 2017/6/12.
 *
 * @author XCH
 */
public class StringUtil {

    // Character.isLetter不仅识别字母还识别包括常用汉字在内的字符，这里只识别英文字母
    public static boolean isLetter(char ch) {
        return Character.isLowerCase(ch) ||
                Character.isUpperCase(ch);
    }

    // http://www.cnblogs.com/zztt/p/3427452.html
    @SuppressWarnings("Since15")
    public static boolean isChinese(char c) {
//        return c >= 0x4E00 && c <= 0x9FA5;
        return Character.UnicodeScript.of(c) == Character.UnicodeScript.HAN;
    }

//    @SuppressWarnings("Since15")
//    public static boolean isChinesePunctuation(char c){
//        Character.UnicodeBlock block = Character.UnicodeBlock.of(c);
//        return block == Character.UnicodeBlock.GENERAL_PUNCTUATION
//                || block == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
//                || block == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
//                || block == Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS
//                || block == Character.UnicodeBlock.VERTICAL_FORMS;
//    }

    // http://blog.csdn.net/ztf312/article/details/54310542
    public static boolean isPunctuation(char ch) {
        if (isCjkPunc(ch)) return true;
        if (isEnPunc(ch)) return true;

        if (0x2018 <= ch && ch <= 0x201F) return true;
        if (ch == 0xFF01 || ch == 0xFF02) return true;
        if (ch == 0xFF07 || ch == 0xFF0C) return true;
        if (ch == 0xFF1A || ch == 0xFF1B) return true;
        if (ch == 0xFF1F || ch == 0xFF61) return true;
        if (ch == 0xFF0E) return true;
        if (ch == 0xFF65) return true;

        return false;
    }

    private static boolean isEnPunc(char ch) {
        return (0x21 <= ch && ch <= 0x22) ||
                (ch == 0x27 || ch == 0x2C) ||
                (ch == 0x2E || ch == 0x3A) ||
                (ch == 0x3B || ch == 0x3F);
    }

    private static boolean isCjkPunc(char ch) {
        return (0x3001 <= ch && ch <= 0x3003) ||
                (0x301D <= ch && ch <= 0x301F);

    }

}
