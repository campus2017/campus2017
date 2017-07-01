package com.charactercounter.service;

import java.util.HashMap;

/**
 * Created by yangyening on 2017/2/19.
 */
public interface characterCounterService {
    public HashMap<String,Integer> countCharacter(String character) ;
    public HashMap<String,Integer> countMostCharacter(String character);
}
