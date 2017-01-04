package com.zhenghan.qunar.po;

import com.zhenghan.qunar.Exception.notSupportTypeCountException;
import jdk.internal.util.xml.impl.Input;
import org.springframework.http.MediaType;

import java.io.InputStream;

/**
 * Author: 郑含
 * Date: 2016/12/13
 * Time: 11:04
 */
public class CharacterStreams {
    public final static String TEXT = "txt";
    public final static String PDF = " pdf";
    public final static String DOC = "doc";
    public static CharacterStream pdf(InputStream in){
        return null;
    }
    public static CharacterStream doc(InputStream in) {return null;}
    public static CharacterStream text(InputStream in){
        return new TextCharacterStream(in);
    }
    public static CharacterStream commons(InputStream in){
        return new TextCharacterStream(in);
    }
    public static CharacterStream commons(String strs){
        return new StringCharacterStream(strs);
    }

    public static CharacterStream getByTypeName(InputStream in,String typeName) throws notSupportTypeCountException {
        if(typeName.equals(TEXT)){
                return text(in);
        }else if(typeName.equals(PDF)){
            return pdf(in);
        }else if(isNotSupportType(typeName)){
            throw new notSupportTypeCountException("不支持该类型");
        }
        return commons(in);
    }

    private static boolean isNotSupportType(String typeName) {
        return (typeName.equals("jpg") ||
                typeName.equals("png") ||
                typeName.equals("rar") ||
                typeName.equals("war") );
    }
}
