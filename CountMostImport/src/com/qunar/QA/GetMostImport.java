package com.qunar.QA;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;
;import java.util.Comparator;
import java.util.ArrayList;

/**
 * @Author Nicole
 * @Time 2017/7/2
 * @Description 获取引用前十的类。不足十就全部列出。
 */

public class GetMostImport {

    public void getMostImport(HashMap<String,Integer> importClassRecords) {

        Iterator iter = importClassRecords.entrySet().iterator();
        System.out.println("全部引用类：");
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String)entry.getKey();
            Integer val = (Integer)entry.getValue();
            System.out.println(key+" = "+val);
        }

        List<Map.Entry<String,Integer>> infoIds= new ArrayList<Map.Entry<String,Integer>>(importClassRecords.entrySet());
        Collections.sort(infoIds,new Comparator<Map.Entry<String,Integer>>(){
           public int compare(Map.Entry<String,Integer> o1, Map.Entry<String,Integer> o2){
               return (o2.getValue()-o1.getValue());
            }
        });
        System.out.println();
        System.out.println("----------------------------------------");
        System.out.println();
        System.out.println("引用最多的前十个类（不足十就全部列出）:");
        for(int i=0,num=10;i<infoIds.size()&&num>0;i++,num--){
                String id=infoIds.get(i).toString();
                System.out.println(id);
        }

    }
}

