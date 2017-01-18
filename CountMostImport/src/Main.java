
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class CountMostImportClass {
    String dirName;
    HashMap<String, Integer> importClassRecords;
    public MostImportClass(String dir){
        this.dirName = dir;
        importClassRecords = new HashMap<String, Integer>();
        this.statisticsClazz(new File(this.dirName));
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

    public String getMostImportClazzName(){
        int max = Integer.MIN_VALUE;
        String clazzName = null;
        for(Entry item: this.importClassRecords.entrySet()){
            String key = (String) item.getKey();
            int value = (Integer)item.getValue();
            if(value>max){
                max = value;
                clazzName = key;
            }
        }
        return clazzName;
    }



}

