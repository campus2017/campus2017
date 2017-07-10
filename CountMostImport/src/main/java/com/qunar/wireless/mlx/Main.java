package com.qunar.wireless.mlx;

import java.util.List;
import java.util.Map;

/**
 * Created by mlx on 2016-12-30.
 */
public class Main {
    public static void main(String[] args) {

        if(args.length>1){
            System.out.println("参数太多了!");
        }else if(args.length<1){
            System.out.println("请提供java源代码的目录!");
        }else{
            List<Map.Entry<String,Integer>> r = CountMostImport.topKImportClass(args[0],10);
            System.out.printf("======被import最多的是%d个类是=====\n",10);
            for(Map.Entry<String, Integer> ic: r){
                System.out.println(ic.getKey()+":"+ic.getValue());
            }
        }

    }
}
