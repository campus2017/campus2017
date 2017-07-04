/**
 * Created by dxq on 2017/6/21.
 */

/**
 *Count the most imported class in the specifies  project directory. author@Dai Xiaoqin.
 */

import java.io.*;
import java.util.*;

public class CountMostImport  {
    private String dir;
    private HashMap<String, Integer> importClassRecords;

    public CountMostImport (String dir){
        this.dir= dir;
        importClassRecords = new HashMap<String, Integer>();
        this.statisticsClazz(new File(dir));
    }

    //读取文件
    public void processFile(File file){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
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
                    String className = line.substring(6, line.length()-1);
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

    public void statisticsClazz(File file){
        if(!file.isDirectory()){
            processFile(file);
        }else{
            File[] files = file.listFiles();
            for(File f: files){
                statisticsClazz(f);
            }
        }
    }

    public List<Map.Entry<String,Integer>> getTopTenImporte(){
       // int max = Integer.MIN_VALUE;
        //String clazzName=null;
        List<Map.Entry<String,Integer> > listImport = new ArrayList<Map.Entry<String, Integer>>();
        for(Map.Entry item: this.importClassRecords.entrySet()){
            String key = (String) item.getKey();
            int value = (Integer)item.getValue();
            listImport.add(item);
        }
        if(listImport.size()<10)
            return listImport;

        Collections.sort(listImport,new Comparator<Map.Entry<String, Integer> >(){
            public int compare(Map.Entry<String, Integer> item1, Map.Entry<String, Integer> item2) {
                return item2.getValue()-item1.getValue();
            }
        });

        List<Map.Entry<String,Integer> > top10list = new ArrayList<Map.Entry<String, Integer>>();
        for(int i = 0;i<10;i++) {
            top10list.add(listImport.get(i));
        }
        return top10list;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        CountMostImport count=new CountMostImport(fileName);
        List<Map.Entry<String,Integer>> TopTen = count.getTopTenImporte();
        for(Map.Entry<String,Integer> entry:TopTen) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        scanner.close();
    }
}

