package edu.zhangzl;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by Administrator on 2017-01-26.
 */
public class CountClass_Demo {
   private static File file = null;
   //set用来存储类名
   private static HashMultiset<String> set;
    public static void main(String[] args) {
        set = HashMultiset.create();
        file = getPath();
        while(!file.isDirectory()){
            System.out.println("路径文件不是目录！请重新确认输入的路径");
            file = getPath();
        }
        //遍历给定文件
       interFiles(file.listFiles());
        //获取set中的元素进行处理
       Set<String> results = set.elementSet();
       //将result中的元素取出，去除重复元素，进行统计
        Set<String> get_num = new HashSet<String>();
        String str = null;
        for(String num : set){
           str = num+":"+set.count(num);
           if(!get_num.contains(str)) {
               get_num.add(str);
           }
       }
        //创建比较器，使用字符串出现次数进行比较
        Ordering<String> order = new Ordering<String>() {
            @Override
            public int compare(String left, String right) {
                int num_left = Integer.parseInt(left.substring(left.indexOf(":")+1));
                int num_right = Integer.parseInt(right.substring(right.indexOf(":")+1));
                return Ints.compare(num_left,num_right);
            }
        };

        List <String> list = order.greatestOf(get_num,10);
        for(String s:list){
            System.out.println(s);
        }


    }
//该方法读取文件，统计类出现的次数
    private static void getClassNumber(File file){
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String line = null;
            while((line = br.readLine())!=null){
                line = line.trim();
                //System.out.println(line);
                if(line.startsWith("import")){
                    //区分静态导包
                    String class_name = null;
                    if(line.contains("static")){
                        class_name = line.substring(line.indexOf("static")+7,line.length()-1);
                        set.add(line.substring(line.indexOf("static")+7,line.length()-1));
                    }else{
                        class_name = line.substring(line.indexOf(" ")+1,line.length()-1);
                        set.add(line.substring(line.indexOf(" ")+1,line.length()-1));
                    }
                }
                //该处可优化，当读取到的字符串不再包含import时直接跳出
               else{
                    if(!line.trim().equals("")&&!line.trim().startsWith("package")){
                        break;
                    }
                }
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                fr.close();
                br.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

/*
   遍历指定目录
   如果file是文件则处理
   如果文件是目录则继续深度遍历
 */
    private static void interFiles(File [] files){
        for (File file: files) {
            if(file.isDirectory()){
               // System.out.println("文件夹"+file.getName());
                interFiles(file.listFiles());
            }else{
               // System.out.println("文件"+file.getName());
                getClassNumber(file);
            }
        }
    }

//指定目录路径
    private static File getPath(){
        File file = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入目录路径：");
        String path = sc.nextLine().trim();
        file = new File(path);
        System.out.println(file);
        while (!file.exists()){
            System.out.println("目录不存在，请重新确认输入的路径值：");
            path = sc.nextLine().trim();
            file = new File(path);
        }
        return file;
    }
}
