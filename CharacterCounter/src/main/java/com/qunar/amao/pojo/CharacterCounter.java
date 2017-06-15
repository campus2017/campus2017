package com.qunar.amao.pojo;

import java.util.*;

/**
 * Created by FGT on 2017/5/31.
 */
public class CharacterCounter {
    private int sumEnglish=0;//统计英文
    private int sumNumber=0;//统计数字
    private int sumChinese=0;//统计中文
    private int sumSymbol=0;//统计中英文标点符号

    private HashMap<Character,Integer> map = new HashMap<Character, Integer>();//统计字符的数量

    private LinkedHashMap<Character,Integer> linkedHashMap = new LinkedHashMap<Character, Integer>();//排序后的键值对

    public int getSumEnglish() {
        return sumEnglish;
    }

    public void setSumEnglish(int sumEnglish) {
        this.sumEnglish = sumEnglish;
    }

    public int getSumNumber() {
        return sumNumber;
    }

    public void setSumNumber(int sumNumber) {
        this.sumNumber = sumNumber;
    }

    public int getSumChinese() {
        return sumChinese;
    }

    public void setSumChinese(int sumChinese) {
        this.sumChinese = sumChinese;
    }

    public int getSumSymbol() {
        return sumSymbol;
    }

    public void setSumSymbol(int sumSymbol) {
        this.sumSymbol = sumSymbol;
    }

    public HashMap<Character, Integer> getMap() {
        return map;
    }

    public void setMap(HashMap<Character, Integer> map) {
        this.map = map;
    }

    public LinkedHashMap<Character, Integer> getLinkedHashMap() {
        return linkedHashMap;
    }

    public void setLinkedHashMap(LinkedHashMap<Character, Integer> linkedHashMap) {
        this.linkedHashMap = linkedHashMap;
    }

    public void addEnglish(){
        sumEnglish++;
    }

    public void addNumber(){
        sumNumber++;
    }

    public void addChinese(){
        sumChinese++;
    }

    public void addSymbol(){
        sumSymbol++;
    }

    /**
     * 统计map
     * @param ch 字符变量
     */
    public void CountCharacterMap(char ch) {
        if (map.size() == 0) {//如果不存在数据，则放入第一条数据
            map.put(ch, 1);
        } else {
            if (map.get(ch) == null) {
                map.put(ch, 1);
            } else {
                map.put(ch, map.get(ch) + 1);
            }
        }
    }

    /**
     * 按value排序
     * @return
     */
    public void SortMap(){
        List<Map.Entry<Character, Integer>> entries = new ArrayList<Map.Entry<Character, Integer>>(map.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<Character, Integer>>() {
            public int compare(Map.Entry<Character, Integer> obj1 , Map.Entry<Character, Integer> obj2) {
                return obj2.getValue().compareTo(obj1.getValue());
            }
        });

        for (Map.Entry<Character, Integer> mapping : entries) {
           linkedHashMap.put(mapping.getKey(),mapping.getValue());
        }
    }

}
