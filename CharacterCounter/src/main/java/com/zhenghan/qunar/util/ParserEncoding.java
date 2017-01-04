package com.zhenghan.qunar.util;


import com.google.common.io.ByteSink;
import com.google.common.io.ByteSource;
import com.google.common.io.Files;
import info.monitorenter.cpdetector.io.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Created by Administrator on 2016/11/19.
 * 判断文件编码格式
 */
public enum ParserEncoding {
    INSTANCE;
    private  ByteOrderMarkDetector byteOrderMarkDetector =  new ByteOrderMarkDetector();
    private CodepageDetectorProxy fastDetectorProxy = null;
    public Charset detectorEncoding(byte[] buffer){
        try {
            return getFastDetectorProxy().detectCodepage(new ByteArrayInputStream(buffer),buffer.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public CodepageDetectorProxy getFastDetectorProxy(){
        if(fastDetectorProxy!=null){
            return fastDetectorProxy;
        }
        fastDetectorProxy = CodepageDetectorProxy.getInstance();
        fastDetectorProxy.add(UnicodeDetector.getInstance());
        fastDetectorProxy.add(ASCIIDetector.getInstance());
        fastDetectorProxy.add(JChardetFacade.getInstance());
        fastDetectorProxy.add(byteOrderMarkDetector);
        return fastDetectorProxy;
    }


}
