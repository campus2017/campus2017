package task3;

import bean.ImportCount;
import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gzx on 16-12-31.
 */
public class CountMostImport {
    // 哈希表，存放的键为类名，值为出现次数
    private HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
    // 模式串，不考虑以*结尾的导入
    private String pat = "\\s*import\\s+([a-zA-Z.]+)\\s*;";

    // 优先队列，用于建立小根堆
    private PriorityQueue<ImportCount> pq = new PriorityQueue<ImportCount>();
    /**
     * 计算一个java文件的内容里面import的类，并在hashMap中加一
     * @param fileContent
     */
    private void calculateImport(String fileContent){
        Pattern pattern = Pattern.compile(pat);
        Matcher matcher = pattern.matcher(fileContent);
        // 遍历所有匹配的子串，并把匹配的子串的第一个组抽取出来
        while(matcher.find()){
            String className = matcher.group(1);
            //System.out.println(className);
            Integer value = hashMap.get(className);
            if(value == null){
                value = 0;
            }
            hashMap.put(className, ++value);
        }
    }

    /**
     * 深度搜索整个目录，计算所有java文件import的类出现的次数，建立hashMap
     * @param dir
     */
    private void buildHashMap(File dir){
        // System.out.println(dir.getAbsolutePath());
        // 是目录，则递归搜索
        if(dir.isDirectory()){
            File[] allFiles = dir.listFiles();
            for(int i = 0; i < allFiles.length; i++){
               buildHashMap(allFiles[i]);
            }
        }
        else if(dir.getName().endsWith(".java")){
            try {
                // 读取文件的内容，并计算，默认utf-8编码
                String fileContent = Files.toString(dir, Charsets.UTF_8);
                calculateImport(fileContent);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 利用已经建立好的hashMap来建立一个优先队列，当队列的元素个数不超过maxSize时，
     * 必须把队头弹出。这里本质上是维护一个大小为maxSize的小根堆。
     * @param maxSize 队列的最大元素个数
     */
    private void buildPriorityQueue(int maxSize){
        Set<Map.Entry<String, Integer>> entrySet = hashMap.entrySet();
        for(Map.Entry<String, Integer> entry : entrySet){
            ImportCount importCount = new ImportCount();
            importCount.setClassName(entry.getKey());
            importCount.setCnt(entry.getValue());
            pq.offer(importCount);
            // 如果队列的个数超过允许的最大元素个数，则队头出队列，因为队头最小
            if(pq.size() > maxSize){
                pq.poll();
            }
        }
    }

    /**
     * 总的入口。
     * 先建立哈希表，然后利用哈希表建立小根堆（优先队列），最后将队列转化为从小到达排序的list返回。
     * @param dirName 含义java文件的根目录名
     * @param maxSize 获取导入最多的类，前maxSize个
     * @return 如果目录不存在，返回null
     */
    public List<ImportCount> getMostImport(String dirName, int maxSize){
        List<ImportCount> list = null;
        File dir = new File(dirName);
        if(!dir.exists()){
            return list;
        }
        buildHashMap(dir);
        buildPriorityQueue(maxSize);
        list = new ArrayList<ImportCount>();
        while(!pq.isEmpty()){
            list.add(pq.poll());
        }
        return list;
    }

    public static void main(String[] args){
        String dirName = "task3_data";
        int maxSize = 10;
        List<ImportCount> list = new CountMostImport().getMostImport(dirName, maxSize);
        if(list == null){
            System.out.println("list is null");
            return;
        }
        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }
    }
}
