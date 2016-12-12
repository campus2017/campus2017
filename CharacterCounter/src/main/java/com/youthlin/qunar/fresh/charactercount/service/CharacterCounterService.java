package com.youthlin.qunar.fresh.charactercount.service;

import com.google.common.collect.Multiset;
import com.youthlin.qunar.fresh.charactercount.vo.CountResult;
import org.springframework.stereotype.Service;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.youthlin.qunar.fresh.charactercount.util.CharacterUtil.isChinese;

/**
 * Created by youthlin.chen on 2016-12-12 012.
 * Service 层
 */
@Service
public class CharacterCounterService {
    public CountResult process(Scanner scanner) {
        CountResult result = new CountResult();
        Multiset<Character> multiset = result.getWords();
        String line;
        int len;
        long symbols = 0;
        long enWords = 0;
        long digital = 0;
        long cnWords = 0;
        Pattern symbolPattern = Pattern.compile("\\pP");
        Pattern enPattern = Pattern.compile("\\p{Alpha}");
        Pattern dgPattern = Pattern.compile("\\p{Digit}");
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            Matcher symbol = symbolPattern.matcher(line);
            Matcher en = enPattern.matcher(line);
            Matcher dg = dgPattern.matcher(line);
            while (symbol.find()) {
                symbols++;
            }
            while (en.find()) {
                enWords++;
            }
            while (dg.find()) {
                digital++;
            }

            len = line.length();
            for (int i = 0; i < len; i++) {
                Character c = line.charAt(i);
                //if (!Character.isWhitespace(c)) {
                //noinspection ResultOfMethodCallIgnored 该注释告诉IDEA忽略返回值
                multiset.add(c);
                //}
                if (isChinese(c) && !c.toString().matches("\\pP")) {
                    cnWords++;
                }
            }
            //noinspection ResultOfMethodCallIgnored
            multiset.add('\n');
        }
        return result.setEnWords(enWords).setNumbers(digital).setCnWords(cnWords).setSymbols(symbols)
                .setTop3(result.getTopkWords(3));
    }
}
