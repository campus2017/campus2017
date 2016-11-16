package com.xiaohan.main;

import com.xiaohan.tool.ImportCounter;

import java.io.File;
import java.util.*;

/**
 * Created by dell on 2016/11/16.
 */
public class Main {
    public static void main(String[] args ) {
        if (args == null || args.length < 1) {
            System.out.println("error: no arguments!");
            showUsage();
            return;
        }
        File dir = new File(args[0]);
        if(!dir.isDirectory()) {System.out.println("the target is not a directory!"); return;}
        ImportCounter importCounter = new ImportCounter();
        List<ImportCounter.ImpNode> list=importCounter.countMostImportant(dir,10);
        for(int i=0;i<list.size();i++){
            System.out.println("class: "+list.get(i).name+" Ref Times:"+list.get(i).count);
        }
    }
    public static void showUsage(){
        System.out.println("usage: toolName directoryPathName");
    }
}
