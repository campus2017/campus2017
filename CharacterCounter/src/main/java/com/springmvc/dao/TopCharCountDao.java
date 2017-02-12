package com.springmvc.dao;

import com.springmvc.CountBean.CharCountBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gcy0904 on 2017/1/24.
 */
public class TopCharCountDao {
    HashMap<String, Integer> CharCountMap;    //记录Map
    String string = "";

    public TopCharCountDao() {
        CharCountMap = new HashMap<String, Integer>();
        this.getCharCountMap();
    }

    public TopCharCountDao(String str){
        this.string = str;
        CharCountMap = new HashMap<String, Integer>();
        this.getCharCountMap();
    }

    private int get(String CharName){
        Integer value = CharCountMap.get(CharName);
        return value == null? 0 : value;
    }
    private void getCharCountMap(){
        char[] chars = this.string.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            String CharName = String.valueOf(chars[i]);
            Integer value = CharCountMap.get(CharName);
            if(value==null){
                CharCountMap.put(CharName, 1);
            }else{
                CharCountMap.put(CharName, value + 1);
            }
        }
    }

    //获得次数最多的一个字符串
    public CharCountBean getMostCharCount(){
        int max = Integer.MIN_VALUE;
        String CharName = null;
        for(Map.Entry item: this.CharCountMap.entrySet()){
            String key = (String) item.getKey();
            int value = (Integer)item.getValue();
            if(value>max){
                max = value;
                CharName = key;
            }
        }
        return new CharCountBean(CharName,max);
    }

    //获得次数做多的10个类
    public List<CharCountBean> getTopCharCount(int num){
        List<CharCountBean> list = new ArrayList<CharCountBean>();
        for (int i = 0; i < num && i < this.CharCountMap.size(); i++) {
            CharCountBean mostCharCount = this.getMostCharCount();
            list.add(mostCharCount);
            this.CharCountMap.remove(mostCharCount.getCharName());
        }
        return list;
    }
}
