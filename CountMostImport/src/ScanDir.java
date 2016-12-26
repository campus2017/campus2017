import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by luvslu on 2016/12/26.
 * scan the dir, and count the most imported class
 */
public class ScanDir {
    private Map<String, Integer> importCountMap;
    private Map<String, Integer> sortedCountMap;
    private String dirPath;

    /**
     * 构造函数
     * @param path 需要扫描的文件夹路径
     * */
    public ScanDir(String path){
        this.dirPath = path;
        this.importCountMap = new HashMap<>();
        this.sortedCountMap = new LinkedHashMap<>();
    }

    /**
     * 遍历文件夹，保存被引用的类字典
     * */
    public void setImportCountMap(){
        int fileNum = 0, folderNum = 0;
        File file = new File(dirPath);
        if (file.exists()) {
            LinkedList<File> list = new LinkedList<>();
            list.add(file);
            File temp_file;
            File[] files;
            while (!list.isEmpty()) {
                temp_file = list.removeFirst();
                files = temp_file.listFiles();
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        //System.out.println("文件夹:" + file2.getAbsolutePath());
                        list.add(file2);
                        fileNum++;
                    } else {
                        System.out.println("扫描文件:" + file2.getAbsolutePath());
                        folderNum++;
                        readFile(file2.getAbsolutePath());
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
        System.out.println("文件夹共有:" + folderNum + ",文件共有:" + fileNum);
    }

    /**
     * 按行读取文件，扫描文件中import的类，并保存到字典里
     * */
    private void readFile(String filePath){
        File file = new File(filePath);
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while((tempString = reader.readLine()) != null){
                //这里判断import的类，并保存到字典
                if(tempString.indexOf("import") == 0){
                    String[] tempArr = tempString.split(" ");
                    String classStr = tempArr[1].substring(0,tempArr[1].length() - 1);
                    if(importCountMap.containsKey(classStr)){
                        importCountMap.put(classStr, importCountMap.get(classStr)+1);
                    }else{
                        importCountMap.put(classStr, 1);
                    }
                }
            }
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(reader != null){
                try{
                    reader.close();
                }catch (IOException e1){
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取引用前topNum的类
     * @param topNum 需要返回的类个数
     * */
    public  Map<String, Integer> getMostImportClass(int topNum){
        if(importCountMap.isEmpty()){
            return null;
        }
        if(sortedCountMap.isEmpty()){
            sortMapByValue(importCountMap, sortedCountMap);
        }

        int count = 0;
        Map<String, Integer> mostImportClassMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : sortedCountMap.entrySet()) {
            //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            mostImportClassMap.put(entry.getKey(), entry.getValue());
            count++;
            if(count >= topNum){
                break;
            }
        }
        return mostImportClassMap;
    }

    private void sortMapByValue(Map<String, Integer> oriMap, Map<String, Integer> sortedMap){
        if (oriMap != null && !oriMap.isEmpty()) {
            List<Map.Entry<String, Integer>> entryList = new ArrayList<Map.Entry<String, Integer>>(oriMap.entrySet());
            Collections.sort(entryList,
                    new Comparator<Map.Entry<String, Integer>>() {
                        public int compare(Map.Entry<String, Integer> entry1,
                                           Map.Entry<String, Integer> entry2) {
                            int value1 = 0, value2 = 0;
                            try {
                                value1 = entry1.getValue();
                                value2 = entry2.getValue();
                            } catch (NumberFormatException e) {
                                value1 = 0;
                                value2 = 0;
                            }
                            return value2 - value1;
                        }
                    });
            Iterator<Map.Entry<String, Integer>> iter = entryList.iterator();
            Map.Entry<String, Integer> tmpEntry = null;
            while (iter.hasNext()) {
                tmpEntry = iter.next();
                sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
            }
        }
        return;
    }
}
