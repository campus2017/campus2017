package com.homework.main;

import com.homework.tool.LineCounter;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class EffectiveLine {
    public String name;
    public static void main(String[] args) {
	// write your code here
        if(args==null || args.length<1 )
        System.out.println("Wrong Arguments! Usage: EffectiveLine filename");
        System.out.println("analysing file "+args[0]+" ...");
        try {
            File file=new File(args[0]);
            int count=LineCounter.countEffectiveLine(file);
            System.out.println("There are "+count+" effective lines");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
