import java.io.*;
import java.util.*;

/**
 * Created by Administrator on 2017/5/31.
 */
public class CountMostImport{
    public static void main(String args[]){
        //根据需要修改路径
       CountMostImport1 c1=new CountMostImport1("F:\\工作\\EffectiveLines");
       c1.getMostImportClassName();
    }
}

 class CountMostImport1 {
    String dirName;
    TreeMap<String, Integer> importClassRecords;
    public CountMostImport1(String dir){
        this.dirName = dir;
        importClassRecords = new TreeMap<String, Integer>();
        this.statisticsClass(new File(this.dirName));
    }

    public int get(String clazzName){
        Integer value = importClassRecords.get(clazzName);
        if(value==null) return 0;
        return value;
    }
    public void processFile(File file){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void statisticsClass(File file){
        if(!file.isDirectory()){
            processFile(file);
        }else{
            File [] files = file.listFiles();
            for(File tmpFile: files){
                statisticsClass(tmpFile);
            }
        }
    }
/**
*获取最多import的类别，将最多的10类显示出来
 **/
    public void getMostImportClassName(){
        List<Map.Entry<String,Integer>> li=new ArrayList<>(this.importClassRecords.entrySet());
        Collections.sort(li, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o1.getValue()>o2.getValue()) return -1;
                else if (o1.getValue()<o2.getValue()) return 1;
                else return 0;
            }
        });
        int i=0;
        for(Map.Entry<String ,Integer>entry :li) {
            System.out.println("importClassname: "+entry.getKey()+" ;count: "+entry.getValue());
            i=i+1;
            if(i==10) break;
            }
        }

    }

