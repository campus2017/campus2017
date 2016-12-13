package com.youthlin.qunar.fresh;

import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.TreeMultiset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * Created by youthlin.chen on 2016-11-11 011.
 * 三、根据指定项目目录下（可以认为是 java 源文件目录）中，统计被 import 最多的类，前十个是什么。（作
 * 业命名为：CountMostImport）
 */
public class CountMostImport {
    private static final Logger log = LoggerFactory.getLogger(CountMostImport.class);
    private static final int defaultShowCount = 10;
    private Multiset<String> result = TreeMultiset.create();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 使用 nextLine 而不是 next 因为文件路径可能有空格
        String dirName = in.nextLine();
        File dir = new File(dirName);
        if (!dir.isDirectory()) {
            log.warn("Input must be a folder name:{}", dir.getAbsolutePath());
            throw new IllegalArgumentException("Input must be a folder name:" + dir.getAbsolutePath());
        }
        CountMostImport countMostImport = new CountMostImport();
        countMostImport.processFile(dir);
        ImmutableMultiset<String> sorted = countMostImport.sort();
        ImmutableSet<Multiset.Entry<String>> entries = sorted.entrySet();
        int count = 0;
        for (Multiset.Entry<String> entry : entries) {
            if (++count > defaultShowCount) {
                break;
            }
            System.out.println(entry.getElement() + ":" + entry.getCount());
        }
    }

    private static boolean lastWordIsClassName(String line) {
        if (line.contains(".")) {
            String clazzName = line.substring(line.lastIndexOf(".") + 1);
            if (clazzName.length() > 0 && Character.isUpperCase(clazzName.charAt(0))) {
                return true;
            }
        }
        return false;
    }

    private void processFile(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles(new FileFilter() {
                public boolean accept(File pathname) {
                    return pathname.isDirectory() || pathname.getName().endsWith(".java");//只处理目录和java文件
                }
            });
            if (files != null) {
                for (File f : files) {
                    processFile(f);//遍历子目录
                }
            }
        } else {
            readFile(file);
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void readFile(File file) {
        log.trace("read file {}", file.getAbsolutePath());
        try {
            Scanner in = new Scanner(file);
            String line;
            while (in.hasNextLine()) {
                line = in.nextLine().trim();
                if (line.startsWith("public") || line.startsWith("class")) {
                    break;
                }
                if (line.startsWith("import") && line.endsWith(";")) {
                    // 去掉import和分号
                    line = line.substring("import".length(), line.length() - 1/*;*/).trim();
                    if (line.startsWith("static ")) {
                        log.trace("static import: {}", line);
                        // import static
                        // bug:
                        // import static NoPackageClass.*;       // Not Allowed
                        // import static NoPackageClass.staticMethod; // Not Allowed
                        // import static java.*;                 // 找不到符号
                        // import static java.math.*;            // 找不到符号
                        // import static java.math.BigDecimal.*; // OK
                        // import static java.math.BigDecimal.valueOf;//OK
                        // import static java.lang.Math.PI;//OK
                        // 结尾只能是 .* 或 静态方法 或 静态常量
                        if (line.contains(".")) {// 一定会包含至少一个点
                            line = line.substring("static ".length(), line.lastIndexOf("."));
                            // java
                            // java.math
                            // java.math.BigDecimal
                            // java.lang.Math
                            // 假装所有命名都符合规范（此时按照命名规范判断：最后一个单词首字母大写就算）
                            if (lastWordIsClassName(line)) {
                                result.add(line);
                            }
                        }
                    } else {
                        log.trace("common import: {}", line);
                        //import com.*;                     //不计
                        //import com.TestClass;             //com.TestClass
                        //import com.TestClass.*;           //com.TestClass
                        //import com.TestClass.InnerClass;  //com.TestClass.InnerClass
                        //import com.TestClass.InnerStaticClass;
                        //import com.TestClass.InnerStaticClass.*;
                        //import com.TestClass.InnerStaticClass.*;
                        //结尾是.*就去掉，然后看最后一个单词首字母是否大写
                        //结尾不是.*直接算
                        if (line.endsWith(".*")) {
                            line = line.substring(0, line.length() - 2);
                            if (lastWordIsClassName(line)) {
                                result.add(line);
                            }
                        } else {
                            result.add(line);
                        }
                    }//if-else 是否static
                }//if import开头分号结尾
            }
        } catch (FileNotFoundException e) {
            log.debug("{File not found.}", e);//this should be not happened
        }
    }

    private ImmutableMultiset<String> sort() {
        return Multisets.copyHighestCountFirst(result);
    }
}
