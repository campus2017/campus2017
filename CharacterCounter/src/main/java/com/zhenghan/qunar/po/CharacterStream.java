package com.zhenghan.qunar.po;

import com.google.common.base.CharMatcher;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.SortedMapDifference;

import java.io.IOException;
import java.io.InputStream;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

/**
 * Author: 郑含
 * Date: 2016/12/13
 * Time: 10:55
 * 英文字母 , 数字,中文字母,中英文标点符号
 */
public abstract class CharacterStream {
    public static enum CharacterClassify{
        ENGLISH_WORD,NUMBER,CHINESE_WORD,PUNCTUTION,NONE;
    }
    private String datas;
    private InputStream stream;
    private Map<String,Integer> caches;
    public CharacterStream(InputStream stream){
        this.stream = stream;
    }
    public CharacterStream(String datas){ this.datas = datas; }
    public String getDatas(){
        return datas;
    }
    public void close() throws IOException {
        if(stream != null){
            stream.close();
        }
    }
    public abstract String doStreamsToStrs(InputStream stream) throws IOException;
    public Map<String,Integer> countEveryWords(){
        if(datas == null) {
            try {
                datas = doStreamsToStrs(stream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Map<String,Integer> temp = Maps.newHashMap();
        char[] charBuffer = datas.toCharArray();
        for(char ch:charBuffer){
            String key = String.valueOf(ch);
            if(!key.trim().equals("")) {
                temp.put(key, Optional.fromNullable(temp.get(key)).or(0) + 1);
            }
        }
        return temp;
    }
    public Map<CharacterClassify,Integer> countTypesWords(){
        if(datas == null) {
            try {
                datas = doStreamsToStrs(stream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Map<CharacterClassify,Integer> reduceCounter = Maps.newHashMap();
        Map<String,Integer> charInteger = countEveryWords();
        for(Map.Entry<String,Integer> entry:charInteger.entrySet()){
            String charStr = entry.getKey();
            CharacterClassify temp = null;
            if(charStr.matches("[a-zA-Z]$")){
                temp = CharacterClassify.ENGLISH_WORD;
            }else if(charStr.matches("^\\d$")){
                temp = CharacterClassify.NUMBER;
            }else if(charStr.matches("^[\\u4e00-\\u9fa5]$")){
                temp = CharacterClassify.CHINESE_WORD;
            }else if(charStr.matches("\\pP")){
                temp = CharacterClassify.PUNCTUTION;
            }else{
                temp = CharacterClassify.NONE;
            }
            if(!temp.equals(CharacterClassify.NONE)) {
                reduceCounter.put(temp,
                        Optional.fromNullable(reduceCounter.get(temp)).or(0) + entry.getValue());
            }
        }
        return reduceCounter;
    }
}
