package com.qunar.QA;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Date; 
import java.util.Scanner; 
import java.lang.System;
import javax.swing.*; 

/**
 * @Author Nicole
 * @Time 2017/7/2
 * @Description 获取引用前十的类。不足十就全部列出。
 */

public class GetMostImport {

    public void getMostImport(HashMap<String,Integer> importClassRecords) {
        int max = Integer.MIN_VALUE;
        String className = null;
        Integer value=null;
        for (int i = 0; i < 10 && importClassRecords != null; i++) {
            for (Map.Entry item : importClassRecords.entrySet()) {
                String key = (String) item.getKey();
                value = (Integer) item.getValue();
                if (value > max) {
                    max = value;
                    className = key;
                }
            }
            System.out.println("ImportClassName:"+className+",ImportTimes:"+value);
            max = Integer.MIN_VALUE;
            importClassRecords.remove("className");

        }

    }
}
