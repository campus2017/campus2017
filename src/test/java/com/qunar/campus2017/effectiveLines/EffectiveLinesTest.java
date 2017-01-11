package com.qunar.campus2017.effectiveLines;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

/**
 * Created by chunming.xcm on 2017/1/5.
 */
public class EffectiveLinesTest {
    @Test
    public void testLines() {
        EffectiveLines effectiveLines = new EffectiveLines();
        int count1;
        int count2;
        String path1 = "src/main/resources/test.java";
        String path2 = "src/main/resources/test.txt";
        count1 = effectiveLines.countLines(path1);
        count2 = effectiveLines.countLines(path2);
        Assert.assertEquals(17, count1);
        Assert.assertNotEquals(17, count2);
    }
}
