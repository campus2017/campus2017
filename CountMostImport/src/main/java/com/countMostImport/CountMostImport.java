package com.countMostImport;

import java.io.*;
import com.google.common.collect.*;

/**
 * Created by canda on 6/29/17.
 * 统计导入类频率
 * 运行test/java/testCountMostImport.java测试
 */


public class CountMostImport {
    public String dir; //输入文件或目录
    public Multiset<String> importClassMap; //存储词频

    /**
     *@Description:构造方法
     *@Params:[dir]
     *@Return:
     *@Date:
     */
    public CountMostImport(String dir) throws FileNotFoundException {
        this.dir = dir;
        this.importClassMap = HashMultiset.create();
        this.processDirectory(new File(dir)); //统计词频
        importClassMap = Multisets.copyHighestCountFirst(importClassMap); //按频率将序排列
    }

    /**
     *@Description:目录递归处理
     *@Params:[file]
     *@Return:void
     *@Date:4:08 AM 6/29/17
     */
    public void processDirectory(File file) throws FileNotFoundException {
        if(file == null || !file.exists()){
            throw new FileNotFoundException("file can not be found!");
        }
        if (file.isDirectory()){
            File[] filesList = file.listFiles();
            for (File f : filesList){
                processDirectory(f);
            }
        }else{
            countOneFileImport(file);
        }
    }

    /**
     *@Description:统计单个java文件导入类频率
     *@Params:[file]
     *@Return:void
     *@Date:4:08 AM 6/29/17
     */
    public void countOneFileImport(File file){
        BufferedReader bReader =null;
        try {
            bReader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        String line = null;
        try {
            while((line=bReader.readLine())!=null){
                line=line.trim();
                if (line.startsWith("public")|line.startsWith("class")){ //循环查找到public或class退出
                    break;
                }
                if (line.startsWith("import")){ //查找import关键词
                    String className = line.substring(6, line.length()-1).trim();
                    importClassMap.add(className);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bReader.close();
            } catch (IOException e) {
                throw new RuntimeException("close BufferedReader failed.");
            }
        }
    }

}
