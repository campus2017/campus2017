package com.zhenghan.qunar;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.*;

/**
 * Author: 郑含
 * Date: 2016/12/7
 * Time: 18:31
 *
 */
public class Trie {
    private Map<String,Trie> childs = Maps.newHashMap();
    private int count = 0;
    public Trie(){
    }

    /**
     * @param mPackage
     *
     */
    public void putPackage(String mPackage){
        List<String> lists =Splitter.on(".").trimResults().omitEmptyStrings().splitToList(Preconditions.checkNotNull(mPackage));
        Queue<String> packageQueue = Lists.newLinkedList(lists);
        Map<String,Trie> pchilds = this.childs;
        String childKey = null;
        Trie childValue = null;
        while(!packageQueue.isEmpty()){
            childKey = packageQueue.poll();
            if(!pchilds.containsKey(childKey)){
                pchilds.put(childKey,new Trie());
            }
            childValue = pchilds.get(childKey);
            pchilds = childValue.childs;
        }
        if(childValue!=null) {
            childValue.count += 1;
        }
    }
    public void putPackages(List<String> mPackages){
        for(String mPackage:mPackages){
            putPackage(mPackage);
        }
    }
    /**
     * *的会在String中存储*所以当前结点,也就是所有的包计算和统计完毕后
     * 当前结点的所有子结点都会+n
     */
    public int counts(String packageStr){
        List<String> lists =Splitter.on(".").trimResults().omitEmptyStrings().splitToList(Preconditions.checkNotNull(packageStr));
        Queue<String> packageQueue = Lists.newLinkedList(lists);
        Map<String,Trie> pchilds = childs;
        Trie chTrie = null;
        int tempCount = 0;
        while(!packageQueue.isEmpty()) {
            String key = packageQueue.poll();
            chTrie = null;
            for (Map.Entry<String, Trie> entry : pchilds.entrySet()) {
                if(entry.getKey().equals(key)){
                    chTrie = entry.getValue();
                }else if(entry.getKey().equals("*")){
                    tempCount ++;
                }
            }
            if(chTrie == null) {
                break;
            }
            pchilds = chTrie.childs;
        }
        if(chTrie != null){
            return chTrie.count + tempCount;
        }
        return tempCount;
    }

    /**
     *
     * @param prePackage
     * @param map
     * @return
     * 该方法计算前将合并所有的*类
     */
    private Map<String,Integer> doCountsAll(String prePackage,Map<String,Integer> map,int count){
        int curCount = 0;
        for (Map.Entry<String, Trie> trieEntry : childs.entrySet()){
            if(trieEntry.getKey().equals("*")) {
                curCount = 1;
            }
        }
        for (Map.Entry<String, Trie> entry : childs.entrySet()) {
            if(entry.getKey().equals("*")) {
                continue;
            }
            Trie trie = entry.getValue();
            String currentPackage = prePackage + entry.getKey();
            if (trie.count > 0) {
                map.put(currentPackage, trie.count+curCount);
            } else {
                trie.doCountsAll(currentPackage + ".", map,count+curCount);
            }
        }
        return map;
    }
    public Map<String,Integer> caculateCounts(){
        Map<String,Integer> map = Maps.newHashMap();
        return doCountsAll("",map,0);
    }

    /**
     * 取出前10个堆排序
     */
    public List<Map.Entry<String,Integer>> topTen(){
        Map<String,Integer> map = caculateCounts();
        ArrayList<Map.Entry<String,Integer>> list = Lists.newArrayList();
        list.addAll(map.entrySet());
        return heapSort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue()-o2.getValue();
            }
        },10);
    }

    private List<Map.Entry<String, Integer>> heapSort(ArrayList<Map.Entry<String, Integer>> list,
                          Comparator<Map.Entry<String, Integer>> comparator,int k) {
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

    private Map.Entry<String,Integer> getMaxValue(ArrayList<Map.Entry<String, Integer>> list,
                                                  Comparator<Map.Entry<String, Integer>> comparator) {
        if(list.size()<=0){
            throw new ArrayIndexOutOfBoundsException();
        }
        Collections.swap(list,0,list.size()-1);
        Map.Entry<String, Integer> top = list.remove(list.size()-1);
        fixHeap(list,0,comparator);
        return top;
    }


    private void fixHeap(ArrayList<Map.Entry<String, Integer>> list, int currIndex,Comparator<Map.Entry<String, Integer>> comparator) {
        int max = currIndex,right=currIndex*2+2,left=currIndex*2+1;
        if(list.size()-1<right){
            return ;
        }
        if(comparator.compare(list.get(currIndex),list.get(right))<0){
            if(comparator.compare(list.get(right),list.get(left))<0){
                max = left;
            }else{
                max = right;
            }
        }else if(comparator.compare(list.get(currIndex),list.get(left))<0){
            if(comparator.compare(list.get(left),list.get(right))<0){
                max = right;
            }else{
                max = left;
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

    public static void main(String[] args) {
        Trie root = new Trie();
        root.putPackage("com.hbut.countMostImport");
        root.putPackage("com.google.guava");
        root.putPackage("com.google.guava");
        root.putPackage("org.apache.commonlogging");
        System.out.println(root.topTen());
    }
}
