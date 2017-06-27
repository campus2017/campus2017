package com.characterCounter.service;

import com.characterCounter.domain.Counter;

import java.util.Scanner;

/**
 * Created by Administrator on 2017/6/24.
 */
public interface CounterService {

    /**
     * 获取字符计算结果
     * @param scanner
     * @return
     */
    public Counter getCountResult(Scanner scanner);
}
