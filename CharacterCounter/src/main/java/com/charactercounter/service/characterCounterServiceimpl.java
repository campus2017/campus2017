package com.charactercounter.service;

import com.util.CharacterUtil;

import java.util.*;

/**
 * 功能：统计输入文本中英文字母 ，数字，汉字，中英文标点符号的个数；
 *       统计出现出现频率最高的三个字符
 * 作者：Yung
 * 时间：2017/2/19.
 */
public class characterCounterServiceimpl implements  characterCounterService{
   /*功能：统计各个字符个数*/
    public HashMap<String,Integer> countCharacter(String character) {
         int englishCount=0;//英文字母个数
         int digitCount = 0;//数字个数
         int chineseCount=0;// 中文个数
         int punctuationCount=0;//中英文标点符号个数
         String temp=null;
        for (int i = 0; i < character.length(); i++) {
            char c = character.charAt(i);
            temp=String.valueOf(c);
            if (temp.matches("[0-9]")) {
                digitCount++;
            }else if(temp.matches("[a-zA-Z]")){
                englishCount++;
            }else if(temp.matches("[\u4e00-\u9fa5]")){
              chineseCount++;
            }else if(CharacterUtil.isPunctuation(c) || CharacterUtil.isSymbol(c)){
                punctuationCount++;
            }
        }
        HashMap<String,Integer> hashMap=new HashMap<String, Integer>();
        hashMap.put("englishCount",englishCount);
        hashMap.put("digitCount",digitCount);
        hashMap.put("chineseCount",chineseCount);
        hashMap.put("punctuationCount",punctuationCount);
        return hashMap;
    }

    /*功能：统计出现频率最高的三个字符*/
    public HashMap<String,Integer> countMostCharacter(String character){
        HashMap<Character,Integer> hashMap=new HashMap<Character, Integer>();
        for (int i = 0; i < character.length(); i++) {
            char c = character.charAt(i);
            if(!CharacterUtil.isPunctuation(c) && !CharacterUtil.isSymbol(c)){ //标点符号除外
                if(hashMap.containsKey(c))  //统计所有文字的个数
                    hashMap.put(c,hashMap.get(c)+1);
                else hashMap.put(c,1);
            }
        }
        HashMap<Character,Integer> hashMap1=(HashMap) descHashMapByComparator(hashMap);
        int i=0;
        HashMap<String,Integer> countMostThreeCharacter=new HashMap<String, Integer>();
        for(Map.Entry item: hashMap1.entrySet()){
            String key = String.valueOf(item.getKey()) ;
            int value = (Integer)item.getValue();
            countMostThreeCharacter.put(key,value);
            i++;
            if(i==3) break;
        }
        return countMostThreeCharacter;
    }

    /*功能：对map按照value值进行降序处理*/
    private static Map descHashMapByComparator(Map map) {
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator()
        {
            public int compare(Object o1, Object o2)
            {
                return ((Comparable) ((Map.Entry) (o2)).getValue())
                        .compareTo(((Map.Entry) (o1)).getValue());
            }
        });
        Map sortedMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry)it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
}
