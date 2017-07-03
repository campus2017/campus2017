package com.hepeng.controller;


import java.util.*;
import java.util.regex.Pattern;

public class Counter {
    private  String inputString;
    private  int alphbetCnt;
    private int numCnt;
    private int chineseCnt;
    private int splitCnt;

    private  HashMap<Character , Integer>  countMostMap;

    public String getInputString() {
        return inputString;
    }

    public int getAlphbetCnt() {
        return alphbetCnt;
    }

    public int getNumCnt() {
        return numCnt;
    }

    public int getChineseCnt() {
        return chineseCnt;
    }

    public int getSplitCnt() {
        return splitCnt;
    }

    public Counter(String inputString)
    {
        this.inputString = inputString;
        this.chineseCnt =0;
        this.alphbetCnt=0;
        this.numCnt =0;
        this.splitCnt =0;

        countMostMap = new HashMap<>();
        countMostMap.clear();
    }
/*统计数量*/
    public void numberProcess()
    {
        int len = inputString.length();

        for(int i =0 ; i < len ; ++i)
        {
            // 汉字
            if (Pattern.compile("[\u4e00-\u9fa5]").matcher(
                    String.valueOf(inputString.charAt(i))).find()) {
                    chineseCnt ++;
            }else if( (inputString.charAt(i)>='A'&& inputString.charAt(i)<='Z') ||
                    (inputString.charAt(i)>='a'&& inputString.charAt(i)<='z') )
            {
                alphbetCnt ++;
            }else if((inputString.charAt(i)>='0'&& inputString.charAt(i)<='9'))
            {
                numCnt++;
            }
            else
            {
                splitCnt++;
            }
        }
    }

    public  String countmostProcess ()
    {
        int len = inputString.length();
        for( int i = 0; i < len; ++i)
        {
            if(countMostMap.containsKey(inputString.charAt(i)))
            {
                Integer temVal = countMostMap.get(inputString.charAt(i));
                temVal ++;
                countMostMap.replace(inputString.charAt(i),temVal);
            }else
            {
                countMostMap.put(inputString.charAt(i),1);
            }

        }

        // sort
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(countMostMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        String resultString = new String("");
        if(list.size() <=3)
            for(int i=0 ;i < 3 ; ++i)
                resultString = resultString+";"+ list.get(i);
        if(list.size() >3)
        {
            for(int i=0 ;i < 3 ; ++i)
                resultString = resultString+";"+ list.get(i);
        }

        System.out.println(resultString);

        return  resultString;
    }


}
