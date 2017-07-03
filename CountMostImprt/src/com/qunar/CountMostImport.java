package com.qunar;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;


public class CountMostImport {

    private String dir;
    private HashMap<String,Integer> importRecords;

    public CountMostImport(){
        this.dir= "E:\\java_project\\CountMostImport\\src\\com\\qunar";
        importRecords = new HashMap<String, Integer>();
    }
    
    public void getTopKParams(int k){
    		staticsAllFiles(new File(dir));
    		ArrayList<MostImportClass> mostImportClassList = changeHashMapToList();
        Collections.sort(mostImportClassList, new SortByValue());
        Integer i = 0;
        for (MostImportClass mostImportClass : mostImportClassList) {
            if(i < k ) {
                System.out.println(mostImportClass.getImportName() + " / " + mostImportClass.getValue());
            }
            i++;
        }
        System.out.println();
    }


    private void staticsAllFiles(File file){
        if(!file.isDirectory()){
            processFile(file);
        }else{
            File[] files = file.listFiles();
            for(File f:files){
                staticsAllFiles(f);
            }
        }
    }


    private void processFile(File file){
        BufferedReader reader=null;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        String line = null;
        try {
            while((line=reader.readLine())!=null){
                line = line.trim();
                if(line.startsWith("public")||line.startsWith("class")){
                    break;
                } if(line.startsWith("import")) {
                    putParamsToHashMap(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void putParamsToHashMap(String line) {
        String className = line.substring(6, line.length() - 1).trim();
        Integer value = importRecords.get(className);
        if (value == null) {
            importRecords.put(className, 1);
        } else {
            importRecords.put(className, 1 + value);
        }
    }


    private ArrayList<MostImportClass> changeHashMapToList(){
        ArrayList<MostImportClass> list = new ArrayList<MostImportClass>();
        for(Map.Entry item: importRecords.entrySet()){
            String key = (String) item.getKey();
            int value = (Integer)item.getValue();
            list.add(new MostImportClass(key,value));
        }
        return list;
    }

    public static void main(String[] args)
    {
        CountMostImport test = new CountMostImport();
        test.getTopKParams(10);
    }

}

