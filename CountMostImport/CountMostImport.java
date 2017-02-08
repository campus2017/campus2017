package com.cn.edu.java;

import java.io.*;
import java.util.*;

/**
 * Created by ASUS on 2017/2/7.
 */
public class CountMostImport {
    String dirName;
    HashMap<String,Integer> importClassRecords;
    public CountMostImport(String dir){
        this.dirName = dir;
        importClassRecords = new HashMap<String,Integer>();
        this.statisticsClazz(new File(this.dirName));
    }
//    public int get(String clazzName){
//        Integer value = importClassRecords.get(clazzName);
//        if(value==null)
//            return 0;
//        return value;
//    }
    public void processFile(File file){
        BufferedReader reader;
        try {
             reader = new BufferedReader(new FileReader(file));
        } catch(FileNotFoundException e){
            e.printStackTrace();
            return;
        }
        String line = null;
        try{
            while((line = reader.readLine())!=null){
                line = line.trim();
                if(line.startsWith("public")||line.startsWith("class")){
                    break;
                }
                if(line.startsWith("import")){
                    String className = line.substring(6,line.length()-1).trim();
                    Integer value = importClassRecords.get(className);
                    if(value == null){
                        importClassRecords.put(className,1);
                    }else{
                        importClassRecords.put(className,value+1);
                    }
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void statisticsClazz(File file){
        if(!file.isDirectory()){
            processFile(file);
        }else{
            File[] files = file.listFiles();
            for(File tmpFile:files){
                statisticsClazz(tmpFile);
            }
        }
    }
    public String getMostImportClazzName(){
        int max = Integer.MIN_VALUE;
        String clazzName = null;
        for(Map.Entry item:this.importClassRecords.entrySet()){
            String key = (String)item.getKey();
            int value = (Integer)item.getValue();
            if(value>max){
                max = value;
                clazzName = key;
            }
        }
        return clazzName;
    }
    public static void main(String[] args){
        CountMostImport c = new CountMostImport("F:\\IDEAcode");

//        Iterator it = c.importClassRecords.keySet().iterator();
//        while(it.hasNext()){
//            String key = (String)it.next();
//            System.out.println("key:"+key);
//            System.out.println("value:"+c.importClassRecords.get(key));
//        }

        List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(c.importClassRecords.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        int i=0;
       for(Map.Entry<String,Integer> mapping:list){
           System.out.println(mapping.getKey()+":"+mapping.getValue());
           i++;
           if(i==10)
               break;
       }
    }
}
