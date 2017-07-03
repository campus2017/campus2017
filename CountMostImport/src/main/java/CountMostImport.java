package qunar.yaozhu;

import java.io.*;
import java.util.*;

/**
 * 根据指定项目目录下（可以认为是java源文件目录）中，统计被import最多的类，前十个是什么
 * 作业命名(CountMostImport)
 */
public class CountMostImport {
    private Map<String, Integer> map = new HashMap<String, Integer>();

    public static void main(String[] args) throws Exception {
        CountMostImport countMostImport = new CountMostImport();
        System.out.print("Pleast input the file path:");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        scanner.close();
        countMostImport.countImport(path);
        
    }

    public void countImport(String dirPath) throws Exception {
        File file = new File(dirPath);
        depthSearch(file);
        sortMapByCollection();
    }

    public String depthSearch(File f) throws Exception {
        if (f.isDirectory()) {
            File[] files = f.listFiles();
            if (files != null) {
                for (File file : files) {
                    depthSearch(file);
                }
            }
        } else {
            findNumberofImport(f);
        }
        return null;
    }

    private void findNumberofImport(File f) throws Exception {
        
        int count = 0;
        InputStream in = new FileInputStream(f);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = "";
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.startsWith("import")) {
                //只保留引用的包名
                line = line.substring(6);
                if (!map.containsKey(line)) {
                    map.put(line, 1);
                } else {
                    int value = map.get(line);
                    map.put(line, value + 1);
                }
            }          
        }
        reader.close();
    }

    //按照value数值排序Map，并且输出最多引用和前十引用。
    public void sortMapByCollection(){
        Comparator<Map.Entry<String, Integer>> valueComparator = new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        };
        List<Map.Entry<String, Integer>> listOfTheMap = new ArrayList<>(map.entrySet());
        listOfTheMap.sort(valueComparator);

        System.out.println("The most import is : " + listOfTheMap.get(0).getKey() + " : " + listOfTheMap.get(0).getValue());

        System.out.println("Top 10 of the import is : ");
        for(int i = 0; i < 10; i++){
            System.out.println(listOfTheMap.get(i).getKey() + " : " + listOfTheMap.get(i).getValue());
        }

    }

}
