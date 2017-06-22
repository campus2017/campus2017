package cn.xuchunh.charcounter.service.impl;

import cn.xuchunh.charcounter.model.TextStats;
import cn.xuchunh.charcounter.service.CharCounterService;
import cn.xuchunh.charcounter.util.StringUtil;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Chars;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created on 2017/6/12.
 *
 * @author XCH
 */
@Service("charCounterService")
public class CharCounterServiceImpl implements CharCounterService {

    public TextStats parse(String content) {
        Preconditions.checkArgument(!StringUtils.isEmpty(content), "文本内容不能为空");
        TextStats textStats = new TextStats();
        char[] source = content.toCharArray();
        int letterCount = 0;
        int numberCount = 0;
        int chineseCount = 0;
        int punctuationCount = 0;
        final Multiset<Character> multiset = HashMultiset.create();
        for (char c : source){
            if (StringUtil.isLetter(c)){
                letterCount++;
            }else if (Character.isDigit(c)){
                numberCount++;
            }else if (StringUtil.isChinese(c)){
                chineseCount++;
            }else if (StringUtil.isPunctuation(c)){
                punctuationCount ++;
            }
            if (c != ' ') {
                multiset.add(c);
            }
        }
        Ordering<Character> ordering = Ordering.natural().onResultOf(new Function<Character, Integer>() {
            public Integer apply(Character input) {
                return multiset.count(input);
            }
        }).compound(Chars::compare);
        List<Character> characters = ordering.greatestOf(multiset.elementSet(), 3);
        LinkedHashMap<Character, Integer> top = new LinkedHashMap<>();
        for (Character ch : characters){
            top.put(ch, multiset.count(ch));
        }
        textStats.setTop(top);
        textStats.setLetterCount(letterCount);
        textStats.setNumberCount(numberCount);
        textStats.setChineseCount(chineseCount);
        textStats.setPunctuationCount(punctuationCount);
        return textStats;
    }

}
