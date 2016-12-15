package com.hadyang.counter.service;

import com.hadyang.counter.bean.JsonResult;
import org.junit.Test;

/**
 * Created by HadYang on 14/12/2016.
 */
public class CountServiceTest {

    CountService service = new CountService();

    @Test
    public void count() throws Exception {
        String str = "题目命名:CharacterCounter\n" +
                "统计一段文字中(1文本输入方式——要求完成、2文件上传方式——选做)英文字母、数字、中文汉字、中英 文标点符号的个数，以及文字中出现频率最高的三个字符(出现频率相同的，按字典顺序排)。\n" +
                "\uF073 点击“统计”按钮，以表格的形式显示以上统计结果，如下图所示。\n" +
                "\uF073 文本输入方式下，点击“清除内容”按钮可清空文本框中的内容。 要求:使用 Spring MVC\n" +
                "文件上传方式页面示例:\n" +
                " 文本输入方式页面示例:\n" +
                " 如果大家只完成了一种方式的输入，radio button 内容不展示，如下图(文本输入为例):\n" +
                " ";
        JsonResult count = service.count(str);
        System.out.println(count);
    }


}