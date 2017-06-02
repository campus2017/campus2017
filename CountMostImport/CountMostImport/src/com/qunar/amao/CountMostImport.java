package com.qunar.amao;

import java.io.*;
import java.util.*;

/**
 * Created by FGT on 2017/6/2.
 */
public class CountMostImport {
    private static Map<String,Integer> map = new HashMap<String,Integer>();  //统计import的类的数量
    private static ArrayList<File> list = new ArrayList<File>();    //所有文件集合
    private static List<Map.Entry<String, Integer>> entryList = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());

    /**
     * 根据根目录得到目录下文件及文件夹，并递归调用
     * @param files 根目录下文件及文件夹的集合
     */
    public static void GetAllFiles(File[] files) {
        for(int i=0;i<files.length;i++){
            if(!files[i].isDirectory()){
                list.add(files[i]);
            }else{
                GetAllFiles(files[i].listFiles());
            }
        }
    }

    /**
     * 统计文件内import类
     * @param file 统计文件
     */
    public static void CountSingleFileImport(File file) throws Exception{
        InputStream in = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String str = "";

        while((str=br.readLine()) != null){
            if(!str.startsWith("import")){
                continue;
            }else{
                String[] temp = str.split(";"); //统计一行多个import
                for(int i=0;i<temp.length;i++){
                    if(temp[i].startsWith("import")){
                        temp[i] = temp[i].replace("\\s*","");
                        temp[i] = temp[i].substring(6);
                        Integer value = map.get(temp[i]);
                        if(value==null){
                            map.put(temp[i],1);
                        }else{
                            map.put(temp[i],value+1);
                        }
                    }
                }//for
            }//else
        }//while
    }

    /**
     * 对HashMap按value排序
     */
    public static ArrayList<Map.Entry<String,Integer>> SortMap(Map map){
        List<Map.Entry<String, Integer>> entries = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> obj1 , Map.Entry<String, Integer> obj2) {
                return obj2.getValue() - obj1.getValue();
            }
        });
        return (ArrayList<Map.Entry<String, Integer>>) entries;
    }

    /**
     * 输出结果
     */
    public static void PrintResult(){
        int length;
        if(entryList.size()>=10){
            length = 10;//输出前十个
        }else{
            length = entryList.size();
        }
        for (Map.Entry<String, Integer> mapping : entryList) {
            length--;
            if(length<0){
                break;
            }
            System.out.println(mapping.getKey() + ":" + mapping.getValue());
        }
    }

    public static void main(String[] args) throws Exception{
        //源路径（即当前根目录路径下）获取所有文件集，适用于文件数量较少的程序
        File filesContent = new File("./");
        File[] files = filesContent.listFiles();
        GetAllFiles(files);

        //统计Import类的次数
        for(int i=0;i<list.size();i++){
            CountSingleFileImport(list.get(i));
        }

        //输出统计结果
        entryList = SortMap(map);
        //输出结果
        PrintResult();
    }
}
