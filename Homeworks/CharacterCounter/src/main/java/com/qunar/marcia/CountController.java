package com.qunar.marcia;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

/**
 * Created by Administrator on 2017/7/2.
 */

@Controller
@RequestMapping("/countCharacter")
public class CountController {

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public String count() {
        return "count";
    }


    @RequestMapping("/counter")
    public String countCharacter(String str, Model model) {
        if (str == null || str.length() == 0) {
            return "count";
        }

        Map<Character, Integer> charCountMap = new HashMap<>();

        int englishCharacters = 0;
        int numbers = 0;
        int chineseCharacters = 0;
        int punctuations = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            Character.UnicodeScript sc = Character.UnicodeScript.of(c);
            Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);

            if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
                englishCharacters++;
                if (charCountMap.containsKey(c)) {
                    charCountMap.put(c, charCountMap.get(c) + 1);
                } else {
                    charCountMap.put(c, 1);
                }
            } else if (sc == Character.UnicodeScript.HAN) {
                chineseCharacters++;
                if (charCountMap.containsKey(c)) {
                    charCountMap.put(c, charCountMap.get(c) + 1);
                } else {
                    charCountMap.put(c, 1);
                }
            } else if (c >= '0' && c <= '9') {
                numbers++;
            } else if (ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                    || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                    || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                    || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS
                    || ub == Character.UnicodeBlock.VERTICAL_FORMS) {
                punctuations++;
            } else if ((0x21 <= c && c <= 0x22)
                    || (c == 0x27 || c == 0x2C)
                    || (c == 0x2E || c == 0x3A)
                    || (c == 0x3B || c == 0x3F)) {
                punctuations++;
            }
        }

        List<Map.Entry<Character, Integer>> entryList = new ArrayList<>(charCountMap.entrySet());
        entryList.sort(new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        model.addAttribute("englishCharacters", englishCharacters);
        model.addAttribute("numbers", numbers);
        model.addAttribute("chineseCharacters", chineseCharacters);
        model.addAttribute("punctuations", punctuations);

        model.addAttribute("name1", entryList.get(0).getKey());
        model.addAttribute("val1", entryList.get(0).getValue());
        model.addAttribute("name2", entryList.get(1).getKey());
        model.addAttribute("val2", entryList.get(1).getValue());
        model.addAttribute("name3", entryList.get(2).getKey());
        model.addAttribute("val3", entryList.get(2).getValue());

        return "count";
    }
}