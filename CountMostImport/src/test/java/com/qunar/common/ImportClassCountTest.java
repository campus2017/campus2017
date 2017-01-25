package com.qunar.common;

import org.junit.Test;

import java.util.List;

/**
 * Created by gcy0904 on 2017/1/20.
 */
public class ImportClassCountTest {

    @Test
    public void testGetMostImportClazzName() throws Exception {
        ImportClassCount importClassCount = new ImportClassCount("E:\\qunar_test\\CountMostImport\\src\\main\\java\\com\\qunar\\common\\ImportClassCount.java");
        ImportClassBean mostImportBean = importClassCount.getMostImportClazzName();
        System.out.print(mostImportBean.toString());
    }

    @Test
    public void testgetTopImportClassName() throws Exception {
        ImportClassCount importClassCount = new ImportClassCount("E:\\qunar_test\\CountMostImport\\src\\main\\java\\com\\qunar\\common\\ImportClassCount.java");
        List<ImportClassBean> topImportClassName = importClassCount.getTopImportClassName(10);
        for (int i = 0; i < topImportClassName.size(); i++) {
            System.out.println(topImportClassName.get(i).toString());
        }
    }

    @Test
    public void testgetTopImportClassName2() throws Exception {
        ImportClassCount importClassCount = new ImportClassCount("E:\\qunar_test\\CountMostImport\\src\\main\\java\\com\\qunar\\common\\ImportClassCount.java");
        importClassCount.getTopImportClassName2();
    }
}