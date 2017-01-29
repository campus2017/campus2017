package com.zdl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * Created by zdl on 2017/1/25.
 */
@Controller
@RequestMapping("/CharacterCounter")
public class CharacterCounterController {
    @RequestMapping(value = {"/",""})
    public String index(){
        return "index";
    };
    @RequestMapping("/statistic.dao")
    public String upload(String ta, Model model) {
        int characters = 0;
        int numbers = 0;
        int chineses = 0;
        int punctuations = 0;
        Character name1, name2, name3;
        int value1 = 0, value2 = 0, value3 = 0;
        Map<Character, Integer> freq = new HashMap<Character, Integer>();
        if (null == ta || ta.equals("")) {
            return "index";
        }
        for (int i = 0; i < ta.length(); ++i) {
            char tmp = ta.charAt(i);
            if ((tmp >= 'A' && tmp <= 'Z') || (tmp >= 'a' && tmp <= 'z')) {
                characters++;
            } else if (tmp >= '0' && tmp <= '9') {
                numbers++;
            } else if (isChinese(tmp)) {
                chineses++;
                if (freq.containsKey(tmp))
                    freq.put(tmp, freq.get(tmp) + 1);
                else
                    freq.put(tmp, 1);
            } else {
                Character.UnicodeBlock ub = Character.UnicodeBlock.of(tmp);
                if (ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                        || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                        || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS
                        || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
                    punctuations++;
                }
                if (tmp == 0x40 || tmp == 0x2D || tmp == 0x2F
                        || (0x23 <= tmp && tmp <= 0x26)
                        || (0x28 <= tmp && tmp <= 0x2B)
                        || (0x3C <= tmp && tmp <= 0x3E)
                        || (0x58 <= tmp && tmp <= 0x60)
                        || (0x78 <= tmp && tmp <= 0x7E))
                    punctuations++;
            }
        }
        PriorityQueue<Map.Entry<Character, Integer>> top3 = new PriorityQueue<Map.Entry<Character, Integer>>(3,
                new Comparator<Map.Entry<Character, Integer>>() {
                    public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                        return o1.getValue().compareTo(o2.getValue());
                    }
                });
        for(Map.Entry<Character, Integer> mapEntry: freq.entrySet()) {
            if (top3.size() < 3) {
                top3.offer(mapEntry);
            } else {
                top3.poll();
                top3.offer(mapEntry);
            }
        }
        ArrayList<Map.Entry<Character, Integer>> res = new ArrayList<Map.Entry<Character, Integer>>();
        while(!top3.isEmpty()) {
            res.add(top3.poll());
        }
        name3 = res.get(0).getKey();
        value3 = res.get(0).getValue();
        name2 = res.get(1).getKey();
        value2 = res.get(1).getValue();
        name1 = res.get(2).getKey();
        value1 = res.get(2).getValue();
        model.addAttribute("characters", characters);
        model.addAttribute("numbers", numbers);
        model.addAttribute("chineses", chineses);
        model.addAttribute("punctuations", punctuations);
        model.addAttribute("name1", name1);
        model.addAttribute("value1", value1);
        model.addAttribute("name2", name2);
        model.addAttribute("value2", value2);
        model.addAttribute("name3", name3);
        model.addAttribute("value3", value3);
        return "index";
    }
    private boolean isChinese(char ch) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(ch);
        //GENERAL_PUNCTUATION 判断中文的“号
        //CJK_SYMBOLS_AND_PUNCTUATION 判断中文的。号
        //HALFWIDTH_AND_FULLWIDTH_FORMS 判断中文的，号
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub ==
                Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B) {
            return true;
        }
        return false;
    }
}
