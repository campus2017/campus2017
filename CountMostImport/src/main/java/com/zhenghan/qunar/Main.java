package com.zhenghan.qunar;

import com.google.common.base.Throwables;

import java.io.FileNotFoundException;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;

/**
 * Author: 郑含
 * Date: 2016/12/20
 * Time: 19:55
 */
public class Main {
    public static void main(String[] args) {
        if(args.length != 2){
            System.out.println("参数错误请输入如下格式:command [dirname] [encoding]");
            System.exit(1);
        }
        try {
            System.out.println(ImportService.countImportClass(args[0],args[1]));
            System.exit(0);
        } catch (FileNotFoundException e) {
            System.out.println("文件未被找到");
            System.exit(1);
        } catch (IllegalCharsetNameException | UnsupportedCharsetException e){
            System.out.println("encoding不支持");
            System.exit(1);
        }
    }
}
