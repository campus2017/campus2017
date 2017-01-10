package com.baobao.service;

import com.baobao.domain.Entry;

import java.util.*;

/**
 * Created by gzx on 17-1-2.
 */
public class CalculateString {
    private final static int SIZE1 = 4;
    private final static int SIZE2 = 3;
    // 对应页面中第一张统计表的选项名
    private final static String[] statistic_key = {"英文字母", "数字", "中文汉字", "中英文标点符号"};
    /*
        中文标点符号
     */
    private static boolean isChinesePunctuation(char c) {
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

    /*
        去除字母，数字，空白，且在128以内的可打印字符，则是英文标点符号
     */
    private static boolean isEnglishPunctuation(char c){
        if(c <= 31 || c >= 128 || Character.isLetter(c) || Character.isDigit(c) || Character.isSpaceChar(c)){
            return false;
        }
        return true;
    }

    /*
        中文汉字
     */
    private static boolean isChineseByScript(char c) {
        Character.UnicodeScript sc = Character.UnicodeScript.of(c);
        if (sc == Character.UnicodeScript.HAN) {
            return true;
        }
        return false;
    }

    /**
     * 统计5类字符出现的个数，可以把输出注释掉
     * @param text
     * @return
     */
    public static List<Entry> getStatistic(String text){
        List<Entry> list = new ArrayList<Entry>();
        for(int i = 0; i < SIZE1; i++){
            Entry entry = new Entry();
            entry.setStr(statistic_key[i]);
            list.add(entry);
        }
        for(int i = 0; i < text.length(); i++){
            char c = text.charAt(i);
            // 先判断中文，不然部分中文被判别为字母
            if(isChineseByScript(c)){
                System.out.println( " `" + c + "` 中文");
                list.get(2).increment();
            }
            else if(Character.isLetter(c)){
                System.out.println( " `" + c + "` 字母");
                list.get(0).increment();
            }
            else if(Character.isDigit(c)){
                System.out.println( " `" + c + "` 数字");
                list.get(1).increment();
            }
            else if(isChinesePunctuation(c) || isEnglishPunctuation(c)){
                System.out.println( " `" + c + "` 中英文标点符号");
                list.get(3).increment();
            }
            else{
                System.out.println( " `" + c + "` 其他");
            }
        }
        return list;
    }

    /**
     * 统计出现次数最高的3个字符
     * @param text
     * @return
     */
    public static List<Entry> getMostFrequency(String text){
        // 建立hash表
        HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();
        int cnt = 0;
        for(int i = 0; i < text.length(); i++){
            char c = text.charAt(i);
            if(hashMap.get(c) == null){
                cnt = 1;
            }
            else{
                cnt = hashMap.get(c) + 1;
            }
            hashMap.put(c, cnt);
        }
        // 建立大小为3的小根堆
        PriorityQueue<Entry> pq = new PriorityQueue<Entry>();
        for(Character c : hashMap.keySet()){
            Entry entry = new Entry();
            entry.setStr(c.toString());
            entry.setCnt(hashMap.get(c));
            pq.add(entry);
            if(pq.size() > SIZE2){
                pq.poll();
            }
        }
        // 从堆中获取最高频率的字符
        List<Entry> list = new ArrayList<Entry>();
        while(!pq.isEmpty()){
            list.add(pq.poll());
        }
        // 当对方没有输入任何内容，或者文件是空的时，补充到3个选项，这样js就不会出错
        while(list.size() < SIZE2){
            Entry entry = new Entry();
            entry.setStr("");
            entry.setCnt(0);
            list.add(entry);
        }
        return list;
    }

    /**
     * 总的入口，合并列表
     * @param text
     * @return
     */
    public static List<Entry> calculate(String text){
        List<Entry> list1 = getStatistic(text);
        List<Entry> list2 = getMostFrequency(text);
        processList(list2);
        list1.addAll(list2);
        return list1;
    }

    /*
        将空白键替换为文字
     */
    private static void processList(List<Entry> list){
        for(Entry entry : list){
            String str = entry.getStr();
            // 字符不够，用none替代，表示没有字符
            if(str.length() == 0){
                entry.setStr("none");
                continue;
            }
            char c = str.charAt(0);
            if(c == '\n'){
                entry.setStr("换行符");
            }
            else if(c == ' '){
                entry.setStr("空格");
            }
            else if(c == '\t'){
                entry.setStr("tab");
            }
        }
    }
    // 测试
    public static void main(String[] args){
        String text = "hello, 1341dasfajjj你玩把|||%!*&^^%%$8888&！````】【。。。";
        getStatistic(text);
    }
}
