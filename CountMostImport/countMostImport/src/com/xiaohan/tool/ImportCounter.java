package com.xiaohan.tool;

import com.sun.javafx.scene.control.skin.VirtualFlow;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

/**
 * Created by dell on 2016/11/16.
 */
public class ImportCounter {
    //优先级比较器
    private Comparator<ImpNode> comparator =  new Comparator<ImpNode>() {
        @Override
        public int compare(ImpNode o1, ImpNode o2) {
            if (o1==o2) return 0;
            if(o1.count.equals(o2.count)) return 0;
            return o1.count > o2.count ? -1 : 1;
        }
    };
    //内部节点
    public static class ImpNode{
        public ImpNode(String s,Integer i){
            this.name=s;
            this.count=i;
        }
        public String name;
        public Integer count;
    }
    public  List<ImpNode> countMostImportant(File file,int  n){
        if(!file.isDirectory()) return null;
        LinkedList<File> queue = new LinkedList<>();
        //按照引用次序排队
        HashMap<String,ImpNode> map=new HashMap<>();
        queue.offer(file);
        while (!queue.isEmpty()){
            File dir=queue.poll();
            File[] files=dir.listFiles();
            for(File f:files){
                //如果不是文件夹就分析之，如果是就加入队列，广度优先搜索
                if(!f.isDirectory()) {
                    if(f.getName().endsWith(".java")){
                        //分析单个文件
                        analyseOneJavaFile(f,map);
                    }
                }else{
                    queue.offer(f);
                }
            }//for
        }//while
        List<ImpNode> resList = new ArrayList<>(map.values());
        Collections.sort(resList,comparator);
        //result
        if(resList.size()>n)
            resList=resList.subList(0,n);

        return resList;
    }
    //
    public void analyseOneJavaFile(File file,Map<String,ImpNode> map){
        BufferedReader reader;
        try{
            reader=new BufferedReader(new FileReader(file));
            String strLine;
            while((strLine= reader.readLine())!=null){
                strLine=strLine.trim();
                if(strLine.startsWith("import")){
                    String[] strArr = strLine.split("\\s+");
                    if(strArr!=null && strArr.length>1){
                        String className=strArr[1];
                        if(map.containsKey(className)){
                            ImpNode tmp=map.get(className);
                            tmp.count++;
                        }else{
                            ImpNode node= new ImpNode(className,1);
                            map.put(className,node);
                        }

                    }
                //提前终止条件
                }else if(strLine.startsWith("public")||strLine.startsWith("interface")||
                        strLine.startsWith("class")|| strLine.startsWith("enum")){
                    reader.close();
                    return;
                }
            }
            reader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
