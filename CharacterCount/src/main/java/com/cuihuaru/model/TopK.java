package com.cuihuaru.model;

/**
 * Created by Administrator on 2017/6/26.
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.PriorityQueue;

public class TopK {
  private Map<Character,Integer> map;
  private List<Map.Entry<Character, Integer>> result;
  public void setMap(Map<Character,Integer> m){this.map=m;}
  public List<Map.Entry<Character,Integer>> getList(){return result;}
 public void getTopk1(int k){

        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<Map.Entry<Character, Integer>>(map.entrySet().size(), new Comparator<Map.Entry<Character, Integer>>() {

            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                // TODO Auto-generated method stub
                return -(o1.getValue() - o2.getValue());
            }


        });
        pq.addAll(map.entrySet());
      result = new ArrayList<Map.Entry<Character, Integer>>(k);
        for (int i = 0; i < k; i++) {
            result.add(pq.poll());
        }
    }
}
