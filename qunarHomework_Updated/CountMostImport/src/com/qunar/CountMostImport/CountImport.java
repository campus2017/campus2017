package com.qunar.CountMostImport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.String;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**【类】功能
 * 1、访问遍历到的.java文件，统计其中import的各个类的数目
 * 2、按照import次数排序输出import最多的前10个类
 * （更加具体的实现细节，见各个方法的功能描述）
 * @author YANG LIU
 */

public class CountImport {
    private static String [] className=new String[4000];
    private static  int [] count=new int[4000];
    static int index=0;
    static int classNum=0;

    /**【方法】功能
     * 1、将读取到的import行的内容存入字符串数组className，并且在数组count中设置对应的计数
     * 2、如果读取的import在字符串数组className中已经存在，则只将对应的计数加1
     * 3、如果读取的import不在字符串数组className中，则将新读到的import行内容追加到className中，并将对应的计数加1
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

    /**【方法】功能
     * 1、dire参数给出了java文件路径，从文件路径中识别出以“import”开头的类导入行，并调用ExtractSubString方法加以处理
     *   （具体处理方法，详见ExtractSubString()的方法功能说明）
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

    /**【方法】功能
     * 1、对className字符串数组中已经存放的类的名字格式化处理
     *    因为之前读到的是java文件中“import java......”整行内容，还有可能包括最后的注释，所以加以格式化，去除类名以外多余的字符
     * 2、根据导入的类的次数进行排序，输出导入次数最多的10个类
     * */
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


