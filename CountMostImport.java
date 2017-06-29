import java.io.*;
import java.util.*;

/**
 * Created by 娜娜 on 2017/6/28.
 */
public class CountMostImport {
    private static String dirName;//存放项目目录
    private static HashMap<String, Integer> importClassRecords;//HashMap存放import的类及import次数
    public CountMostImport(String dir){
        this.dirName = dir;
        importClassRecords = new HashMap<String, Integer>();//每次使用初始化HashMap
        this.statisticsImportClass(new File(this.dirName));
    }

    private static void statisticsFile(File file){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return;
        }
        String line = null;
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

    private static void statisticsImportClass(File file){
        if(!file.isDirectory()){
            statisticsFile(file);
        }else{
            File [] files = file.listFiles();
            for(File tmpFile: files){
                statisticsImportClass(tmpFile);
            }
        }

    }

    public void getTop10ImportClazzName() {
        //这里将importClassRecords.entrySet()转换成list
        List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(importClassRecords.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>() {//降序排序
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        int count = 0;
        for(Map.Entry<String,Integer> mapping:list){
            System.out.println(mapping.getKey()+":"+mapping.getValue());
            count++;
            if(count > 9)
                break;
        }

    }
    public static void main(String[] args) {
        CountMostImport CountMostImport = new CountMostImport("D:/IdeaProjects/ExchangeRate");
        CountMostImport.getTop10ImportClazzName();
    }
}
