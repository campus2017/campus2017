package com.qunar.wireless.mlx;

import org.junit.Test;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by mlx on 2016-12-30.
 */
public class CountMostImportTest {


    @Test
    public void findFiles() throws Exception {
        String path="../EffectiveLines";
        List<File> files = CountMostImport.findFiles(path);
        System.out.println("=======findFiles结果=========");
        for(File f:files){
            System.out.println(f.getName());
        }
    }

    @Test
    public void findJavaFiles() throws Exception {
        String path="../EffectiveLines";
        List<File> files = CountMostImport.findJavaFiles(path);
        System.out.println("=======JavaFiles结果=========");
        for(File f:files){
            System.out.println(f.getName());
        }
    }

    @Test
    public void importClass() throws Exception {
        String[] lines = {
                "import java.io.File;",
                "       import java.io.*;",
                " import java.io.File;import java.io.*; ;;",
                "package com.qunar.wireless.mlx;"
        };

        List<String> r;
        for(String s:lines){
            r = CountMostImport.findImportClassInLine(s);
            System.out.println("=========找到的导入类===========");
            showList(r);
        }
    }

    @Test
    public void countImport() throws Exception {
        String javasrc = "./src/test/javaCases/javasrc01.java";
        File file = new File(javasrc);
        Map<String,Integer> map = CountMostImport.countImportClassInFile(file);
        System.out.println("=========从单个文件中找到的导入类===========");
        showMap(map);
    }

    @Test
    public void countImportClassInPath() throws Exception {
        String path="../EffectiveLines";
        Map<String,Integer> mp = CountMostImport.countImportClassInPath(path);
        System.out.println("=========从目录中找到的导入类===========");
        showMap(mp);
    }

    @Test
    public void topKImportClass() throws Exception {
        int k = 4;
        String path="../EffectiveLines";
        List<Map.Entry<String,Integer>> r = CountMostImport.topKImportClass(path,k);

        System.out.printf("======被import最多的是%d个类是=====\n",k);
        for(Map.Entry<String, Integer> ic: r){
            System.out.println(ic.getKey()+":"+ic.getValue());
        }

    }


    public void showList(List<?> list){
        for(Object o:list){
            System.out.println(o);
        }
    }

    public void showMap(Map<?,?> map) {
        Iterator<? extends Map.Entry<?, ?>> entries = map.entrySet().iterator();
        while (entries.hasNext()){
            Map.Entry<?,?> entry = entries.next();
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
    }

}
