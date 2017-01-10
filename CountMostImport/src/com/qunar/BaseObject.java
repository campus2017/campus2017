package com.qunar;

import java.util.Date;

/**
 * Created by WanlongMa on 2016/12/21.
 */
public abstract class BaseObject {

    // 错误类型：源文件未找到
    public static final int ERROR_CODE_FILE_NOR_FOUND = 0X01;


    public static void throwError(int errorCode,String message){
        System.err.println("Error:" + message + "\t(ErrorCode:" + errorCode + ")");
    }

    public static void log(String message){
        System.out.println(new Date().toLocaleString() + " Message:" + message);
    }

}
