package com.qunar.CountMostImport;

import java.io.File;
import java.io.IOException;

/**功能
 * 1、遍历指定的目录，找到目录下所有的.java文件
 * 2、每找到一个.java文件，就调用自定义类CountImport的方法，访问文件，并统计被import的类
 * @author YANG LIU
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

