package com.qunar.service;

import com.google.gson.Gson;
import com.qunar.bean.ResultJson;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhuyin on 17-1-20.
 */

@Service
public class FileUploadService {

    private static HashMap top3;
    ResultJson resultJson;
    public String parse(InputStream stream) {
        byte[] buffer = new byte[1024];
        int len = -1;

        String result = null;
        String tmp = "";
        
        int letterCount = 0;
        int chineseCount = 0;
        int numCount = 0;
        int punctuationCount = 0;
        int top3Chinese = 0;
        resultJson = new ResultJson();
        Gson gson = new Gson();


        try {
            while ((len = stream.read(buffer)) != -1) {
                tmp += new String(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        numCount = countNumber(tmp);
        chineseCount = countChinese(tmp);
        letterCount = countLetter(tmp);
        punctuationCount = countPunctuation(tmp);
        top3Chinese();

        resultJson.setChineseCount(chineseCount);
        resultJson.setEnglishCount(letterCount);
        resultJson.setNumCount(numCount);
        resultJson.setPunctuationCount(punctuationCount);
        System.out.println("englishCount: "+letterCount+" chinese: "+chineseCount+" num: "+numCount+" p: "+punctuationCount);
        result = gson.toJson(resultJson);

        return result;
    }

    public static int countNumber(String str) {
        int count = 0;
        Pattern p = Pattern.compile("\\d");
        Matcher m = p.matcher(str);
        while(m.find()){
            count++;
        }
        return count;
    }

    public static int countLetter(String str) {
        int count = 0;
        Pattern p = Pattern.compile("[a-zA-Z]");
        Matcher m = p.matcher(str);
        while(m.find()){
            count++;
        }
        return count;
    }

    public static int countChinese(String str) {
        int count = 0;
        Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]");
        Matcher m = p.matcher(str);
        top3 = new HashMap();
        while(m.find()){
            count++;
            top3.put(m.group(),((int)top3.getOrDefault(m.group(),0))+1);
        }

        return count;
    }

    public void top3Chinese(){
        List<Map.Entry<String, Integer>> infoIds =
                new ArrayList<Map.Entry<String, Integer>>(top3.entrySet());

        Collections.sort(infoIds, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o2.getValue() - o1.getValue());
            }
        });

        if(infoIds.size()>=3) {
            resultJson.setTop1(infoIds.get(0).getKey());
            resultJson.setTopN1(infoIds.get(0).getValue());
            resultJson.setTop2(infoIds.get(1).getKey());
            resultJson.setTopN2(infoIds.get(1).getValue());
            resultJson.setTop3(infoIds.get(2).getKey());
            resultJson.setTopN3(infoIds.get(2).getValue());
        }else if(infoIds.size()==2){
            resultJson.setTop1(infoIds.get(0).getKey());
            resultJson.setTopN1(infoIds.get(0).getValue());
            resultJson.setTop2(infoIds.get(1).getKey());
            resultJson.setTopN2(infoIds.get(1).getValue());
        }else if(infoIds.size()==1){
            resultJson.setTop1(infoIds.get(0).getKey());
            resultJson.setTopN1(infoIds.get(0).getValue());
        }else{
            resultJson.setTop1("无汉字");
            resultJson.setTopN1(0);
        }
//        System.out.println(infoIds.get(0).getKey());
    }

    public static int countPunctuation(String str) {
        int count = 0;
        Pattern p =
                Pattern.compile("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");
        Matcher m = p.matcher(str);

        while(m.find()){

            count++;
        }
        return count;
    }
}
