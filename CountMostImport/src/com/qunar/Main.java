package com.qunar;

import java.io.*;
import java.util.*;

/**
 *
 * Created by NULL on 2016/12/23.
 */
public class Main extends BaseObject {


    public static void main(String[] args){

        // 统计
        traverseFolder("TestDir");
        // 排序并打印结果
        sortAndPrintResult();


    }

    private static Map<String,Integer> map = new HashMap<>();

    /**
     * 遍历文件
     * @param filePath
     */
    private static void traverseFolder(String filePath){

        File file = new File(filePath);
        log("当前遍历：" + file.getAbsolutePath());
        if(file.exists()){
            File[] files = file.listFiles();
            // 文件夹不为空
            if(files.length != 0){
                for(File f : files){
                    if(f.isDirectory()){
                        traverseFolder(f.getAbsolutePath());
                    }else if(f.getName().substring(f.getName().lastIndexOf(".")+1).equals("java")) {
                        readFile(f);
                    }
                }
            }
        } else {
            throwError(1,"文件路径不存在");
            return;
        }

    }

    /**
     * 读取文件
     * @param file
     */
    private static void readFile(File file){

        log("处理文件：" + file.getPath());

        if(file == null || !file.exists()){
            throwError(BaseObject.ERROR_CODE_FILE_NOR_FOUND,"文件不存在");
            return;
        }

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader reader = null;
        try{
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            reader = new BufferedReader(isr);
            String line = reader.readLine();
            while(line != null){
                if(line.trim().startsWith("import"))
                    countMostImportClass(line);
                line = reader.readLine();
            }
        } catch(IOException e){
            e.printStackTrace();
        } finally {
            try{
                if(fis != null) fis.close();
                if(isr != null) isr.close();
                if(reader != null) reader.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }


    /**
     * import单句处理
     * exp: import java.util.HashMap;
     *      import java.io.*;
     *
     * @param line
     */
    private static void countMostImportClass(String line) {

        log("处理语句：" + line);

        if(Main.map == null) map = new HashMap<>();

        line = line.trim();
        String fullClassName = line.split(" ")[1]; // java.util.HashMap;
        String className = fullClassName.substring(fullClassName.lastIndexOf(".")+1,fullClassName.length()-1);
        // 不考虑 * 的情况
        if(!"*".equals(className)){
            if(map.containsKey(className)){
                Integer i = map.get(className);
                map.replace(className,++i);
            }else{
                map.put(className,new Integer(1));
            }
        }

    }

    /**
     * 对map排序并打印结果
     */
    private static void sortAndPrintResult() {

        List<Map.Entry<String, Integer>> list=new ArrayList<>();
        list.addAll(map.entrySet());
        Collections.sort(list,(m1, m2) ->m2.getValue()-m1.getValue());

        int size = list.size() > 10 ? 10 : list.size();
        log("统计结果：前 " + size + " 个被引用次数最多的类");
        for(int i=0;i<size;i++){
            System.out.println(list.get(i).getKey() + "  被引用  " + list.get(i).getValue() + "次");
        }

    }


}
