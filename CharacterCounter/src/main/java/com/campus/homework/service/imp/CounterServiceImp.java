package com.campus.homework.service.imp;

import com.campus.homework.service.CounterService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by Administrator on 2017/7/2.
 */
@Service
public class CounterServiceImp implements CounterService{
    public static String letters="[a-zA-Z]";
    public static String numbers="[0-9]";
    public static String chineses="[\\u4e00-\\u9fa5]";
    public static String punctuations="[\\p{P}+~$`^=|<>～｀＄＾＋＝｜＜＞￥×]";
    private Map characterMap;

    public Map countCharacter(BufferedReader br){
        Map recordMap = new HashMap<Character,Integer>();
        Map retMap = new HashMap<String,Integer>();
        retMap.put("letters", 0);
        retMap.put("numbers", 0);
        retMap.put("chineses", 0);
        retMap.put("punctuations", 0);
        try {
            String line = null;
            while((line = br.readLine()) != null)
                stringCount(line, recordMap, retMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.characterMap=recordMap;
        return retMap;
    }

    public Map countCharacter(String str) {
        Map recordMap = new HashMap<Character,Integer>();
        Map retMap = new HashMap<String,Integer>();
        retMap.put("letters", 0);
        retMap.put("numbers", 0);
        retMap.put("chineses", 0);
        retMap.put("punctuations", 0);
        stringCount(str, recordMap, retMap);
        this.characterMap=recordMap;
        return retMap;
    }

    public void stringCount(String str, Map recordMap, Map retMap) {
        int numl=0,numN=0,numC=0,numP=0;
        for(int i = 0; i < str.length(); ++i){
            char c = str.charAt(i);
            String ch = String.valueOf(c);
            if(ch.matches(letters))
                ++numl;
            else if(ch.matches(numbers))
                ++numN;
            else if(ch.matches(chineses))
                ++numC;
            else if(ch.matches(punctuations))
                ++numP;
            else
                continue;
            if(recordMap.containsKey(c))
                recordMap.put(c, (Integer) recordMap.get(c) + 1);
            else
                recordMap.put(c, 1);
        }
        retMap.put("letters", (Integer) retMap.get("letters") + numl);
        retMap.put("numbers", (Integer) retMap.get("numbers") + numN);
        retMap.put("chineses", (Integer) retMap.get("chineses") + numC);
        retMap.put("punctuations", (Integer) retMap.get("punctuations") + numP);

    }

    public Map SortCharacter() {
        return sortMapByValue(characterMap);
    }

    public static Map sortMapByValue(Map map){
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator(){
            public int compare(Object o1, Object o2) {
                return -((Comparable) ((Map.Entry)o1).getValue())
                        .compareTo(((Map.Entry)o2).getValue());  //降序排列
            }
        });
        Map result = new LinkedHashMap();
        for (Object o : list) {
            result.put(((Map.Entry) o).getKey(),((Map.Entry) o).getValue());
        }
        return result;
    }

}
