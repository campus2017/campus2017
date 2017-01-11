package com.qunar.campus2017.countMostImport;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chunming.xcm on 2017/1/11.
 */
public class GetTop10 {
    /**
     * 返回最终结果
     * @param directory 指定目录
     * @return
     */
    public List<String> top10(File directory) {
        CountMostImport countMostImport = new CountMostImport();
        countMostImport.travDirectory(directory);
        countMostImport.travFile();
        List<Map.Entry<String, Integer>> list = countMostImport.sort();
        List<String> result = new ArrayList<String>();
        if(list == null || list.size() == 0) {
            System.out.println("没有被Import的类!");
            return result;
        }
        int n = 10;
        if(list.size() < 10) {
            n = list.size();
            System.out.println("被Import的类不足10个!");
        }
        for(int i = 0; i < n; i++) {
            result.add(list.get(i).getKey() + "被Import" + list.get(i).getValue() + "次");
        }
        return result;
    }
}
