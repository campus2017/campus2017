package xyz.yylai.assignment;

import java.io.*;
import java.util.*;

public class ImportedClassCounter {
    private File projectDirectory;
    private static void Traverse(File fileOrDirectory, Map<String, Integer> ClassCounts){
        if (fileOrDirectory.isDirectory()){
            File[] files = fileOrDirectory.listFiles();
            if(files==null){
                return;
            }
            for(File file : files){
                Traverse(file, ClassCounts);
            }
        }else{
            ParseFile(fileOrDirectory, ClassCounts);
        }
    }
    private static void ParseFile(File sourceFile, Map<String, Integer> ClassCounts){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(sourceFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        String line = null;
        try {
            while((line = reader.readLine())!=null){
                line = line.trim();
                if(line.startsWith("public")||line.startsWith("class")){
                    break;
                }
                if(line.startsWith("import")){
                    String className = line.substring(6,line.length()-1);
                    ClassCounts.merge(className, 1, Integer::sum);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static List<String> getKTop(int K, Map<String, Integer> ClassCounts){
        List<String> kTop = new LinkedList<>();
        if(K>=ClassCounts.size()){
            ClassCounts.forEach((k,v)->{
                kTop.add(k);
            });
        }else{
            Iterator<Map.Entry<String,Integer>> itr = ClassCounts.entrySet().iterator();
            PriorityQueue<String> priorityQueue = new PriorityQueue<String>(10,(class1,class2)->{
                int count1 = ClassCounts.getOrDefault(class1,0);
                int count2 = ClassCounts.getOrDefault(class2,0);
                if(count1<count2){
                    return -1;
                }else if(count1==count2){
                    return 0;
                }else{
                    return 1;
                }
            });
            while((K--)!=0){
                priorityQueue.add(itr.next().getKey());
            }
            while(itr.hasNext()){
                Map.Entry<String,Integer> entry = itr.next();
                if(entry.getValue()>ClassCounts.get(priorityQueue.peek())){
                    priorityQueue.add(entry.getKey());
                    priorityQueue.poll();
                }
            }
            String className = null;
            while((className = priorityQueue.poll())!=null){
                kTop.add(className);
            }

        }
        return kTop;
    }
    public static List<String> getKMostImportedClassName(File projectDirectory, int K){
        Map<String, Integer> ClassCounts = new HashMap<>();
        Traverse(projectDirectory,ClassCounts);
        return getKTop(K, ClassCounts);
    }
}

