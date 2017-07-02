package com.renhao.service.com.renhao.service.impl;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.renhao.model.ChineseCountPair;
import com.renhao.model.CountResult;
import com.renhao.service.CharacterCounterService;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class CharacterCountServiceImpl implements CharacterCounterService {

    public CountResult getResultOfText(String text) {
        CountResult result = new CountResult();
        result.setChinese(countOfChinese(text));
        result.setAlphabet(countOfLetter(text));
        result.setNumber(countOfNumber(text));
        result.setPunctuation(countOfPunctuation(text));
        result.setChineseList(sortChinese(text));
        return result;
    }

    public CountResult getResultOfFile(MultipartFile file) {
        String text = transToString(file);
        System.out.println(text);
        return getResultOfText(text);
    }

    private String transToString(MultipartFile file) {
        String text = null;
        try {
            text = IOUtils.toString(file.getInputStream(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    /*
     * 工具方法,通过给定的正则表达式得到统计次数
     * text: 需要处理的文本   reg: 正则表达式
     */
    private int calCount(String text, String reg) {
        int number = 0;
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            number++;
        }
        return number;
    }

    /*
     * 统计字母
     */
    private int countOfLetter(String text) {
        return calCount(text, "[a-zA-Z]");
    }

    /*
     * 统计数字
     */
    private int countOfNumber(String text) {
        return calCount(text, "[0-9]");
    }

    /*
     * 统计汉字
     */
    private int countOfChinese(String text) {
        return calCount(text, "[\\u4e00-\\u9fa5]");
    }

    /*
     * 统计标点符号
     */
    private int countOfPunctuation(String text) {
        return calCount(text, "\\pP");
    }

    /*
     * 频率最高的三个文字
     */
    private List<ChineseCountPair> sortChinese(String text) {
        List<String> chinese = new ArrayList<>();
        Pattern pattern = Pattern.compile("[\\u4e00-\\u9fa5]");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            chinese.add(matcher.group());
        }
        Multiset<String> multiset = HashMultiset.create();
        multiset.addAll(chinese);
        List<ChineseCountPair> top3Chinese = new ArrayList<>();
        for (String key : multiset.elementSet()) {
            top3Chinese.add(new ChineseCountPair(key, multiset.count(key)));
        }
        Collections.sort(top3Chinese);
        if (top3Chinese.size() <= 3) {
            return top3Chinese;
        } else {
            return top3Chinese.subList(0, 3);
        }
    }
}
