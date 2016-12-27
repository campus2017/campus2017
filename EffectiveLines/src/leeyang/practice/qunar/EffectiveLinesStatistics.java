package leeyang.practice.qunar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;



/**
 * 统计一个java文件的有效行
 *  1.有效行不包括空行
 *  2.不考虑代码间有多行注释的情况
 */

public class EffectiveLinesStatistics {

    //总有效代码行数
    public static int effectiveLines = 0;
    //统计的Java文件数
    public static int javaFileCount = 0;

    /**
     *
     * 统计Java源文件数和有效行数
     */

    public static void statistics(File inFile)
            throws IOException, NullPointerException {

        File[] files = inFile.listFiles(new JavaFileFilter());
        if (files == null) {
            throw new NullPointerException("输入文件错误");
        }
        for (File file : files) {
            if (file.isFile()) {
                int line = 0;
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        new FileInputStream(file),"utf-8"));
                String tmp;
                while ((tmp = br.readLine()) != null) {
                    tmp = tmp.replaceAll("\\s", "");
                    if (!("".equals(tmp)
                            || tmp.startsWith("//")
                            || tmp.startsWith("/*")
                            || tmp.startsWith("/**")
                            || tmp.startsWith("*")
                            )) {
                        line ++;
                    } //统计有效行数
                }
                br.close();
                System.out.println(file.getName() + "\t" + line); //每一个文件有效行数
                javaFileCount ++; //有效java文件的统计
                effectiveLines += line; //总有效行的统计

            } else if (file.isDirectory()) {
                statistics(file);
            }
        }
    }
}
