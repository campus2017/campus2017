package com.qunar.homework.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qunar.homework.entity.CountInfo;
import com.qunar.homework.service.CountService;
import com.qunar.homework.util.MapUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

/**
 * Created by dayong.gao on 2016/12/12.
 */
public class CountServiceImpl implements CountService {

    private static final Logger logger = LoggerFactory.getLogger(CountServiceImpl.class);
    private  CountInfo countInfo;

    private void getNum(String str) {
        String E1 = "[\u4e00-\u9fa5]";// 中文
        String E2 = "[a-zA-Z]";// 英文
        String E3 = "[0-9]";// 数字

        int chCount = 0;
        int enCount = 0;
        int numCount = 0;
        int puncCount = 0;
        String temp;
        for (int i = 0; i < str.length(); i++) {
            temp = String.valueOf(str.charAt(i));
            //logger.info("char:{}", temp);
            if (temp.matches(E1)) {
                chCount++;
            }
            if (temp.matches(E2)) {
                enCount++;
            }
            if (temp.matches(E3)) {
                numCount++;
            }
        }
        puncCount = str.length() - enCount - chCount - numCount;
        countInfo = new CountInfo(enCount, chCount, numCount, puncCount);
    }

    private void getNum(InputStream input) throws IOException {
        Reader reader =new InputStreamReader(input);
        String E1 = "[\u4e00-\u9fa5]";// 中文
        String E2 = "[a-zA-Z]";// 英文
        String E3 = "[0-9]";// 数字

        int chCount = 0;
        int enCount = 0;
        int numCount = 0;
        int puncCount = 0;
        int tempchar;
        String temp;
        int len=0;
            while ((tempchar = reader.read()) != -1) {
                len++;
                /* 字符统计 忽略/n /r*/
                if (((char) tempchar) != '\r'&&((char) tempchar) != '\n') {
                    temp=String.valueOf((char) tempchar);
                    //logger.info("char:{}", temp);
                    if (temp.matches(E1)) {
                        chCount++;
                    }
                    if (temp.matches(E2)) {
                        enCount++;
                    }
                    if (temp.matches(E3)) {
                        numCount++;
                    }
                }
            }
            reader.close();
        puncCount = len - enCount - chCount - numCount;
        countInfo = new CountInfo(enCount, chCount, numCount, puncCount);
    }

    private void getTop3(String str) {
        Map<Character, Integer> countMap = Maps.newHashMap();
        List<Map.Entry<Character, Integer>> resultlist = Lists.newArrayList();
        /* 借助Map实现对List的统计 不存在则添加，存在则数量++*/
        for (int i = 0; i < str.length(); i++) {
            char tem = str.charAt(i);
            if (!countMap.containsKey(tem)) {
                countMap.put(tem, 1);
            } else {
                countMap.put(tem, countMap.get(tem) + 1);
            }
        }
        resultlist = MapUtil.valuesort(countMap);
        countInfo.setTop3List(resultlist);
    }

    private void getTop3(InputStream input) throws IOException {
        Reader reader =new InputStreamReader(input);
        Map<Character, Integer> countMap = Maps.newHashMap();
        List<Map.Entry<Character, Integer>> resultlist = Lists.newArrayList();
        int tempchar;
        char tem;
        while ((tempchar = reader.read())!= -1) {
            tem=(char) tempchar;
            /* 字符统计 忽略/n /r*/
            if ((tem != '\r' && tem != '\n'&&tem!=' ')) {
                if (!countMap.containsKey(tem)) {
                    countMap.put(tem, 1);
                } else {
                    countMap.put(tem, countMap.get(tem) + 1);
                }
            }
        }
        reader.close();
        resultlist = MapUtil.valuesort(countMap);
        countInfo.setTop3List(resultlist);
    }

    @Override public CountInfo getCount(String str) {
        getNum(str);
        getTop3(str);
        return countInfo;
    }

    @Override public CountInfo getCount(InputStream input) throws IOException {
        input.mark(0);
        getNum(input);
        input.reset(); //重置流 ，在第一个函数中流已经读取到结尾
        getTop3(input);
        return countInfo;
    }
}
