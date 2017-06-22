package cn.xuchunh.charcounter.test.util;

import cn.xuchunh.charcounter.util.StringUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created on 2017/6/12.
 *
 * @author XCH
 */
public class StringUtilTest {

    @Test
    public void testIsLetter(){
        Assert.assertFalse(StringUtil.isLetter('奋'));
        Assert.assertTrue(StringUtil.isLetter('c'));
        Assert.assertTrue(StringUtil.isLetter('C'));
        Assert.assertFalse(StringUtil.isLetter('#'));
        Assert.assertFalse(StringUtil.isLetter('【'));
    }

    @Test
    public void testIsChinese(){
        Assert.assertTrue(StringUtil.isChinese('需'));
        Assert.assertFalse(StringUtil.isChinese('c'));
        Assert.assertFalse(StringUtil.isChinese('C'));
        Assert.assertFalse(StringUtil.isChinese(','));
        Assert.assertFalse(StringUtil.isChinese('，'));
        Assert.assertFalse(StringUtil.isChinese(' '));
    }

    @Test
    public void testIsChinesePunctuation(){
        Assert.assertTrue(StringUtil.isPunctuation('，'));
        Assert.assertTrue(StringUtil.isPunctuation(','));
        Assert.assertFalse(StringUtil.isPunctuation('$'));
    }

}
