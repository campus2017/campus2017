package com.qunar.CountMostImport;

import java.io.File;
import java.io.IOException;

/**
 * @author liuyang[QA]
 */
public class FindJavaFiles {
   static int i=0;
   public static void listDir(String filePath)throws IOException{

       File dir=new File(filePath);
       if (!dir.exists()) {
           throw new IllegalArgumentException("目录/文件不存在！");
       }
       if (dir.isFile()){
           String fileName=dir.getName();
           if(fileName.endsWith(".java")){
               CountImport.Count(dir);
               i++;
           }
       }
       else {
           if (dir.isDirectory()) {
               String[] names = dir.list();
               for (String str : names
                       ) {
                   listDir(dir.getPath()+File.separatorChar +str);
               }
           }
       }
   }
}

