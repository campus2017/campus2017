package cn.xuchunh.charcounter.test.util;

import cn.xuchunh.charcounter.util.FileUtil;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;

/**
 * Created on 2017/6/13.
 *
 * @author XCH
 */
public class FileUtilTest {

    @Test
    public void testGetFileEncode() throws IOException {
        File file = new File(FileUtilTest.class.getResource("/").getPath(), "test.txt");
        Assert.assertEquals("UTF-8", FileUtil.getFileEncode(file));
    }

    @Test
    public void testGetFileEncodeByStream() throws IOException {
        File file = new File(FileUtilTest.class.getResource("/").getPath(), "test.txt");
        Assert.assertEquals("UTF-8", FileUtil.getFileEncode(new BufferedInputStream(new FileInputStream(file))));
    }

}
