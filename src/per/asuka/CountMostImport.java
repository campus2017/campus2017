package per.asuka;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by asuka on 23/06/2017.
 */
public class CountMostImport {
    private static HashMap<String, Integer> fileCountMap;
    private static Vector<File> fileVec;
    private static Pattern pattern = Pattern.compile("import\\s(.*);");
    private static List<Map.Entry<String, Integer>> list;
    private static void printTop10(){
        for (int i = 0; i < 10 && i < list.size(); i++){
            System.out.println(String.format("%s:%d", list.get(i).getKey(), list.get(i).getValue()));
        }
    }
    private static void sort(){
        list = new ArrayList<Map.Entry<String, Integer>>(fileCountMap.entrySet());
        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o1.getValue() < o2.getValue()) return 1;
                else if (o1.getValue() == o2.getValue()) return 0;
                else return -1;
            }
        });
    }
    private static void countSingleFile(File file)throws FileNotFoundException{
        Scanner sc = new Scanner(file);
        Matcher matcher;
        while (sc.hasNextLine()){
            String newLine = sc.nextLine().trim();
            matcher = pattern.matcher(newLine);
            if (matcher.find()){
                String importedClass = matcher.group(1);
//                System.out.println(importedClass);
                if (importedClass.endsWith("*")){
                    continue;
                }else{
                    if (fileCountMap.containsKey(importedClass)){
                        fileCountMap.put(importedClass, fileCountMap.get(importedClass) + 1);
                    }else{
                        fileCountMap.put(importedClass, 1);
                    }
                }
            }
        }
    }
    private static void handleJavaFile(File destination){
        if (destination.isDirectory()){
            for (File item: destination.listFiles()){
                handleJavaFile(item);
            }
        }else{
            fileVec.add(destination);
        }
    }
    public static void countTop10(String path){
        fileCountMap = new HashMap<>();
        fileVec = new Vector<>();
        File dir = new File(path);
        if (dir.exists()){
            handleJavaFile(dir);
            try {
                for (File item : fileVec) {
                    countSingleFile(item);
                }
                sort();
                printTop10();
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }else{
            System.out.println("The file or directory does not exist!");
            return;
        }
    }
}
