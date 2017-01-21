package CountMostImport;

import java.io.*;
import java.util.*;

/**
 * 功能：根据指定项目目录下（可以认为是java源文件目录）中，统计被import最多的类，前十个是什么
 * 作者：Yung
 * 时间：2016-12-1
 */
public class CountMostImport
{
    static HashMap<String, Integer> importClassRecords=new HashMap<String, Integer>();

    public static void main( String[] args )
    {
        String importClass=null;
        HashMap<String,Integer> countClass=null;
        File file=new File("F:\\work\\SEI-Pro\\src\\com\\controller\\"); //通过指定路径读取java文件
        File[] codeFiles= file.listFiles();
        for(File child:codeFiles){
            if(child.getName().matches(".*\\.java$")){   //判断当前文件是否以.java结尾
                processFile(child);
            }
        }
        countClass= (HashMap) descHashMapByComparator(importClassRecords);

        System.out.println("被import最多的前10个类：");
        int i=0;
        for(Map.Entry item: countClass.entrySet()){
            String key = (String) item.getKey();
            int value = (Integer)item.getValue();
            System.out.println("类名： "+key+" ， 被import次数： "+value);  //打印有效代码行数
            i++;
            if(i==10) break;
        }
    }

   /*   对当前行进行处理，统计被引用类的次数*/
    private static void processFile(File f) {
        BufferedReader reader=null;
        String line = null;
        try {
            reader = new BufferedReader(new FileReader(f));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            while((line = reader.readLine()) != null){
                line = line.trim();
                if(line.startsWith("public")||line.startsWith("class")){
                    break;
                }
                if(line.startsWith("import")){
                    String className = line.substring(6, line.length()-1).trim();
                    Integer value = importClassRecords.get(className);
                    if(value==null){
                        importClassRecords.put(className, 1);
                    }else{
                        importClassRecords.put(className, value+1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*对map按照value值进行降序处理*/
    private static Map descHashMapByComparator(Map map) {
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator()
        {
            public int compare(Object o1, Object o2)
            {
                return ((Comparable) ((Map.Entry) (o2)).getValue())
                        .compareTo(((Map.Entry) (o1)).getValue());
            }
        });
        Map sortedMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry)it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

}
