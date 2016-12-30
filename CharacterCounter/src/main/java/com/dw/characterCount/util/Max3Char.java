package com.dw.characterCount.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by DW on 2016/12/28.
 */
//计算出现次数最多的3个元素
public class Max3Char {
    public static Map<Character,Integer> getMax3Char(Map<Character,Integer> map){
        if(map==null||map.size()==0) return map;
        //存放最大的3个元素
        Map<Character,Integer> max = new HashMap<>();
        Iterator<Character> iterator = map.keySet().iterator();
        //max里的最小值的键值
        char min = 0;
        while(iterator.hasNext()){
            char c = iterator.next();
            int v = map.get(c);
            //如果max的元素小于3个，则往里面存放,否则和max里最小的元素进行比较替换
            if(max.size()<3){
                max.put(c,v);
                min = getMinKey(max);
            }
            else if(v>max.get(min)||(v==max.get(min)&&c>min)){
                max.remove(min);
                max.put(c,v);
                min = getMinKey(max);
            }
        }
        return max;
    }
    //在map中得到value最小值的key
    public static char getMinKey(Map<Character,Integer> map){
        Iterator<Character> iterator = map.keySet().iterator();
        //将第一个值设为最小值
        char min = iterator.next();
        //循环与最小值比较
        while(iterator.hasNext()){
            int val = map.get(min);
            char c = iterator.next();
            int v = map.get(c);
            //如果当前值比最小值小，则当前值为最小值；如果一样大，则按字典顺序比较
            if(v<val||(v==val&&c>min)){
                min = c;
            }
        }
        return min;
    }
}
