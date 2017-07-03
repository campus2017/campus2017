package com.qunar.campus2017.effectiveLines;

import java.io.*;

/**
 * Created by chunming.xcm on 2017/1/5.
 */
public class EffectiveLines {
    private final String regexSpace = "\\s*";
    private final String regexSingleLine = "\\/\\/[^\\n]*";
    private final String regexMultiLine = "\\/\\*([^\\*^\\/]*|[\\*^\\/*]*|[^\\**\\/]*)*\\*\\/";
    private final String regexJava = ".*\\.java";

    /**
     * 有效行判断
     * @param line
     * @return
     */
    private boolean legalLine(String line){
        String l = line.trim();
        return !(l.matches(regexSpace) || l.matches(regexSingleLine) || l.matches(regexMultiLine));
    }

    /**
     * 统计一个Java文件的有效行数
     * @param filePath
     * @return
     */
    public int countLines(String filePath) {
        int result = 0;
        if(filePath.matches(regexJava)) {
            File file = new File(filePath);
            BufferedReader bufferedReader = null;
            if(file.exists()) {
                try {
                    bufferedReader = new BufferedReader(new FileReader(file));
                    String line;
                    try {
                        line = bufferedReader.readLine();
                        while(line != null) {
                            if(legalLine(line)) {
                                result++;
                            }
                            line = bufferedReader.readLine();
                        }
                    } catch(IOException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return result;
    }
}
