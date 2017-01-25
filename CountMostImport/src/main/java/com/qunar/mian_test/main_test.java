package com.qunar.mian_test;

import com.qunar.common.ImportClassBean;
import com.qunar.common.ImportClassCount;

import java.util.List;

/**
 * Created by gcy0904 on 2017/1/20.
 */
public class main_test {
    public static void main(String args[]){
        ImportClassCount importClassCount = new ImportClassCount("E:\\qunar_test\\CountMostImport\\src\\main\\java");
        List<ImportClassBean> topImportClassName = importClassCount.getTopImportClassName(10);
        for (int i = 0; i < topImportClassName.size(); i++) {
            System.out.println(topImportClassName.get(i).toString());
        }
    }
}