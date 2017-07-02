package com.qunar;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

public class CountMostImport {

    private String dir;
    private static HashMap<String,Integer> importRecords;
    private static ArrayList<MostImportClass> mostImportClassList;


    public CountMostImport(){
        this.dir= "/Users/wst/Desktop/CountMostImport/src/com/qunar";
        importRecords = new HashMap<String, Integer>();
        this.staticsAllFiles(new File(dir));
    }

    public static void getTopKParams(int k){
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


    private static ArrayList<MostImportClass> changeHashMapToList(){
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
        mostImportClassList =  changeHashMapToList();
        getTopKParams(10);
    }

}

