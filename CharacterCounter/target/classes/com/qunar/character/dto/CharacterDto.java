package com.qunar.character.dto;

import lombok.Data;

import java.util.List;

@Data
public class CharacterDto {
    long englishWord = 0l;
    long chineseWord = 0l;
    long numberWord = 0l;
    long punctuationWord = 0l;
    List<String> wordList;
}
