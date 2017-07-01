package com.CharacterCounter.service;

import java.util.*;
import  com.CharacterCounter.data.*;
/**
 * Created by apple on 17/6/19.
 */

public class wordCount {
    public   String fileContent="";
    public  resultData data;
    public  wordCount(String content)
    {
        fileContent=content;

        data=count();
        data.top_tree=selectTopThree();
    }
    public List<count> selectTopThree()
    {
        Map<String,Integer> keyValue=new HashMap<>();
        for(int i=0;i<fileContent.length();i++)
        {
            String key = String.valueOf((fileContent.charAt(i)));
            if(!keyValue.containsKey(key))
                keyValue.put(key, 1);
            else{
                int val =keyValue.get(key);
                keyValue.put(key, val+1);
            }
        }
        List<Map.Entry<String,Integer>> list =
                new ArrayList<Map.Entry<String,Integer>>(keyValue.entrySet());


        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o2.getValue() - o1.getValue());
            }
        });

        List<count> res=new ArrayList<>();
        int i=3;

        int j=0;
        while(i>0)
        {
            //String temp=(String) iter.next();
            Map.Entry<String, Integer>  temp=list.get(j);
            res.add(new count(temp.getKey(),temp.getValue()));
            i--;
            j++;
        }
        return res;
    }
    public  resultData count()
    {
        resultData data=new resultData();
        byte[] bytStr = fileContent.getBytes();
        for(int i=0;i<fileContent.length();i++)
        {
            char c = fileContent.charAt(i);
            if (c >= '0' && c <= '9') {
                data.number_count++;
            }else if((c >= 'a' && c<='z') || (c >= 'A' && c<='Z')){
                data.en_ch_count++;
            }
            else if(isChinese(c))
            {
                data.chinese_ch_count++;
            }
            else
            {
                data.punctuation_count++;
            }
        }
//        int all_chinese_num=0;
//        for(int i = 0; i < bytStr.length; i ++)
//        {
//            if(bytStr[i] < 0 )//java中文字符是负的BYTE值
//            {
//                all_chinese_num ++;
//                i++;//中文字符是双字节
//            }
//        }
       // data.punctuation_count+=(all_chinese_num-data.chinese_ch_count);
        return data;
    }
    private boolean isChinese(char ch) {
        //获取此字符的UniCodeBlock
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(ch);
        //  GENERAL_PUNCTUATION 判断中文的“号
        //  CJK_SYMBOLS_AND_PUNCTUATION 判断中文的。号
        //  HALFWIDTH_AND_FULLWIDTH_FORMS 判断中文的，号
//        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
//                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
//                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
//                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION||ub==Chara)
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_C
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_D
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT)
        {
            System.out.println(ch + " 是中文");
            return true;
        }
        return false;
    }
}
