package com.qunar.util;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;

/**
 * Created by yaoguoli on 2017/6/15.
 * Description:判断接收的是文件夹还是java文件，将其中所有的java文件放入list，进行计算
 */
public class GetFiles {
    static int fileCount = 0;
    File file;
    Multiset<String> set = HashMultiset.create();
    public Multiset<String> transfer(File file, Multiset<String> set) throws FileNotFoundException{
        this.file = file;
        this.set = set;
        QueryFile(file, set);
        return set;
    }

    public static void QueryFile(File file, Multiset<String> set) throws FileNotFoundException{
        if(file == null || !file.exists()){
            throw new FileNotFoundException(file + "文件不存在");
        }
        fileCount ++;
        if (file.isDirectory()){
            File[] files = file.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.getName().endsWith(".java") || pathname.getName().endsWith(".jsp")||pathname.isDirectory();
                }
            });
            for(File query_file:files){
                QueryFile(query_file,set);
            }
        }else{
            set = new ImportClassCount().compute(file,set);
        }
    }


}
