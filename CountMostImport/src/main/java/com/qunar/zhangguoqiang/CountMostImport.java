package com.qunar.zhangguoqiang;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;

public class CountMostImport {
    private Multiset<String> ImportClassSet=null;
    private String dirname;
    public CountMostImport(String dirname)
    {
        ImportClassSet= HashMultiset.create();
        this.dirname=dirname;
        readDirectory(dirname);
    }
    private void readDirectory(String dirname)
    {
        File dir=new File(dirname);
        File[] files=dir.listFiles();
        for(File file:files)
        {
            if(file.isDirectory())
            {
                readDirectory(file.getAbsolutePath());
            }
            else if(file.getName().matches(".*\\.java"))
                try {
                    System.out.println("文件"+file.getAbsolutePath());
                    countImportClass(file.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
    }
    private void countImportClass(String filename) throws IOException {
        String LineString=null;
        BufferedReader br=new BufferedReader(new FileReader(filename));
        while((LineString=br.readLine())!=null)
        {
            if(LineString.matches("^import\\s+([^\\s]*?\\.)+([A-Z].*?);$"))
                ImportClassSet.add(LineString.substring(6,LineString.length()-1).trim());
            else if(LineString.matches("^\\s*(public|private|protected|class).*$"))
                break;
        }
    }
    public void getInfo()
    {
        int count=0;
        ImmutableSet<Multiset.Entry<String>> info= Multisets.copyHighestCountFirst(ImportClassSet).entrySet();
        System.out.println("-------------"+dirname+"统计信息-----------------------");
        for(Multiset.Entry<String> e:info)
        {
            System.out.println("类名:"+e.getElement()+"\t"+"import次数:"+e.getCount()+"\t");
            if(count++>=9)
                break;
        }
    }

    public static void main(String[] args)
    {
        String dirname="";
        CountMostImport CMI=null;
        Scanner scanner=new Scanner(System.in);
        System.out.println("输入目录路径");
        dirname=scanner.nextLine();
        File dir =new File(dirname);
        if(!dir.exists())
        {
            System.out.println("目录不存在");
            return;
        }
        else if(!dir.isDirectory())
        {
            System.out.println("输入的路径不是一个目录");
            return;
        }
        else
        {
            CMI=new CountMostImport(dir.getAbsolutePath());
            CMI.getInfo();
        }
    }
}
