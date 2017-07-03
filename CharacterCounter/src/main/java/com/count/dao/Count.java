package com.count.dao;

import java.util.*;

/**
 * Created by chenli on 2017/6/12.
 */
public class Count {
    private String words;
    private HashMap<String, Integer> result = new HashMap<String, Integer>();
    private HashMap<Character, Integer> allWords = new HashMap<Character, Integer>();
    private List<Map.Entry<Character, Integer>> maxThirdWords = new ArrayList<Map.Entry<Character, Integer>>();

    public Count(String words){
        this.words = words;
        result.put("letter", 0);
        result.put("number", 0);
        result.put("chinese", 0);
        result.put("punctuation", 0);
    }
    //英文字母
    public boolean isLetter(char ch){
        if((ch >= 'a' && ch <= 'z')||((ch >= 'A' && ch <= 'Z'))){
            return true;
        }else{
            return false;
        }
    }
    //数字
    public boolean isNumber(char ch){
        if(ch >= '0' && ch <= '9'){
            return true;
        }else{
            return false;
        }
    }
    // 汉字
    public boolean isChinese(char ch){
        if(ch >=19968 && ch <= 171941){
            return true;
        }else{
            return false;
        }
    }
    // 根据UnicodeBlock方法判断中文标点符号
    public boolean isChinesePunctuation(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS) {
            return true;
        } else {
            return false;
        }
    }
    //英文标点
    public boolean isEnglishPunctuation(char ch){
        if(ch >= 33 && ch <= 126){
            return true;
        }else{
            return false;
        }
    }
    public HashMap<String, Integer> getCount(){
        int len = words.length();
        for(int i=0; i<len; i++){
            if(isLetter(words.charAt(i))){
                result.put("letter", result.get("letter") + 1);
                setWordMap(words.charAt(i));
            }else if(isNumber(words.charAt(i))){
                result.put("number", result.get("number") + 1);
                setWordMap(words.charAt(i));
            }else if(isChinese(words.charAt(i)) && !isChinesePunctuation(words.charAt(i))){
                result.put("chinese", result.get("chinese") + 1);
                setWordMap(words.charAt(i));
            }else if(isChinesePunctuation(words.charAt(i)) || isEnglishPunctuation(words.charAt(i))){
                result.put("punctuation", result.get("punctuation") + 1);
                setWordMap(words.charAt(i));
            }
        }
        return result;
    }
    public void setWordMap(char ch){
        Object obj = allWords.get(ch);
        if(obj == null){
            allWords.put(ch, 1);
        }else{
            allWords.put(ch, (Integer)obj + 1);
        }
    }

    //得到最大的3个字符
    public List<Map.Entry<Character, Integer>> getMaxThirdWords(){
        List<Map.Entry<Character, Integer>> tmpList = new ArrayList<Map.Entry<Character, Integer>>(allWords.entrySet());
        Collections.sort(tmpList, new Comparator<Map.Entry<Character, Integer>>() {
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                if((o2.getValue().toString().compareTo(o1.getValue().toString())) == 0){//字典序
                    return (o1.getKey().toString().compareTo(o2.getKey().toString()));
                }
                return (o2.getValue().toString().compareTo(o1.getValue().toString()));
            }
        });
        for(int i = 0; i < 3 && i < tmpList.size(); i++){
            Map.Entry<Character, Integer> tmp= tmpList.get(i);
            maxThirdWords.add(tmp);
        }
        return maxThirdWords;
    }

    //http://www.oschina.net/code/snippet_88638_5091
    //http://www.cnblogs.com/zztt/p/3427452.html
}
