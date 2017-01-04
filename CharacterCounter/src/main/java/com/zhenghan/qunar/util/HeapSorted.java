package com.zhenghan.qunar.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.*;
import java.util.function.Consumer;

/**
 * Author: 郑含
 * Date: 2016/12/27
 * Time: 19:02
 */
public class HeapSorted {
    public static List<Map.Entry<String, Integer>> heapSort(List<Map.Entry<String, Integer>> list,
                                                      Comparator<Map.Entry<String, Integer>> comparator, int k) {
        List<Map.Entry<String, Integer>> strs = Lists.newArrayList();
        //建堆
        for(int i= (list.size()-2)/2;i>=0;i--){
            fixHeap(list,i,comparator);
        }
        //堆排序取topTen
        for(int index = 0;index<k&&!list.isEmpty();index++){
            strs.add(getMaxValue(list,comparator));
        }
        return strs;
    }

    private static Map.Entry<String,Integer> getMaxValue(List<Map.Entry<String, Integer>> list,
                                                  Comparator<Map.Entry<String, Integer>> comparator) {
        if(list.size()<=0){
            throw new ArrayIndexOutOfBoundsException();
        }
        Collections.swap(list,0,list.size()-1);
        Map.Entry<String, Integer> top = list.remove(list.size()-1);
        fixHeap(list,0,comparator);
        return top;
    }


    private static void fixHeap(List<Map.Entry<String, Integer>> list, int currIndex,Comparator<Map.Entry<String, Integer>> comparator) {
        int max = currIndex,right=currIndex*2+2,left=currIndex*2+1;
        if(list.size()-1<right){
            return ;
        }
        Map.Entry<String,Integer> currEntry = list.get(currIndex),leftEntry = list.get(left),rightEntry = list.get(right);
        if(comparator.compare(currEntry,leftEntry)<0){
            if(comparator.compare(leftEntry,rightEntry)<0){
                max = right;
            }else{
                max = left;
            }
        }else if(comparator.compare(currEntry,rightEntry)<0){
            if(comparator.compare(rightEntry,leftEntry)<0){
                max = left;
            }else{
                max = right;
            }
        }
        if(max != currIndex) {
            Map.Entry<String, Integer> curr = list.get(currIndex);
            list.set(currIndex, list.get(max));
            list.set(max, curr);
        }
        fixHeap(list,left,comparator);
        fixHeap(list,right,comparator);
    }

}
