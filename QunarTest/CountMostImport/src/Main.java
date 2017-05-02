import java.io.*;
import java.util.*;

/**
 * Created by zhuyin on 16-11-23.
 */
public class Main {

    public static void main(String args[]){
        String path = "ExchangeRate/src/main";
        List<File> files = getFile(path,new ArrayList<File>());
        Map<String,Integer> ret = countImportNum(files);
        for(Map.Entry<String,Integer> entry:ret.entrySet()){
            System.out.println("class: "+entry.getKey()+" nums；"+entry.getValue());
        }
    }

    private static Map<String,Integer> countImportNum(List<File> files) {
        Map<String,Integer> map = new HashMap();

        Map<String,Integer> ret = new HashMap<>();
        
        if(files.size()==0)
            return null;
        for(File f:files) {
            readFile(f, map);
        }

        List<Integer> res = FindKthLargest.findKthNum(map.values().toArray(new Integer[map.values().size()]),10);

        for(Map.Entry<String,Integer> entry : map.entrySet()){
            int v = entry.getValue();
            if(res.contains(v))
                ret.put(entry.getKey(),entry.getValue());
        }
        return ret;
    }

    private static void readFile(File file, Map<String, Integer> map) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = "";
            while((line=reader.readLine())!=null){
                if(line.startsWith("import")){
                    line = line.replaceAll("\\s","");
                    line = line.substring(6);
                    if(map.get(line)==null)
                        map.put(line,1);
                    else
                        map.put(line,map.get(line)+1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static List<File> getFile(String path,List<File> fileList) {
        File file = new File(path);
        if(file.exists()){
            File[] files = file.listFiles();
            if(files.length>0){
                for(File f:files){
                    if(f.isDirectory()){
                        getFile(f.getAbsolutePath(),fileList);
                    }else{
                        fileList.add(f);
                    }
                }
            }
        }
        return fileList;
    }
}
