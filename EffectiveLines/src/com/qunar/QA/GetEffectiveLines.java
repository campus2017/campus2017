package com.qunar.QA;

/**
 * @Author Nicole
 * @Time  2017/7/1
 * @Description 获取java文件的有效行数
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetEffectiveLines {


    public int getEffectiveLines(File file) throws FileNotFoundException {

        int effectiveLines = 0;

        if (file == null || !file.exists())
            throw new FileNotFoundException(file + "，文件不存在！");

        BufferedReader bufr = null;
        try {
            bufr = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(file + "，文件不存在！" + e);
        }

        String line = null;
        try {
            while((line = bufr.readLine()) != null) {

                if(line.matches(RegularExp.docNoteBegin)){
                    continue;
                }
                if(line.matches(RegularExp.docNoteEnd)){
                    continue;
                }
                if(line.matches(RegularExp.singleLineNote)) {
                    continue;
                }
                if(line.matches(RegularExp.blankLine)) {
                    continue;
                }

                 ++effectiveLines;
            }

        } catch (IOException e) {
            throw new RuntimeException("读取文件失败！" + e);
        } finally {
            try {
                bufr.close();
            } catch (IOException e) {
                throw new RuntimeException("关闭文件输入流失败！");
            }
        }

        return effectiveLines;

    }
}

