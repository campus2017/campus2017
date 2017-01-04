package com.zhenghan.qunar;


import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.io.Files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Administrator on 2016/11/17.
 */
public class CountEffectiveLines {
    private String filepath;
    private Charset charset;
    public CountEffectiveLines(String filepath,Charset charset){
        this.filepath = filepath;
        this.charset = charset;
    }
    public int count(){
        try {
            List<String> fileLines = Files.readLines(new File(filepath),charset);
            Iterable<String> javaLines = Iterables.filter(fileLines,new Predicate<String>() {
                public boolean apply(String s) {
                    return !s.matches("^[\\s]*$");
                }
            });
            return Iterables.size(javaLines);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        if(args.length < 1){
            System.out.println("参数错误请输入如下格式:command [dirname] [encoding]encoding非必须");
            System.exit(1);
        }
        File file = new File(args[0]);
        if(!file.isFile() || !Files.getFileExtension(file.getName()).equals("java")) {
            System.out.println("请输入java源码文件.");
            System.exit(1);
        }
        try {
            //检测文件编码后执行
            CountEffectiveLines countEffectiveLines =new CountEffectiveLines(args[0],
                    args.length==2?Charset.forName(args[1]):ParserEncoding.INSTANCE.detectorEncoding(file));
            System.out.println(countEffectiveLines.count());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        }
}
