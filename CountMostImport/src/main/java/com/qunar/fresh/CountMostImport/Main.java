package com.qunar.fresh.CountMostImport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.min;

/**
 * Created by wupei on 2017/7/2.
 */
public class Main {
    private static Map<String, Integer> map = new HashMap<String, Integer>();
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        readDir(new File("."));

        ArrayList<Map.Entry> entries = new ArrayList<>(map.entrySet());
        entries.sort((a, b) -> Integer.compare((int)b.getValue(), (int)a.getValue()));

        System.out.println(entries.subList(0, min(10, entries.size())));
    }

    private static void readDir(File file) {
        if (file.isDirectory()) {
            for (File subFile : file.listFiles()) {
                readDir(subFile);
            }
        } else {
            if (file.getName().endsWith(".java")) {
                readFile(file);
            }
        }
    }

    private static void readFile(File file) {
        BufferedReader br = null;
        try {
            FileReader fr = new FileReader(file);
            br = new BufferedReader(fr);
            String str = br.readLine();

            while (str != null) {
                if (str.startsWith("import")) {
                    str = str.replaceAll("import\\s+|;", "");
                    Integer value = map.get(str);
                    if(value==null){
                        map.put(str, 1);
                    }else{
                        map.put(str, value+1);
                    }
                }
                str = br.readLine();
            }
        } catch (IOException e) {
            LOGGER.error("读文件异常, file:{}", file.getAbsoluteFile(), e);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                LOGGER.error("关闭文件异常, file:{}", file.getAbsoluteFile(), e);
            }
        }
    }

}
