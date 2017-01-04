package com.zhenghan.qunar;


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
    public Charset detectorEncoding(File file){
        InputStream in  =null;
        try {
            byte[] buffer = Files.toByteArray(file);
            in = new ByteArrayInputStream(buffer);
            return getFastDetectorProxy().detectCodepage(in,buffer.length);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(in!=null)
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
