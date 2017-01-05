package com.liu.CountMostImport;

import java.util.Comparator;
import java.util.Map;

/**
 * Created by liudan on 2017/1/5.
 */
public class NewComparator implements Comparator<Map.Entry<String,Integer>> {

    //根据 key 得到相应的 value，比较大小，降序排列
    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {

        return o1.getValue() > o2.getValue() ? -1 : o1.getValue() <  o2.getValue() ? 1 : 0;
    }
}
