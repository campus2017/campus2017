package com.qunar.hw.character_counter.service;

import com.qunar.hw.character_counter.bean.CharCount;
import com.qunar.hw.character_counter.bean.CountRes;
import com.qunar.hw.character_counter.bean.StatisticsInfo;
import com.qunar.hw.character_counter.bean.Top3Info;
import com.qunar.hw.character_counter.system.CountConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * Created by runsheng.zhou on 2017/2/5.
 */
@Service
public class CountService {

    public CountRes process(String text) {
        CountRes countRes = new CountRes();

        StatisticsInfo statisticsInfo = statistics(text);
        countRes.setStatisticsInfo(statisticsInfo);

        Top3Info top3Info = top3(text);
        countRes.setTop3Info(top3Info);

        return countRes;
    }

    private Top3Info top3(String text) {

        Map<Character, Integer> charMap = new TreeMap<Character, Integer>();
        for (int i = 0; i < StringUtils.length(text); i++) {
            char c = text.charAt(i);
            if(charMap.containsKey(c))
                charMap.put(c, charMap.get(c) + 1);
            else
                charMap.put(c, 1);
        }

        List<Map.Entry<Character, Integer>> charList = new LinkedList<Map.Entry<Character, Integer>>(charMap.entrySet());
        Collections.sort(charList, new Top3Comparator());

        return buildTop3Info(charList);
    }

    private Top3Info buildTop3Info(List<Map.Entry<Character, Integer>> charList) {
        Top3Info top3 = new Top3Info();

        CharCount theMost = new CharCount();
        CharCount secondMore = new CharCount();
        CharCount thirdMore = new CharCount();

        theMost.setCountKey(charList.get(0).getKey());
        secondMore.setCountKey(charList.get(1).getKey());
        thirdMore.setCountKey(charList.get(2).getKey());

        theMost.setCountValue(charList.get(0).getValue());
        secondMore.setCountValue(charList.get(1).getValue());
        thirdMore.setCountValue(charList.get(2).getValue());

        top3.setMost(theMost);
        top3.setSecondMore(secondMore);
        top3.setThirdMore(thirdMore);

        return top3;
    }

    private StatisticsInfo statistics(String text) {
        StatisticsInfo info = new StatisticsInfo();
        long numbers = pattern(CountConstant.NUMBERS_PATTERN, text);
        long chineseChars = pattern(CountConstant.CHINESE_CHARS_PATTERN, text);
        long letters = pattern(CountConstant.LETTERS_PATTERN, text);
        long punctuations = (long)StringUtils.length(text) - numbers - chineseChars - letters;
        info.setNumbers(numbers);
        info.setLetters(letters);
        info.setChineseChars(chineseChars);
        info.setPunctuations(punctuations);
        return info;
    }

    private long pattern(String pattern, String text) {
        long num = 0;
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(text);
        while (m.find()) {
            num++;
        }
        return num;
    }

    class Top3Comparator implements Comparator<Map.Entry<Character, Integer>> {

        @Override
        public boolean equals(Object obj) {
            return false;
        }

        @Override
        public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
            if (o1.getValue().equals(o2.getValue()))
                return o1.getKey().compareTo(o2.getKey());
            return o2.getValue() - o1.getValue();
        }
    }
}
