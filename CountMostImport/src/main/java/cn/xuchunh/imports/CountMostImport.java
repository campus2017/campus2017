package cn.xuchunh.imports;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.regex.Pattern;

/**
 * Created on 2017/4/6.
 * <p>
 * 假设提供的 Java 文件是合法的，可编译通过的
 *
 * @author XCH
 */
public class CountMostImport {

    /**
     * @param path 文件夹路径
     * @param num  期望获取
     * @return 前 num 个被 import 的类，返回 List 的大小不超过 num，异常情况或无import类时返回一个空的 List
     */
    public static List<ClassNode> countMostImport(String path, int num) throws IOException {

        Preconditions.checkArgument(num > 0, "num 必须大于 0");

        File file = new File(path);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }

        Map<String, ClassNode> map = Maps.newHashMap();
        if (file.isFile()) {
            if (path.endsWith(".java")) {
                analyseJavaFile(file, map);
            }
        }

        Queue<File> queue = Lists.newLinkedList();
        queue.offer(file);
        while (!queue.isEmpty()) {
            File child = queue.poll();
            if (child.isDirectory()) {
                for (File f : child.listFiles()) {
                    queue.offer(f);
                }
            } else {
                analyseJavaFile(child, map);
            }
        }

        Ordering<ClassNode> ordering = new Ordering<ClassNode>() {
            @Override
            public int compare(ClassNode classNode, ClassNode t1) {
                return Ints.compare(classNode.getFrequency(), t1.getFrequency());
            }
        };

        return ordering.greatestOf(map.values(), num);
    }

    private static void analyseJavaFile(File file, Map<String, ClassNode> map) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String s;
            Splitter splitter = Splitter.on(" ").omitEmptyStrings();
            Pattern pattern = Pattern.compile("(public|abstract|class).+");
            while ((s = reader.readLine()) != null) {
                s = s.trim();
                if (!Strings.isNullOrEmpty(s)) {
                    if (s.startsWith("import")) {
                        Iterator<String> keyWords = splitter.split(s).iterator();
                        keyWords.next();
                        String keyWord = keyWords.next();
                        String className = "";
                        if ("static".equals(keyWord)) {
                            keyWord = keyWords.next();
                            className = keyWord.substring(0, keyWord.lastIndexOf("."));
                        } else {
                            if (keyWord.endsWith(".*")) {
                                continue;
                            } else {
                                className = keyWord.substring(0, keyWord.indexOf(";")).trim();
                            }
                        }

                        ClassNode node = map.get(className);
                        if (node == null) {
                            node = new ClassNode(className, 1);
                            map.put(className, node);
                        } else {
                            node.frequencyIncrement();
                        }
                    } else if (pattern.matcher(s).matches()){
                        break;
                    }
                }
            }
        }
    }
}
