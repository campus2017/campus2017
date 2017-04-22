package com.lfz;
import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String dirPath = scanner.nextLine();
        File file=new File(dirPath);
        ArrayList<File> fileList=new ArrayList<File>();
        HashMap<String,Integer> map=new HashMap<String,Integer>();
        getFiles(file,fileList);
        for(File f:fileList){
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String str=null;
            String name=null;
            Integer count=null;
            while((str=reader.readLine())!=null){
                if(str.startsWith("import")){
                    name=str.substring(6, str.length()-1).trim();
                    count=map.get(name);
                    if(count==null)
                        map.put(name,1);
                    else
                        map.put(name,count+1);
                }
            }
        }
        Iterator<Map.Entry<String,Integer>> it=map.entrySet().iterator();
        Map.Entry[] result=new Map.Entry[10];
        for (int i = 0; i < 10; i++) {
            if(it.hasNext())
                result[i]=it.next();
            else
                break;
        }
        buildHeap(result);
        while(it.hasNext()){
            Map.Entry<String,Integer> tmp=it.next();
            if(tmp.getValue()>((Map.Entry<String,Integer>)result[0]).getValue()){
                result[0]=tmp;
                adjustHeap(result,0,9);
            }
//            System.out.print(entry.getKey());
//            System.out.print(",");
//            System.out.print(entry.getValue());
//            System.out.println();
        }
        for (int i = 9; i > 0; i--) {
            Map.Entry<String,Integer> tmp= result[0];
            result[0]=result[i];
            result[i]=tmp;
            adjustHeap(result, 0, i-1);
        }
        for(Map.Entry<String,Integer> entry:result)
            System.out.println(entry.getKey()+" : "+entry.getValue());

    }
    public static void getFiles(File file,ArrayList<File> list){
        if(file==null)
            return;
        if(file.isDirectory()){
            File[] files=file.listFiles();
            if(files!=null){
                for(int i=0;i<files.length;i++){
                    getFiles(files[i],list);
                }
            }
        }else if(file.getName().endsWith(".java")){
            list.add(file);
        }
    }
    public static void buildHeap(Map.Entry<String,Integer>[] entrys) {
        int end = entrys.length - 1;
        for (int i = (end - 1) / 2; i >= 0; i--) {
            adjustHeap(entrys, i, end);
        }
    }
    public static void adjustHeap(Map.Entry<String,Integer>[] entrys, int init, int end) {
        Map.Entry<String,Integer> tmp = entrys[init];
        for (int i = init * 2 + 1; i <= end; i = 2 * i + 1) {
            if (i != end && entrys[i].getValue() > entrys[i + 1].getValue())
                i++;
            if (tmp.getValue() < entrys[i].getValue())
                break;
            else {
                entrys[init] = entrys[i];
                init = i;
            }
        }
        entrys[init] = tmp;
    }

}

