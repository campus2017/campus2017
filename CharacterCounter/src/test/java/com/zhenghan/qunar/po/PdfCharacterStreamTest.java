package com.zhenghan.qunar.po;

import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Author: 郑含
 * Date: 2017/1/19
 * Time: 0:34
 */
public class PdfCharacterStreamTest {
    @Test
    public void doPdfTest(){
        InputStream in = null;
        try {
            in = new FileInputStream(new File("D:\\学习\\qunaer作业\\应届生入职前自学内容&作业.pdf"));
            CharacterStream characterStream = new PdfCharacterStream(in);
            System.out.println(characterStream.countEveryWords());
            System.out.println(characterStream.countTypesWords());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(in != null)
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

    }
}
