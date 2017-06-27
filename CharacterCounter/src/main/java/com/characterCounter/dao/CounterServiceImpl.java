package com.characterCounter.dao;

import com.characterCounter.domain.Counter;
import com.characterCounter.service.CounterService;
import com.google.common.collect.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/6/25.
 */
@Service
public class CounterServiceImpl implements CounterService {

    /**
     * 获取字符计算结果
     * @param scanner
     * @return
     */
    public Counter getCountResult(Scanner scanner) {

        Counter counter = new Counter();
        Multiset<String> charactersMultiSet = TreeMultiset.create();
        List<Multiset.Entry<String>> list = new ArrayList<Multiset.Entry<String>>();
        String line = "";
        String engReg = "[a-zA-Z]";// 英文
        String numReg = "[0-9]";// 数字
        String chiReg = "[\u4e00-\u9fa5]";// 中文
        String punReg = "\\pP"; //中英文标点符号
        long engCount = 0;
        long numCount = 0;
        long chiCount = 0;
        long punCount = 0;
        //逐行读取
        while(scanner.hasNextLine()) {
            line = scanner.nextLine();
            //对每行的逐个字符进行正则匹配
            for(int i = 0; i < line.length(); i++) {
                String str = String.valueOf(line.charAt(i));
                if(Pattern.matches(engReg,str)) {
                    engCount++;
                    charactersMultiSet.add(str);
                } else if(Pattern.matches(numReg,str)) {
                    numCount++;
                    charactersMultiSet.add(str);
                } else if(Pattern.matches(chiReg,str)) {
                    chiCount++;
                    charactersMultiSet.add(str);
                } else if(Pattern.matches(punReg,str)) {
                    punCount++;
                    charactersMultiSet.add(str);
                }
            }
        }
        //逆序set
        ImmutableMultiset<String> set = Multisets.copyHighestCountFirst(charactersMultiSet);
        ImmutableSet<Multiset.Entry<String>> entries = set.entrySet();
        //取最大的前3个entry
        int num = 3;
        for(Multiset.Entry<String> entry : entries) {
            if(--num < 0) {
                break;
            }
            list.add(entry);
        }
        //设置各类字符数量
        counter.setEngAlphabet(engCount);
        counter.setNumber(numCount);
        counter.setChiCharacters(chiCount);
        counter.setPunctuation(punCount);
        counter.setHighFrequency(list);
        return counter;
    }
}
