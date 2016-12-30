package com.dw.characterCount.service;

import com.dw.characterCount.model.Result;
import com.dw.characterCount.util.CountString;
import com.dw.characterCount.util.Max3Char;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by DW on 2016/12/27.
 */
@Service
public class CountService {
    @Autowired
    private Result result;
    //统计输入文本
    public Result countText(String text){
        int[] result = CountString.count(text);
        this.result.setAll(result);
        //得到频率最多的三个元素及其次数
        Map<Character,Integer> max = Max3Char.getMax3Char(CountString.map);
        //将map中的元素按序赋给result对象
        getMostChar(max);
        return this.result;
    }

    //将频率最多的三个元素按序存放进result
    private void getMostChar(Map<Character,Integer> map){
        //若map无内容，则赋给result长度为0的list
        if(map==null||map.size()==0){
            result.setCh_list(new ArrayList<>());
            result.setVal_list(new ArrayList<>());
            return;
        }
        int size = map.size();
        //按序存放键值
        char[] ch = new char[size];
        //按序存放次数
        int[] val = new int[size];
        int i=0,j=0;
        while(map.size()>0){
            char min = Max3Char.getMinKey(map);
            ch[i++] = min;
            val[j++] = map.get(min);
            map.remove(min);
        }
        List<Character> ch_list = new ArrayList<>();
        List<Integer> val_list = new ArrayList<>();
        for(int m=ch.length-1;m>=0;m--){
            ch_list.add(ch[m]);
            val_list.add(val[m]);
        }
        result.setCh_list(ch_list);
        result.setVal_list(val_list);
    }
}
