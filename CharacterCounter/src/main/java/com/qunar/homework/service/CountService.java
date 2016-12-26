package com.qunar.homework.service;

import com.qunar.homework.entity.CountInfo;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by dayong.gao on 2016/12/12.
 */
public interface CountService {

    CountInfo getCount(String str);
    CountInfo getCount(InputStream input);

}
