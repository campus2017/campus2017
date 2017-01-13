package com.qunar.CountMostImport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.String;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author liuyang[QA]
 */

public class CountImport {
    private static String [] className=new String[4000];
    private static  int [] count=new int[4000];
    static int index=0;
    static int classNum=0;

    /**
     * @param str the string starting with "import"
     * */

    public  static void ExtractSubString(String str){
        int i;
        String s;
        List<String>list=Arrays.asList(className);
        List strList=new ArrayList(list);
        if (list.contains(str))
        {
            index=strList.indexOf(str);
            count[index]++;
        }
        else{
            if (className[0]==null)
            {
                strList.add(0,str);
                className[0]=(strList.get(0)).toString();
                count[0]++;
                classNum++;
            }
            else{
                for (i=0;className[i]!=null;i++)
                {
                }
                strList.add(i,str);
                className[i]=(strList.get(i)).toString();
                count[i]++;
                classNum++;
            }
        }
    }

    /**
     * @param dire the path of specific java file
     * */
    public static void Count(File dire){
        BufferedReader br ;
          try{
              br = new BufferedReader(new FileReader(dire));
              String tempStr;
              while(((tempStr=br.readLine())!=null))
              {
                  if (tempStr.startsWith("import"))
                      ExtractSubString(tempStr);
              }
                br.close();
          }
          catch(Exception e) {
              e.printStackTrace();
          }
    }

    public static void Output(){
        System.out.println("共导入了 "+classNum+" 个类");
        String tempStr;
        int temp;
        for (int i=0;i<classNum;i++)
        {
            for (int k=classNum-1;k>i;k--){
            if (count[k]>count[k-1]) {
                temp = count[k];
                count[k] = count[k-1];
                count[k-1] = temp;

                tempStr = className[k];
                className[k] = className[k-1];
                className[k-1] = tempStr;
            }
            }
        }
        System.out.println("import最多的前10个类为：（说明：[ ]中为该类被import的次数）");
        for (int j=0;j<10&&className[j]!=null;j++) {
            className[j]=className[j].replace("import","");
            String s=className[j];
            int m;
            for (m=0;s.charAt(m)!=';';m++){

            }
            className[j]=className[j].substring(1,m);
            System.out.println(className[j]+"["+count[j]+"]");
        }
    }
}


