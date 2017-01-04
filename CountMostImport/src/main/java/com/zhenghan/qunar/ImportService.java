package com.zhenghan.qunar;
import com.google.common.base.*;
import com.google.common.collect.Lists;
import com.google.common.io.Files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Author: 郑含
 * Date: 2016/12/20
 * Time: 19:06
 */
public class ImportService {
    protected static void doCountImportClass(Trie root,File file,String encoding) throws FileNotFoundException {
        if(!file.exists() || !file.isDirectory()){
            throw new FileNotFoundException();
        }
        File[] files = file.listFiles();
        for(File temp:files){
            if(temp.isFile() && Files.getFileExtension(temp.getName()).equals("java")) {
                try {
                    importSingleFile(root, temp, encoding);
                } catch (IOException e) {
                    //不做处理
                    e.printStackTrace();
                }
            }else if(temp.isDirectory()){
                doCountImportClass(root,temp,encoding);
            }
        }
    }
    public static List<String> countImportClass(String dir,String encoding) throws FileNotFoundException {
        Trie root =new Trie();
        doCountImportClass(root,new File(dir),encoding);
        List<Map.Entry<String,Integer>> topTen = root.topTen();
        System.out.println(root.topTen());
        return Lists.transform(root.topTen(), new Function<Map.Entry<String, Integer>, String>() {
            public String apply(Map.Entry<String, Integer> stringIntegerEntry) {
                return stringIntegerEntry.getKey();
            }
        });
    }
    private static void importSingleFile(Trie root, File temp,String encoding) throws IOException {
        List<String> lines = Files.readLines(temp, Charset.forName(encoding));
        for(String line:lines){
            if(!Strings.isNullOrEmpty(line) && line.startsWith("import ")){
                root.putPackage(line.replace("import","").replace(";","").trim());
            }
        }
    }

}
