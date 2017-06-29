import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by isc on 2016/12/19.
 */
public class CountMostImport {

    private String dir;
    private HashMap<String,Integer> importRecords;

    public CountMostImport(String dir){
        this.dir=dir;
        importRecords = new HashMap<String, Integer>();
        this.staticsAllFiles(new File(dir));
    }


    /**
     * 利用小根堆求取Top K数据
     * @param k
     */
    public void getTopKParams(int k){
        List<MostImportClass> list = TopK.getTopK(changeHashMapToList(),k);
        for(int i=0;i<list.size();i++) {
            System.out.println(list.get(i).importName+":"+list.get(i).value);
        }
    }

    /**
     * 遍历文件和文件夹
     * @param file
     */
    private void staticsAllFiles(File file){
        if(!file.isDirectory()){
            processFile(file);
        }else{
            File[] files = file.listFiles();
            for(File f:files){
                staticsAllFiles(f);
            }
        }
    }

    /**
     * 加载文件
     * @param file
     */
    private void processFile(File file){
        BufferedReader reader=null;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        String line = null;
        try {
            while((line=reader.readLine())!=null){
                line = line.trim();
                if(line.startsWith("public")||line.startsWith("class")){
                    break;
                } if(line.startsWith("import")) {
                    putParamsToHashMap(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 把加载数据放入HashMap中
     * @param line
     */
    private void putParamsToHashMap(String line) {
        String className = line.substring(6, line.length() - 1).trim();
        Integer value = importRecords.get(className);
        if (value == null) {
            importRecords.put(className, 1);
        } else {
            importRecords.put(className, 1 + value);
        }
    }

    /**
     * 把HashMap中的数据转换成list<MostImortClass>
     * @return
     */
    private List<MostImportClass> changeHashMapToList(){
        List<MostImportClass> list = new ArrayList<MostImportClass>();
        for(Map.Entry item: this.importRecords.entrySet()){
            String key = (String) item.getKey();
            int value = (Integer)item.getValue();
            list.add(new MostImportClass(key,value));
        }
        return list;
    }

}
