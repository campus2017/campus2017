package com.sugarman.caculator;

import java.io.*;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

/**
 * Created by SugarMan on 2017/1/19.
 */
public class QuoteTask extends Task implements Task.TaskInterface{

    private static String language = "java";
    private static String quote = "import";
    private Map<String, Integer> map;

    public QuoteTask(BlockingQueue<File> queue,Map<String, Integer> map,CountDownLatch downLatch){
        super(queue, downLatch);
        this.map = map;
        this.taskInterface = this;
    }

    public void task(File file) {
        String line = "";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            while((line = br.readLine()) != null){
                // 可能出现一行有多个引用信息
                if(line.startsWith(quote)){
                    line = line.replaceAll("\\s", "").substring(6);
                    System.out.println(Thread.currentThread().getName() +" " + line);
                    if(!map.containsKey(line) ){
                        map.put(line, 1);
                    } else {
                        map.put(line, map.get(line)+1);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
