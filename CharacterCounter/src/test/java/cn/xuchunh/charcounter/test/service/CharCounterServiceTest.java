package cn.xuchunh.charcounter.test.service;

import cn.xuchunh.charcounter.service.CharCounterService;
import cn.xuchunh.charcounter.test.BaseSpringTest;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created on 2017/6/12.
 *
 * @author XCH
 */
public class CharCounterServiceTest extends BaseSpringTest {

    @Resource(name = "charCounterService")
    private CharCounterService charCounterService;

    private String text;

    @Before
    public void before() {
        text = "关于实现Android 透明状态栏，Android 提供的fitsSystemWindows方法很让人困惑，4.4 和5.0+之间存在效果差异，" +
                "同时Support 包下的一些控件（如CoordinatorLayout 等）都自定义了自己的行为效果，在兼容到4.4会遇到各种坑。";
    }

    @Test
    public void testParse() {
        System.out.println(charCounterService.parse(text));
    }

}
