package leeyang.practice.qunar;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

import leeyang.practice.qunar.JavaFileFilter;

/**
 *  映射并统计import的java类 className-->count
 *
 */


public class MapClassFromFile {

    public static Map<String, Integer> mapClass = new HashMap<>();

    public static void readFile(File inFile)
            throws IOException, NullPointerException{

        File[] files = inFile.listFiles(new JavaFileFilter());

        if (null == files) {
            throw new NullPointerException("输入文件错误");
        }
        String charsetName = "utf-8";
        String identification = "import";
        for (File file : files) {
            if (file.isFile()) {
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        new FileInputStream(file), charsetName));
                String line = "";
                while ((line = br.readLine()) != null) {
                    //exact java.io.className
                    if (line.startsWith(identification)) {
                        line = line.substring(identification.length()).trim();
                        //remove ";"
                        if (line.contains(";")) {
                            line = line.substring(0, line.length() - 1);
                        }
                        // ignore ".*"
                        String className = "";
                        if (line.contains(".") && (!line.endsWith(".*"))) {
                            className = line.substring(line.lastIndexOf(".") + 1);
                        }
                        if (!className.equals("")) {
                            if (mapClass.get(className) != null) {
                                mapClass.put(className, mapClass.get(className) + 1);
                            } else {
                                mapClass.put(className, 1);
                            }
                        }
                    }
                }
            } else if (file.isDirectory()) {
                readFile(file);
            }
        }
    }
}
