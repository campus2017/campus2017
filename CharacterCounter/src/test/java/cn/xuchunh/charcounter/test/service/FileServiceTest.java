package cn.xuchunh.charcounter.test.service;

import cn.xuchunh.charcounter.service.FileService;
import cn.xuchunh.charcounter.test.BaseSpringTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Random;

/**
 * Created on 2017/6/13.
 *
 * @author XCH
 */


public class FileServiceTest extends BaseSpringTest{

    @Resource
    private FileService fileService;

    private String content;

    private String name;

    @Before
    public void before(){
        content = "测试" +
                "测试" +
                "测试";
        name = "测试" + new Random().nextFloat();
    }


    @Test
    public void testInsert() {
        int id = fileService.insert(name, content);
        System.out.println(id);
        Assert.assertTrue(id > 0);
    }

    @Test
    public void testFGetContentById(){
        Assert.assertEquals(content, fileService.getContentById(fileService.insert(name, content)));
    }

}
