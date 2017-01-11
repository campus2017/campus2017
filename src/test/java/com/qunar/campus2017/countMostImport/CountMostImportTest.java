package com.qunar.campus2017.countMostImport;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * Created by chunming.xcm on 2017/1/11.
 */
public class CountMostImportTest {
    @Test
    public void testImport() {
        GetTop10 getTop10 = new GetTop10();
        File dir = new File("src");
        List<String> result = getTop10.top10(dir);
        Assert.assertEquals(result.size(), 10);
    }
}
