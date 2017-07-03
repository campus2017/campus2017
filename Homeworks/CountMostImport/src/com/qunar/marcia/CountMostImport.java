package com.qunar.marcia;


import sun.reflect.generics.tree.Tree;

import java.util.*;
import java.io.*;

/**
 * Created by Marcia on 2017/7/1.
 * 三、根据指定项目目录下（可以认为是java源文件目录）中，
 * 统计被import最多的类，前十个是什么。（作业命名为：CountMostImport）
 */
public class CountMostImport {

    public static void main(String[] args) {
        //System.out.println("hello world");

        Scanner scan=new Scanner(System.in);
        System.out.println("请输入指定项目目录：");
        String filePath=scan.nextLine();
        File file=new File(filePath);
        HashMap<String, Integer> hashMap=new HashMap<String, Integer>();
        TraversalFile(file, hashMap);//遍历file文件，将类的名称和出现的次数存在hashMap中
        //hashMap中已经存好了各种类和类出现的次数
        DisPlayTop10(hashMap);//打印出现次数前10的类

    }

    private static void DisPlayTop10(HashMap<String, Integer> hashMap) {
        ArrayList<Integer> arrayList=new ArrayList<>();
        Set<String> keys=hashMap.keySet();
        if(keys!=null){
            for(String string: keys){
                arrayList.add(hashMap.get(string));
            }
        }
        Collections.sort(arrayList,Collections.reverseOrder());//value按降序放在arrayList中
        Set<String> set=new LinkedHashSet<String>();//把排名前10的存到Set中
        Set<String> keySets=hashMap.keySet();
        for(int i=0;i<10;i++){
            for(String key:keySets){
                if(hashMap.get(key)==arrayList.get(i)){
                    set.add(key);
                }
            }
        }
        Iterator<String> iterator=set.iterator();//打印排名前10的
        int countS=0;
        while(true){
            if(iterator.hasNext()){

               String keyS=iterator.next();
               int valueS=arrayList.get(countS);
               countS++;
               System.out.println(keyS+"的出现次数为："+valueS);
            }else{
                break;
            }
        }

    }

    public static void TraversalFile(File file, HashMap<String, Integer> hashMap){
        if(file.exists()){
            File[] files=file.listFiles();
            for(File filee:files){
                if(filee.isDirectory()){
                    TraversalFile(filee, hashMap);
                }else{
                    AddFile(filee, hashMap);//把目录下的文件添加到容器中，并记录相同类出现的次数
                }
            }
        }else{
            System.out.println("文件不存在");
        }
    }

    public static void AddFile(File file, HashMap<String, Integer> hashMap){
        try{
            BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
            while(true){
                String className=bufferedReader.readLine();
                if(className==null){
                    break;
                }
                if(className.startsWith("import")){
                    className=className.replace("import", "").replace(";", "");
                    Integer count=CountNum(className,hashMap);
                    hashMap.put(className,count);
                }

            }
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public static Integer CountNum(String className, HashMap<String, Integer> hashMap) {
        int count=0;
        if(hashMap.get(className)==null){
            count=1;
        }else{
            count=hashMap.get(className)+1;
        }
        return new Integer(count);
    }

}