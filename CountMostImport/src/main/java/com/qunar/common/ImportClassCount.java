package com.qunar.common;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gcy0904 on 2017/1/20.
 */
public class ImportClassCount {
    String dirName;//文件路径
    HashMap<String, Integer> importClassRecords;    //记录Map
    public ImportClassCount(){}

    public ImportClassCount(String dir){
        this.dirName = dir;
        importClassRecords = new HashMap<String, Integer>();
        this.statisticsClazz(new File(this.dirName));
    }

    private int get(String clazzName){
        Integer value = importClassRecords.get(clazzName);
        if(value==null) return 0;
        return value;
    }
    private void processFile(File file){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return;
        }
        String line = null;
        try {
            while((line = reader.readLine()) != null){
                line = line.trim();
                if(line.startsWith("public")||line.startsWith("class")){
                    break;
                }
                if(line.startsWith("import")){
                    String className = line.substring(6, line.length()-1).trim();
                    Integer value = importClassRecords.get(className);
                    if(value==null){
                        importClassRecords.put(className, 1);
                    }else{
                        importClassRecords.put(className, value+1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void statisticsClazz(File file){
        if(!file.isDirectory()){
            //是文件就进行统计
            processFile(file);
        }else{
            //文件夹进行迭代
            File [] files = file.listFiles();
            for(File tmpFile: files){
                statisticsClazz(tmpFile);
            }
        }

    }

    //获得次数最多的一个类
    public ImportClassBean getMostImportClazzName(){
        int max = Integer.MIN_VALUE;
        String clazzName = null;
        for(Map.Entry item: this.importClassRecords.entrySet()){
            String key = (String) item.getKey();
            int value = (Integer)item.getValue();
            if(value>max){
                max = value;
                clazzName = key;
            }
        }
        return new ImportClassBean(clazzName,max);
    }

    //获得次数做多的10个类
    public List<ImportClassBean> getTopImportClassName(int num){
        List<ImportClassBean> list = new ArrayList<ImportClassBean>();
        for (int i = 0; i < num && i < this.importClassRecords.size(); i++) {
            ImportClassBean mostImportClazzName = this.getMostImportClazzName();
            list.add(mostImportClazzName);
            this.importClassRecords.remove(mostImportClazzName.getClassName());
        }
        return list;
    }

    public void getTopImportClassName2(){
        System.out.print(this.importClassRecords.toString());
        ImportClassBean mostImportClazzName = this.getMostImportClazzName();
        this.importClassRecords.remove(mostImportClazzName.getClassName());
        System.out.print(this.importClassRecords.toString());

    }
}
