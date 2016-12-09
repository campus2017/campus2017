package com.youthlin.qunar.fresh;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by youthlin.chen on 2016-11-11 011.
 * 三、根据指定项目目录下（可以认为是 java 源文件目录）中，统计被 import 最多的类，前十个是什么。（作
 * 业命名为：CountMostImport）
 */
public class CountMostImport {
    private static final Logger log = LoggerFactory.getLogger(CountMostImport.class);
    private HashMap<String, Integer> clazzNameCountMap = new HashMap<String, Integer>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 使用 nextLine 而不是 next 因为文件路径可能有空格
        String dirName = in.nextLine();
        File dir = new File(dirName);
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException("Input must be a folder name:" + dir.getAbsolutePath());
        }

        CountMostImport countMostImport = new CountMostImport();
        countMostImport.processFile(dir);
        Pair[] pairsArr = countMostImport.getMostCountClass();
        int count = 0;
        for (Pair pair : pairsArr) {
            if (++count > 10) {
                break;
            }
            System.out.println(pair);
        }
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

    private void readFile(File file) {
        log.trace("read file {}", file.getAbsolutePath());
        try {
            Scanner in = new Scanner(file);
            String line;
            while (in.hasNextLine()) {
                line = in.nextLine();
                if (line.startsWith("public") || line.startsWith("class")) {
                    break;
                }
                if (line.startsWith("import")) {
                    // 暂时不考虑import java.util.*; 这种星号结尾的
                    line = line.substring("import".length(), line.length() - 1/*;*/).trim();
                    if (line.startsWith("static ")) {
                        // import static
                        // bug:
                        // import static java.math.*;
                        // import static java.math.BigDecimal.*;
                        // import static java.math.BigDecimal.valueOf;
                        line = line.substring("static ".length(), line.length()).trim();
                    }
                    Integer count = clazzNameCountMap.get(line);
                    if (count != null) {
                        clazzNameCountMap.put(line, count + 1);
                    } else {
                        clazzNameCountMap.put(line, 1);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            log.debug("{File not found.}", e);//this should be not happened
        }
    }

    // 总数并不是很大，可以先排序再返回不需要用最小堆等数据结构实现
    private Pair[] getMostCountClass() {
        List<Pair> pairs = new ArrayList<Pair>();
        Set<String> classNames = clazzNameCountMap.keySet();
        for (String className : classNames) {
            Pair pair = new Pair(className, clazzNameCountMap.get(className));
            pairs.add(pair);
        }
        // 本来是用 PriorityQueue 的，但是发现 PriorityQueue 的迭代器<b>不保证顺序</b>，
        // 反正都要 Arrays.sort 干脆直接 List 不用优先级队列
        Pair[] pairsArr = new Pair[0];
        pairsArr = pairs.toArray(pairsArr);
        Arrays.sort(pairsArr);
        return pairsArr;
    }

    private class Pair implements Comparable<Pair> {
        String clazzName;
        int count;

        Pair(String clazzName, int count) {
            this.clazzName = clazzName;
            this.count = count;
        }

        public int compareTo(Pair o) {
            return o.count - count;
        }

        @Override
        public String toString() {
            return clazzName + ':' + count;
        }
    }
}
