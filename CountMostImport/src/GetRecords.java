import java.io.*;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/12/20.
 */
//统计引入包的包名和数量，放入map<String Integer>中
class GetRecords {
   private HashMap<String, Integer> importClassRecords;//存放统计结果
   private String path;

    public GetRecords(String path){
        this.path=path;
        importClassRecords = new HashMap<String, Integer>();
        this.statisticsClazz(new File(this.path));
    }
    //测试对象是否为文件夹
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
    //对文件的处理，统计该文件引入包的包名和数量
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

    public HashMap<String, Integer> getImportClassRecords(){
        return this.importClassRecords;
    }
}
