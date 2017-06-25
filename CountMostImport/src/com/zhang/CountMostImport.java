package com.zhang;

/**
 * Created by zhangpei on 2017/6/25.
 */
import java.util.*;
import java.util.regex.*;

import java.io.BufferedReader;
import java.io.BufferedReader;
import java.io.BufferedReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class CountMostImport {
    String dirName;
    HashMap<String, Integer> importClassRecords;

    public CountMostImport(String dir){
        this.dirName = dir;
        importClassRecords = new HashMap<String, Integer>();
        this.statisticsClass(new File(this.dirName));
    }

    public int get(String clazzName){
        Integer value = importClassRecords.get(clazzName);
        if(value==null) return 0;
        return value;
    }
    public void processFile(File file){
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

    public void statisticsClass(File file){
        if(!file.isDirectory()){
            processFile(file);
        }else{
            File [] files = file.listFiles();
            for(File tmpFile: files){
                statisticsClass(tmpFile);
            }
        }

    }

    public String getMost10ImportClass(){

        PriorityQueue<Entry<String,Integer>> pq=new
                PriorityQueue<Entry<String,Integer>>(new Comparator<Entry<String,Integer>>(){
            @Override
            public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {

                return e1.getValue()<e2.getValue()?1:e1.getValue()==e2.getValue()?0:-1;
            }

        });
        for(Entry<String,Integer> item: this.importClassRecords.entrySet()){
            pq.offer(item);
        }
        //	System.out.println(pq);
        StringBuilder result=new StringBuilder();
        for(int i=0;i<10;i++){
            Entry<String,Integer>  big=pq.poll();
            if(big==null)
                break;
            result.append(big.getKey());
            result.append(", ");
            result.append(big.getValue());
            result.append("\n");
        }
        return result.toString();
    }
    public static void main(String[] args) {
        String path="F:\\itelJ\\CountMostImport\\src\\com\\zhang\\CountMostImport.java";
        File directory = new File(path);
        System.out.println(directory.getAbsolutePath());
//		try {
//			System.out.println(directory.getCanonicalPath());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
        //	System.out.println(System.getProperties());
        CountMostImport cmi=new CountMostImport(path);
        System.out.println(cmi.getMost10ImportClass());
        System.out.println(cmi.importClassRecords);
    }



}

