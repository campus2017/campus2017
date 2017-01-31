package com.sugarman.caculator;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by SugarMan on 2017/1/19.
 */
public class Main {

    public static void main(String[] args){
        System.out.println("请输入查找文件夹路径：");
        Scanner in = new Scanner(System.in);
        String dir = in.nextLine();
        long startTime = System.currentTimeMillis();

        // 核心代码
        CodeManager cmn = new CodeManager("java");
        List<Map.Entry<String, Integer>> list = cmn.start(dir,10);

//        List<Map.Entry<String, Integer>> list = cmn.getQuote(dir,10);

        // 打印输出
        if (list == null || list.size() == 0){
            System.out.println("没有找到相关java类");
            return;
        }
        System.out.println("引用类排行Top");
        for (int i=0;i<list.size();++i){
            Map.Entry<String, Integer> entry = list.get(i);
            System.out.println("Top"+i+":"+entry.getKey() + "  " + entry.getValue());
        }
        in.close();
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
    }

}
