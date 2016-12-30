package com.dw.characterCount.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DW on 2016/12/28.
 */
//统计字符串中英文、中文、数字、标点符号的数量
public class CountString {
    //存放各个字符出现次数
    public static Map<Character,Integer> map;
    public static int[] count(String text){
        map = new HashMap<>();
        if(text==null) return null;
        text = text.trim();
        //数组分别存放英文、中文、数字、标点符号的数量
        int[] result = new int[4];
        //遍历文本，统计字符
        for(int i=0;i<text.length();i++){
            String a = text.charAt(i)+"";
            if(a.matches("[a-zA-Z]")){
                ++result[0];
                putMap(text.charAt(i));
            }
            else if(a.matches("[\\u4e00-\\u9fa5]")){
                ++result[1];
                putMap(text.charAt(i));
            }
            else if(a.matches("[0-9]")){
                ++result[2];
                putMap(text.charAt(i));
            }
            else if(a.matches("[\\pP]")){
                ++result[3];
                putMap(text.charAt(i));
            }
        }
        return result;
    }
    //将符合条件的字符放进map
    private static void putMap(char c){
        if(map.get(c)==null){
            map.put(c,1);
        }
        else{
            int n = map.get(c);
            map.put(c,++n);
        }
    }
}
