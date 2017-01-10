import java.io.*;
import java.util.*;
import java.util.Map.Entry;


/**
 * To count the most top ten import class.
 * @version 1  2017-01-05
 * @author Abby
 */


public class MostImportClass {

    String dirName;
    HashMap<String, Integer> importClassRecords;
    public MostImportClass(String dir){
        this.dirName = dir;
        importClassRecords = new HashMap<String, Integer>();
        this.statisticsClazz(new File(this.dirName));
    }

    //得到某个类（key）被统计到的个数（value）
    public int get(String clazzName){
        Integer value = importClassRecords.get(clazzName);
        if(value==null) return 0;
        return value;
    }
    //处理文件，统计每个文件的引用类
    public void processFile(File file){
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

    //处理文件（单个文件或文件夹）
    public void statisticsClazz(File file){
        if(!file.isDirectory()){
            processFile(file);
        }else{
            File [] files = file.listFiles();
            for(File tmpFile: files){
                statisticsClazz(tmpFile);
            }
        }

    }

    //得到被引用最多的Top10的类，如果不够10个则返回所有，不足返回null
    public String[] getMostImportClazzName(){

        int max = 10;
        String  clazzName[] = new String[10];
        //先把Hashmap按value由大到小排序
        List<Entry<String, Integer>> infoIds = new ArrayList<Map.Entry<String, Integer>>(importClassRecords.entrySet());
        Collections.sort(infoIds, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o2.getValue() - o1.getValue());}
        });

        if(infoIds.size()<10)
            max=infoIds.size();

        for (int i = 0; i < max; i++) {
            clazzName[i] = (String) infoIds.get(i).getKey();
        }

        return clazzName;
    }

    public static void main(String[] args)
    {
        MostImportClass M = new MostImportClass("D:/IdeaProjects/CountMostImport/src");   //"E:/eclipseWork/J2EE/DTW_KNN_Classification/src/com/zn/timeseries");
        String  clazzNames[] = new String[10];
        clazzNames = M.getMostImportClazzName();

        System.out.println("被引用最多的前十个类：");
        for (int i = 0; i < 10; i++) {

            System.out.println(clazzNames[i]);
        }

    }

}
