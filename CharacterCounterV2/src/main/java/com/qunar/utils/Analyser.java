package com.qunar.utils;

import com.qunar.model.Data;
import com.qunar.model.Most;
import com.qunar.model.Result;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xiazihao on 7/3/17.
 */
public class Analyser {
    public static Result analyse(String string) {
        String text = string;
        text = text.replaceAll(" ", "");
        text = text.replaceAll("\n", "");
        Data data = Analyser.statistics(text);
        List<Most> mostList = Analyser.getMost(text);
        Result result = new Result(data, mostList);
        return result;
    }

    public static Data statistics(String text) {
        Data data = new Data();
        Integer english = 0;
        Integer chinese = 0;
        Integer punctuation = 0;
        Integer number = 0;
        String chinese_reg = "^[\u4e00-\u9fa5]$";
        String number_reg = "[0-9]";
        String english_reg = "[A-Z]|[a-z]";
        for (char item :
                text.toCharArray()) {
            if (Pattern.matches(chinese_reg, "" + item)) {
                chinese++;
            } else if (Pattern.matches(english_reg, "" + item)) {
                english++;
            } else if (Pattern.matches(number_reg, "" + item)) {
                number++;
            } else {
                punctuation++;
            }
        }
        data.setChinese(chinese);
        data.setEnglish(english);
        data.setNumber(number);
        data.setPunctuation(punctuation);
        return data;
    }

    public static String getChinese(String text) {
        String re = "(([\u4e00-\u9fa5]|[A-Z]|[a-z])+)";
        String result = "";
        Matcher matcher = Pattern.compile(re).matcher(text);
        while (matcher.find()) {
            result += matcher.group(0);
        }
        return result;
    }

    public static List<Most> getMost(String text) {
        Map<String, Integer> map = new TreeMap<String, Integer>();
        String chinese = getChinese(text);
        for (char single : chinese.toCharArray()) {
            if (map.containsKey("" + single)) {
                map.put("" + single, map.get("" + single) + 1);
            } else {
                map.put("" + single, 1);
            }
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o2.getValue().compareTo(o1.getValue()) == 0) {
                    return o2.getKey().compareTo(o1.getKey());
                }
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        List<Most> result = new ArrayList<Most>();
        if (list.size() >= 3) {

            for (int i = 0; i < 3; i++) {
                Most most = new Most();
                most.setName(list.get(i).getKey());
                most.setNum(list.get(i).getValue());
                result.add(most);
            }
        } else {
            for (Map.Entry<String, Integer> m : list) {
                Most most = new Most();
                most.setNum(m.getValue());
                most.setName(m.getKey());
                result.add(most);
            }
        }
        return result;
    }
}
