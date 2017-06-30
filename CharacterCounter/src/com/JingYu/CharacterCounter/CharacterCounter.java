package com.JingYu.CharacterCounter;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by steamedfish on 17-6-29.
 */
public class CharacterCounter {
    public static HashMap map=new HashMap<String,Integer>();
    public static HashMap map1=new HashMap<Character,Integer>();
    public static void AnalyzeString(String text){
        text=" "+text+" ";
        //计算字符的数量
        String regex="[a-zA-Z]";
        int cnt=text.split(regex).length-1;
        map.put("letter",cnt);
        //计算汉字数量
        regex="[\u4e00-\u9fa5]";
        cnt=text.split(regex).length-1;
        map.put("Chinese",cnt);
        //计算数字
        regex="[\\d]";
        cnt=text.split(regex).length-1;
        map.put("number",cnt);
        //计算中英文标点符号
        regex="[,<.>/?;:'\\\"`~，《。》【】？/、——；：’“·]";
        cnt=text.split(regex).length-1;
        map.put("symbol",cnt);
    }
    public static void CountCharacter(String text){
        HashMap map2=new HashMap<Character,Integer>();
        text=text.replace(" ","");
        for(int i = 0;i < text.length();i++){
            Character c=text.charAt(i);
            if(map2.get(c)==null){
                map2.put(c,1);
            }else{
                map2.put(c,(Integer)map2.get(c)+1);
            }
        }
        for(int i=0;i<3;i++){
            int max=0;
            Character c=null;
            for(Object o:map2.keySet()){
                Integer temp=(Integer)map2.get(o);
                if(temp>max){
                    max=temp;
                    c=(Character)o;
                }
                if(temp==max){
                    if((Character)o<c)
                        c=(Character)o;
                }
            }
            map1.put("Seq"+i,c+" "+max);
            //map1.put(c,max);
            map2.remove(c);
        }
    }
}
