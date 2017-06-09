package com.qunar.character.service;


import com.google.common.collect.Lists;
import com.qunar.character.dto.CharacterDto;
import com.qunar.character.enums.CharacterRegex;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterCounterServiceImpl implements CharacterCounterService {

    public CharacterDto countWords(StringBuffer content) {
        long chineseWord = 0l;
        long englishWord = 0l;
        long numberWord = 0l;
        long punctuationWord = 0l;
        List<String> words = Lists.newArrayList();
        CharacterDto characterDto = new CharacterDto();
        for (char c : content.toString().toCharArray()) {
            String temp = String.valueOf(c);
            if (temp.matches(CharacterRegex.CHINESE.toString())) {
                chineseWord++;
                words.add(temp);
            } else if (temp.matches(CharacterRegex.ENGLISH.toString())) {
                englishWord++;
                words.add(temp);
            } else if (temp.matches(CharacterRegex.NUMBER.toString())) {
                numberWord++;
                words.add(temp);
            } else if (temp.matches(CharacterRegex.EXTRA.toString())) {
                punctuationWord++;
                words.add(temp);
            }

        }
        characterDto.setChineseWord(chineseWord);
        characterDto.setEnglishWord(englishWord);
        characterDto.setNumberWord(numberWord);
        characterDto.setPunctuationWord(punctuationWord);
        characterDto.setWordList(words);

        return characterDto;
    }
}
