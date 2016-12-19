package com.qunar.flight.zechuan_guo.charactercount.service;

import java.util.Map;
import java.util.Scanner;

/**
 * Created by zechuan.guo on 2016/12/12.
 */
public interface ITestService {
    public Map countLength(String str);
    public Map countLengthByFile(Scanner scanner);


}
