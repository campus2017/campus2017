package com.sugarman.caculator;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created by SugarMan on 2017/1/19.
 */
public class CodeManager {

    final int FILE_QUEUE_SIZE = 10;// 阻塞队列大小
    final int SEARCH_THREADS = 10;// 关键字搜索线程个数

    private String language;
    private String quote;
    private BlockingQueue<File> queue = new ArrayBlockingQueue<File>(
            FILE_QUEUE_SIZE);

    public CodeManager(String language){
        setLanguage(language);
    }


    public void setLanguage(String language) {
        this.language = language;
        // 判断编程语言，设定引用头
        this.quote = "import";
    }

    public List<Map.Entry<String, Integer>> start(String directory,int top){
        // 基于ArrayBlockingQueue的阻塞队列
        CountDownLatch latch = new CountDownLatch(SEARCH_THREADS+1);
        Executor executor = Executors.newFixedThreadPool(20);
        // 只启动一个线程来搜索目录
        FileEnumerationTask enumerator = new FileEnumerationTask(latch,queue,
                new File(directory), new FilterInterface() {
            public boolean filter(File file) {
                return  file.getName().endsWith("."+language);

            }
        });
        new Thread(enumerator).start();
//        executor.execute(enumerator);
        // 启动100个线程用来在文件中搜索指定的关键字
        Map<String, Integer> map = new Hashtable<String, Integer>();
        for (int i = 1; i <= SEARCH_THREADS; i++){
            new Thread(new QuoteTask(queue,map,latch)).start();;
//            executor.execute(new QuoteTask(queue,map,latch));
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Map.Entry<String, Integer>> result = new ArrayList<Map.Entry<String, Integer>>();
        List<Map.Entry<String, Integer>> list= sortHashMap(map);
        // 判断是否超数目
        if (top>list.size()){
            top = list.size();
            System.out.println("超过引用类型的数量,显示前"+top+"排行");
        }
        for (int i=0;i<top;++i){
            result.add(list.get(i));
        }
        return result;
    }

    public List<Map.Entry<String, Integer>> getQuote(String dir,int top){
        List<Map.Entry<String, Integer>> result = new ArrayList<Map.Entry<String, Integer>>();
        try {
            List<Map.Entry<String, Integer>> list= sortHashMap(getQuoteMap(dir));
            // 判断是否超数目
            if (top>list.size()){
                top = list.size();
                System.out.println("超过引用类型的数量,显示前"+top+"排行");
            }
            for (int i=0;i<top;++i){
                result.add(list.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取引用信息
     * @param dir
     * @return
     * @throws IOException
     */
    private Map<String, Integer> getQuoteMap(String dir)throws IOException{
        if(dir == null)
            return null;
        Map<String, Integer> map = new HashMap<String, Integer>();
        List<File> files = new ArrayList<File>();
        // 文件夹查找
        getFiles(dir,files);
        // 文件按行读取
        String line = "";
        for (File file: files) {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while((line = br.readLine()) != null){
                // 可能出现一行有多个引用信息
                if(line.startsWith(quote)){
                    line = line.replaceAll("\\s", "").substring(6);
                    if(!map.containsKey(line) ){
                        map.put(line, 1);
                    } else {
                        map.put(line, map.get(line)+1);
                    }
                }
            }
        }
        return map;
    }

    /**
     * 对HashMap进行value排序
     * @param map
     * @return
     */
    public static List<Map.Entry<String, Integer>> sortHashMap(Map<String, Integer> map){
        List<Map.Entry<String, Integer>> infoIds =
                new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(infoIds, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                //return (o2.getValue() - o1.getValue());
                return (o2.getValue() - o1.getValue());
            }
        });
        return infoIds;
    }

    /**
     * 根据路径获取对应编程语言路径集合
     * @param path 文件夹路径
     * @param list 获取路径集合
     */
    public void getFiles(String path,List<File> list){
        File file = new File(path);
        if(file.exists()){
            File[] files = file.listFiles();
            if(files.length > 0){
                for(File f : files){
                    if(f.isDirectory()){
                        getFiles(f.getAbsolutePath(),list);
                    } else {
                        if (f.getName().endsWith("."+language))
                             list.add(f);
                    }
                }
            }
        }
    }

}
