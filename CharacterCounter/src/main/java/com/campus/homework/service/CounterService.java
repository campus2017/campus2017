package com.campus.homework.service;

import java.io.BufferedReader;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/2.
 */

public interface CounterService {
    Map countCharacter(BufferedReader br);
    Map countCharacter(String str);
    Map SortCharacter();
}
