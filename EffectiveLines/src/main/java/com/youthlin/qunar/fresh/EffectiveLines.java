package com.youthlin.qunar.fresh;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by youthlin.chen on 2016-11-10 010.
 * 一、统计一个 Java 文件的有效行数。（作业命名：EffectiveLines）
 * 1、有效不包括空行
 * 2、不考虑代码见有多行注释的情况
 */
public class EffectiveLines {
    private static final Logger log = LoggerFactory.getLogger(EffectiveLines.class);   // slf4j 日志

    private File file;

    private EffectiveLines(File file) {
        this.file = file;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String fileName = in.nextLine();
        File file = new File(fileName);
        if (!file.exists() || !file.isFile()) {
            log.warn("Please input a filename.(your file = {})", file.getAbsoluteFile());
            System.exit(1);
        }
        System.out.println(new EffectiveLines(file).getEffectiveLines());
    }

    private int getEffectiveLines() {
        // * 1、有效不包括空行                  空行不计
        // * 2、不考虑代码有多行注释的情况      没考虑多行注释，认为空行不算有效，其他行都算有效
        return getNotBlankLinesCount();
    }

    private int getNotBlankLinesCount() {
        int count = 0;
        try {
            Scanner in = new Scanner(file);
            String line;
            while (in.hasNextLine()) {
                line = in.nextLine();
                if (!Strings.isNullOrEmpty(line)) {
                    count++;
                }
            }
        } catch (FileNotFoundException e) {
            log.warn("File not exists!({})", file.getAbsoluteFile(), e);
        }
        return count;
    }
}
