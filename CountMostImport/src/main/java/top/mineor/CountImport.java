package top.mineor;

/**
 * Created by Mineor on 2017/7/1.
 */
import java.io.*;
import java.util.*;

public class CountImport {

    public static HashMap<String, Integer> importClass = new HashMap<String, Integer>();

    public static void processFiles(String dirString) {

        File file = new File(dirString);

        if(!file.exists()) {
            System.out.println("file not exists!");
            System.exit(1);
        }

        if(file.isFile()){
            readFromFile(file);
        }
        else if(file.isDirectory()){
            //列出目录下所有文件
            String [] fileList = file.list();
            for(int i = 0 ; i < fileList.length ; i++) {
                String filePath = dirString+ "/"+ fileList[i];  // 构建新的路径
                processFiles(filePath);   // 递归处理
            }
        }

    }

    private static void readFromFile(File file){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine())!=null) {
                if(line.trim().startsWith("import ")) {
                    String splitResult []= line.trim().split(" ");
                    for(int i =1 ; i < splitResult.length ; i++) {
                        if(importClass.containsKey(splitResult[i])){
                            Integer val = importClass.get(splitResult[i])+1;
                            importClass.put(splitResult[i],val);
                        }
                        else{
                            importClass.put(splitResult[i],1);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void countMostImportClass() {
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(importClass.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        for (int i = 0; i < list.size() && i < 10; i++) {
            System.out.println(list.get(i).getKey());
        }
    }
}