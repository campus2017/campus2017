package com.qunar.character.service;


import com.qunar.character.dto.CharacterDto;

public interface CharacterCounterService {
    public CharacterDto countWords(StringBuffer content);
}
