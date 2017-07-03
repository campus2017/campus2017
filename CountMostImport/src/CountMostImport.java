/**
 * Created by mml on 17-7-3.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountMostImport{
    HashMap<String,Integer> map=new HashMap<String,Integer>();
    int maxNum=0;

    public static void main(String[] args)
    {
        CountMostImport cmi=new CountMostImport();
        cmi.searchFile(new File("/home/mml/IdeaProjects"));
        cmi.printTopKClass(10);
    }


    // 遍历所有java文件
    public void searchFile(File f)
    {
        if(f.isDirectory())
        {
            File[] files=f.listFiles();
            for (int i=0;i<files.length;i++)
                searchFile(files[i]);
        }
        else
        {
            String fileName=f.getName();
            if (fileName.endsWith(".java")) {
                processFile(f);
            }
        }
    }
    // 使用hashmap记录java文件中import的类
    public void processFile(File f)
    {

        try {
            BufferedReader br=new BufferedReader(new FileReader(f));
            String line;
            while ((line=br.readLine())!=null)
            {
                line=line.trim();
                if (line.startsWith("class")||line.startsWith("public"))
                    break;
                if (line.startsWith("import"))
                {
                    String className=line.substring(6,line.length()-1);
                    Integer n=map.get(className);
                    if(null==n)
                        map.put(className,1);
                    else {
                        map.put(className, n + 1);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    // 输出次数最多的前K个类
    public void printTopKClass(int k)
    {
        List<ClassAndValue> list=changeHashMapToList();
        GetTopK func=new GetTopK();
        List<ClassAndValue> topK=func.getTopK(list,k);
        System.out.println(String.format("该project(s)中被impor最多的前%d个类如下：",k));
        for(int i=0;i<topK.size();i++)
            System.out.println(topK.get(i).className+String.format(" ： %d次：",topK.get(i).times));
    }
    private List<ClassAndValue> changeHashMapToList(){
        List<ClassAndValue> list = new ArrayList<ClassAndValue>();
        for(Map.Entry item: this.map.entrySet()){
            String key = (String) item.getKey();
            int value = (Integer)item.getValue();
            list.add(new ClassAndValue(key,value));
        }
        return list;
    }


}
