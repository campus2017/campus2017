package com.charactercounter.controller;

import com.charactercounter.model.CounterResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by canda on 6/19/17.
 */
@Controller
@RequestMapping("/CharacterCounter")
public class CharacterCounter {
    @RequestMapping(value ="/index",method = RequestMethod.GET)
    public String inputView(){
        return "index";
    }

    @RequestMapping(value ="/index",method = RequestMethod.POST)
    public String resultView(@ModelAttribute CounterResult counterResult, Map<String, Object> model){
        if(!(counterResult.getInputString()==null||counterResult.getInputString().length()==0)){
            String str=counterResult.getInputString();
            //正则匹配英文字符
            Pattern pLetter= Pattern.compile("[a-zA-z]");
            //正则匹配数字
            Pattern pNum= Pattern.compile("[0-9]");
            //正则匹配中文汉字
            Pattern pCn= Pattern.compile("[\u4e00-\u9fa5]");
            //正则匹配标点符号
            Pattern pPunctution= Pattern.compile("\\pP");
            //统计结果
            int letterCount=0;
            int numberCount=0;
            int wordOfCnCount=0;
            int punctutionCount=0;
            //map存储词频
            Map<Character, Integer> map = new HashMap<Character, Integer>();
            for (int i = 0; i < str.length() ; i++) {
                String  item=String.valueOf(str.charAt(i));
                Matcher mLetter=pLetter.matcher(item);
                Matcher mNum=pNum.matcher(item);
                Matcher mCn=pCn.matcher(item);
                Matcher mPunctution=pPunctution.matcher(item);
                if (mLetter.matches()){
                    letterCount++;
                }
                if (mNum.matches()){
                    numberCount++;
                }
                if (mCn.matches()){
                    wordOfCnCount++;
                }
                if (mPunctution.matches()){
                    punctutionCount++;
                }
                char c=str.charAt(i);
                Integer count = map.get(c);
                map.put(c, count == null ? 1 : count + 1);
            }
            //对map根据value进行逆序排序
            List<Map.Entry<Character,Integer>> list=new ArrayList<Map.Entry<Character,Integer>>(map.entrySet());
            Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
                @Override
                public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                    return o2.getValue() - o1.getValue();
                }
            });

            if(list!=null && list.size()>0){
                Map.Entry<Character, Integer> first=list.get(0);
                counterResult.setFirstFrequencyKey(first.getKey());
                counterResult.setFirstFrequencyValue(first.getValue());
                if (list.size()>1){
                    Map.Entry<Character, Integer> second=list.get(1);
                    counterResult.setSecondFrequencyKey(second.getKey());
                    counterResult.setSecondFrequencyValue(second.getValue());
                }
                if (list.size()>2){
                    Map.Entry<Character, Integer> third=list.get(2);
                    counterResult.setThirdFrequencyKey(third.getKey());;
                    counterResult.setThirdFrequencyValue(third.getValue());
                }

            }
            counterResult.setInputString(str);
            counterResult.setLetterCount(letterCount);
            counterResult.setNumberCount(numberCount);
            counterResult.setWordOfCnCount(wordOfCnCount);
            counterResult.setPunctutionCount(punctutionCount);
            model.put("counterResult",counterResult);
        }

        return "index";
    }
}
