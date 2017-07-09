package com.test.dev.service;

import net.sf.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by Vsur-Pc on 2017/7/4.
 */

@Service
@Transactional(readOnly = true)
public class CharacterCounterService {
    public JSONObject characterCounterOfInputStream(InputStream input){
        JSONObject json = new JSONObject();
        HashMap<String,Integer> map = new HashMap<String, Integer>();
        String E[] = new String[4];
        E[0] = "[a-zA-Z]";// 英文
        E[1] = "[0-9]";// 数字
        E[2] = "[\u4e00-\u9fa5]";// 中文
        E[3] = "\\pP|\\pS";
        int number[] = new int [4];
        BufferedReader br = new BufferedReader(new InputStreamReader(input));
        try {
            String line = null;
            while((line = br.readLine()) != null){
                System.out.println(line);
                for (int i = 0; i < line.length(); i++)
                {
                    String temp = String.valueOf(line.charAt(i));
                    for (int j=0;j<4;j++){
                        if(temp.matches(E[j])){
                            number[j]++;
                            if(map.containsKey(temp)) map.put(temp,map.get(temp)+1);
                            else map.put(temp,1);
                            break;
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        TreeSet<Map.Entry<String,Integer>> set = new TreeSet<Map.Entry<String,Integer>>
                ( new Comparator<Map.Entry<String,Integer>>() {
            public int compare(Map.Entry<String,Integer> o1, Map.Entry<String,Integer> o2) {
                if(o1.getValue() == o2.getValue() )
                    return o1.getKey().compareTo(o2.getKey());
                if(o1.getValue() < o2.getValue()) return 1;
                else return -1;
            }
        });
        for(Map.Entry<String,Integer> entry: map.entrySet()){
            if(set.size()<=3) set.add(entry);
            else{
                if(set.last().getValue()<entry.getValue()){
                    set.remove(set.last());
                    set.add(entry);
                }
            }
        }
        json.put("number",number);
        json.put("set",set);

        return json;
    }
}
