package com.campus.homework;

import java.io.*;
import java.util.*;


/**
 * Created by Administrator on 2017/7/1.
 */
public class CountMostImport {
    private HashMap<String, Integer> recordsMap;

    public CountMostImport() {
        this.recordsMap = new HashMap<String, Integer>();
    }

    public void printTenMostImport(String filePth) {
        dfsFile(new File(filePth));
        final Map map = sortMapByValue(recordsMap);
        Iterator it = map.keySet().iterator();
        for (int i = 0; i < 10; i++) {
            if(it.hasNext())
                System.out.println(it.next());
            else
                break;
        }
    }
    public void dfsFile(File file){
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for (File f:files) {
                dfsFile(f);
            }
        }
        else{
            try {
                processFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void processFile(File file) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = null;
        while((line = br.readLine()) != null){
            line = line.trim();
            if(line.startsWith("public")||line.startsWith("class")){
                break;
            }
            if(line.startsWith("import")){
                String className = line.substring(6, line.length()-1).trim();
                Integer value = recordsMap.get(className);
                if(value==null){
                    recordsMap.put(className, 1);
                }else{
                    recordsMap.put(className, value+1);
                }
            }
        }
    }
    public static Map sortMapByValue(Map map){
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator(){
            public int compare(Object o1, Object o2) {
                return -((Comparable) ((Map.Entry)o1).getValue())
                        .compareTo(((Map.Entry)o2).getValue());  //降序排列
            }
        });
        Map result = new LinkedHashMap();
        for (Object o : list) {
            result.put(((Map.Entry) o).getKey(),((Map.Entry) o).getValue());
        }
        return result;
    }

}
