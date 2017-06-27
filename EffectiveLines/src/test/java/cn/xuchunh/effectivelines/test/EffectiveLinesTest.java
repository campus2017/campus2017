package cn.xuchunh.effectivelines.test;

import cn.xuchunh.effectivelines.EffectiveLines;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created on 2017/4/5.
 *
 * @author XCH
 */
public class EffectiveLinesTest {

    @Test(expected = IllegalArgumentException.class)
    public void testNullPath() throws IOException {
        EffectiveLines.effectiveLines(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyPath() throws IOException {
        EffectiveLines.effectiveLines("    ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidJavaFile() throws IOException {
        EffectiveLines.effectiveLines("./Test");
    }

    @Test(expected = FileNotFoundException.class)
    public void testInvalidPath() throws IOException {
        EffectiveLines.effectiveLines("xuchunh/Test.java");
    }

    @Test
    public void testNormalCase() throws IOException {
        Assert.assertEquals(12, EffectiveLines.effectiveLines(EffectiveLines.class.getResource("/").getPath() + "Test.java"));
    }

}
