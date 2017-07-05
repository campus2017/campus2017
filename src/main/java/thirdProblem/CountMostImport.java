package thirdProblem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author gonglei
 */
public class CountMostImport {
    private Map<String, Integer> fileAndCountMap = new HashMap<>();
    
    /**
     *
     * @param file file path
     * @throws java.lang.Exception
     */
    public void depSearchFile(File file) throws Exception{
        if(file.isDirectory()){
            File[] files = file.listFiles();
            if(files != null){
                for(File f : files){
                    depSearchFile(f);
                }
            }
        }else{
            countImportFile(file);
        }
    }
    
    /**
     *
     * @param file pase file to count file and times
     * @throws java.lang.Exception
     * 
     */
    public void countImportFile(File file) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String str = "";
        while((str=br.readLine()) != null){
            if(str.startsWith("import")){
                String[] strs = str.split(" ");
                String importFileName = strs[1].substring(0, strs[1].length()-1);
                if(fileAndCountMap.containsKey(importFileName)){
                     fileAndCountMap.put(importFileName, fileAndCountMap.get(importFileName)+1);
                }else{
                    fileAndCountMap.put(importFileName, 1);
                }
            }
        }
    }
    
    /**
     *
     * @param map store file and its times
     */
    public void getCountMostImport(){
        List<Map.Entry<String, Integer>> list = new ArrayList<>(fileAndCountMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>(){
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        int flag = 0;
        for(Map.Entry<String, Integer> map : list){
            if(10==flag) break;
            System.out.println(map.getKey());
            flag++;
        }
    }
    
    public static void main(String[] args) throws Exception {
        CountMostImport countMostImport = new CountMostImport();
        Scanner sc = new Scanner(System.in);
        String dirPath = sc.nextLine();
        File file = new File(dirPath);
        countMostImport.depSearchFile(file);
        countMostImport.getCountMostImport();
    }
}
