package com.qunar.flight.util;

import java.util.regex.Pattern;

/**
 * Created by nostalie.zhang on 2017/2/3.
 */
public class CharUtil {

    /*public static void main(String[] args) {
        char c = '.';
        System.out.println("中文标点："+isChinesePunctuation(c));
        System.out.println("中文汉字："+isChineseCharacter(c));
        System.out.println("英文字母："+isEnglishCharacter(c));
        System.out.println("英文标点："+isEnglishPunctuation(c));
        System.out.println("数字："+isNumber(c));
    }*/

    //根据Unicode编码使用UnicodeBlock判断是否是中文汉字
    public static boolean isChineseCharacter(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_C
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_D
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT) {
            return true;
        }
        return false;
    }
    //使用UnicodeScript判断是否是中文汉字
    public static boolean isChineseCharacterByUnicodeScript(char c){
        Character.UnicodeScript sc = Character.UnicodeScript.of(c);
        if (sc == Character.UnicodeScript.HAN) {
            return true;
        }
        return false;
    }
    //根据Unicode编码使用UnicodeBlock判断是否为中文标点符号
    public static boolean isChinesePunctuation(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS
                || ub == Character.UnicodeBlock.VERTICAL_FORMS) {
            return true;
        }
        return false;
    }

    //判断字符是否为数字
    public static boolean isNumber(char c){
        return Character.isDigit(c);
    }

    //判断是否为英文字母
    public static boolean isEnglishCharacter(char c){
        String str = String.valueOf(c);
        if(str.isEmpty() || str==null){
            return false;
        }
        Pattern pattern = Pattern.compile("[a-zA-Z]");
        return pattern.matcher(str).matches();
    }

    //判断是否为英文标点
    public static boolean isEnglishPunctuation(char c){
        String str = String.valueOf(c);
        str = str.replaceAll("[\\pP‘’“”]", "");
        return str.isEmpty() ? true : false;
    }

    //忽略字符
    public static boolean ignore(char c){
        if(c == ' ' || c == '\n' || c == '\t'){
            return true;
        }
        return false;
    }

    // 只能判断部分CJK字符（CJK统一汉字）
    public static boolean isChineseByREG(String str) {
        if (str == null) {
            return false;
        }
        Pattern pattern = Pattern.compile("[\\u4E00-\\u9FBF]+");
        return pattern.matcher(str.trim()).find();
    }

    // 只能判断部分CJK字符（CJK统一汉字）
    public static boolean isChineseByName(String str) {
        if (str == null) {
            return false;
        }
        // 大小写不同：\\p 表示包含，\\P 表示不包含
        // \\p{Cn} 的意思为 Unicode 中未被定义字符的编码，\\P{Cn} 就表示 Unicode中已经被定义字符的编码
        String reg = "\\p{InCJK Unified Ideographs}&&\\P{Cn}";
        Pattern pattern = Pattern.compile(reg);
        return pattern.matcher(str.trim()).find();
    }
}