package com.java;

import javax.swing.text.html.HTMLDocument;
import java.io.File;
import java.util.*;
import java.util.*;
/**
 * Created by apple on 17/7/2.
 */
public class fileOperate {

    private List<String> fileList=null;
   private  String path=null;
private  List<String> top10=null;
    public fileOperate(String path)
    {
        fileList=new ArrayList<>();
        traverseFolder(path);
        top10=new ArrayList<>();
        top10=selectTop10();
    }
    public List<String> selectTop10()
    {
        Map<String,Integer> map=new HashMap<>();
        Iterator iter= fileList.iterator();
        while(iter.hasNext())
        {
            String str=(String) iter.next();

                    if(map.get(str)!=null)
                    {
                        int preNum=map.get(str);
                        map.put(str,preNum+1);
                    }
                    else
                        map.put(str,1);
                }
        List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(map.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>() {
            //升序排序
            public int compare(Map.Entry<String,Integer> o1,
                               Map.Entry<String,Integer> o2) {
                return Integer.parseInt(o2.getValue().toString())-Integer.parseInt(o1.getValue().toString());
            }

        });
        List<String> res=new ArrayList<>();
        Iterator iter1= list.iterator();
        int i=10;
        while(iter1.hasNext()&&i>0)
        {
            Map.Entry<String,Integer> temp=(Map.Entry<String,Integer>)iter1.next();
            String str=temp.getKey() ;
            String resStr=str.replace("import ","");
            resStr.replace(";","");
            res.add(resStr);
            i--;
        }
        return res;
     }
    public void traverseFolder(String path) {

        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
//                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        traverseFolder(file2.getAbsolutePath());
                    } else {
                        fileContent newFile=new fileContent(file2.getAbsolutePath());
                        fileList.addAll(newFile.getContent());
//                        System.out.println("文件:" + file2.getAbsolutePath());
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }

    public List<String> getTop10() {
        return top10;
    }

    public void setTop10(List<String> top10) {
        this.top10 = top10;
    }
}
