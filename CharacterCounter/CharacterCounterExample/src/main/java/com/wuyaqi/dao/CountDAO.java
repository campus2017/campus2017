package com.wuyaqi.dao;

import com.google.common.collect.TreeMultiset;

import java.util.*;

/**
 * Created by wuyaqi on 17-4-29.
 * 负责业务逻辑，给service提供接口
 */
public class CountDAO {
    private TreeMultiset<Character> characterCountSet;
    //对str进行扫描，得到各个种类字符的个数。
    public Map<String,Integer> getCounter(String str)//这个函数，应该可以得到各种字符的个数，将这四个值设为一个map。
    {
        characterCountSet= TreeMultiset.create();
        int engCount=0;
        int numCount=0;
        int chnCount=0;
        int punCount=0;
        Map<String ,Integer> charnumMap = new HashMap<String, Integer>();

        String english_r = "[a-zA-Z]";//英文字符
        String digital_r = "[0-9]";//数字
        String chinese_r = "[\u4e00-\u9fa5]";//中文
        String space_r = " ";

        for(int i = 0;i<str.length();i++)
        {
            String word = String.valueOf(str.charAt(i));
            characterCountSet.add(str.charAt(i));
            if(word.matches(english_r))
            {
                engCount++;
            }
            else if(word.matches(digital_r))
            {
                numCount++;
            }
            else if(word.matches(chinese_r))
            {
                chnCount++;
            }
            else if(word.matches(space_r))
            {

            }
            else
            {
                punCount++;
            }
        }
        charnumMap.put("engCount",engCount);
        charnumMap.put("numCount",numCount);
        charnumMap.put("chnCount",chnCount);
        charnumMap.put("punCount",punCount);
        return charnumMap;

    }
    //得到前三个比较大的字符。应该返回一个Map，存的是前三个最大的字符的个数。
    public List<Map.Entry<Character,Integer>> getTop3Charater()
    {
        List<Map.Entry<Character,Integer>> top3ListMap = new ArrayList<Map.Entry<Character, Integer>>();
        HashMap<Character,Integer> charMap = new HashMap<Character, Integer>();
        for(char ch : characterCountSet.elementSet())
        {
            int temp = characterCountSet.count(ch);
            charMap.put(ch,temp);
        }
        //以下代码为了使map按照Integer的value值进行排序。
        List<Map.Entry<Character,Integer>> charList = new ArrayList<Map.Entry<Character, Integer>>(charMap.entrySet());
        Collections.sort(charList, new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                int temp_comp = 0;
                if (o2.getValue() > o1.getValue())
                    temp_comp = 1;
                else if (o2.getValue() < o1.getValue())
                    temp_comp = -1;
                else if (o2.getValue() == o1.getValue())//如果次数相同，按照字典序从小到大排序
                {
                    temp_comp = o1.getKey() - o2.getKey();
                }
                return temp_comp;
            }
            //逆序（从大到小）排列，正序为“return o1.getValue()-o2.getValue”
        });

        //对charList进行排序后，就可以直接从前面去前三个。
        top3ListMap.add(charList.get(0));
        top3ListMap.add(charList.get(1));
        top3ListMap.add(charList.get(2));

        return top3ListMap;
    }

}
