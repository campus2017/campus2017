package com.qunar.QA;

/**
 * @Author Nicole
 * @Time 2017/7/2
 * @Description 统计被import的类
 */

import java.io.*;

import java.util.HashMap;

public class CountImportClasses {

        String dirPath;
        HashMap<String,Integer> importClassRecords;

        public CountImportClasses(String dirPath){
                        this.dirPath=dirPath;
                        importClassRecords=new HashMap<String,Integer>();
                        this.countImportClasses(new File(this.dirPath));
        }

        public void processFile(File file){
                        BufferedReader reader;
                        try{
                            reader=new BufferedReader(new FileReader(file));
                        }catch(FileNotFoundException e){
                            e.printStackTrace();
                            return;
                        }
                        String line=null;
                        try{
                            while((line=reader.readLine())!=null){
                                   line=line.trim();
                                    if(line.startsWith("import")){
                                        String className=line.substring(6,line.length()-1).trim();
                                        Integer value=importClassRecords.get(className);
                                        if(value==null){
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

        public void countImportClasses(File file){
                        if(!file.isDirectory()){
                            processFile(file);
                        }else{
                             File[] files=file.listFiles();
                            for(File tmpFile:files){
                                countImportClasses(tmpFile);
                            }
                        }
        }

}