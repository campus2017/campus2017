package cn.xuchunh.charcounter.util;

import com.google.common.base.Preconditions;
import info.monitorenter.cpdetector.io.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Created on 2017/6/13.
 *
 * @author XCH
 */
public class FileUtil {

    public static String getFileEncode(File file) throws IOException {

        Preconditions.checkArgument(file != null && file.exists(), "文件不存在!");

        CodepageDetectorProxy detector = buildDetector();

        return detectEncode(detector.detectCodepage(file.toURI().toURL()));
    }

    public static String getFileEncode(InputStream in) throws IOException {
        Preconditions.checkArgument(in != null, "输入流不存在");
        CodepageDetectorProxy detector = buildDetector();
        detector.add(new ByteOrderMarkDetector());
        try {
            return detectEncode(detector.detectCodepage(in, 8));
        } finally {
            in.close();
        }
    }

    private static CodepageDetectorProxy buildDetector() {
        CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();

        detector.add(new ParsingDetector(false));
        detector.add(UnicodeDetector.getInstance());
        detector.add(JChardetFacade.getInstance());//内部引用了 chardet.jar的类
        detector.add(ASCIIDetector.getInstance());
        return detector;
    }

    private static String detectEncode(Charset charset) {
        String charsetName = "UTF-8";
        if (charset != null) {
            if (charset.name().equals("US-ASCII")) {
                charsetName = "ISO_8859_1";
            } else {
                charsetName = charset.name();
            }
        }
        return charsetName;
    }


}
