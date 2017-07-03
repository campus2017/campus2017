import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by liuliling on 2016/11/13.
 */
public class CountMostImport {
//1读取源目录
//2对每一个文件进行读取
//3 用hashmap存取类名和类的次数
// 4 输出前十个类名

    public static void getPathFiles(String filePath,List<File> fileList){    //递归读取目录，将文件放在文件列表中
        File root = new File(filePath);
        if(root.isDirectory())
        {
            File[] files = root.listFiles();
            for(File file:files)
                getPathFiles(file.getAbsolutePath(), fileList);

        }
        else{
            boolean suffix= root.getName().endsWith(".java");    //判断文件是否为Java文件
            if(suffix)
                fileList.add(root);

        }
    }
    public static void fileToMap(File file,HashMap<String,Integer> importMap) {     //读取文件，将类映射到map中
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));//读取文件
            String str;
            while ((str = in.readLine())!= null) {
                String []words=str.split("\\s");
                if (words[0].trim().equals("import")) {
                    String classname = words[1];
                    if (importMap.isEmpty()||!importMap.containsKey(classname)) {
                        importMap.put(classname, 1);
                    } else {
                        int count = importMap.get(classname);
                        importMap.put(classname, count + 1);
                    }
                }else if(words[0].trim().equals("public")){
                    // System.out.println(str);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<String> Top10Import(HashMap<String,Integer> importMap)           //按导入多少排列导入类
    {   List<Map.Entry<String,Integer>>	list=new ArrayList<>(importMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });
        List<String> result=new ArrayList<>();int i=0;
        for(Map.Entry<String,Integer> map:list){
            if(i<10) {
                result.add(map.getKey());
                // System.out.println(map.getKey()+"........."+map.getValue());
            }else
                break;
            i++;
        }
        return result;
    }
    public static void print(List<String> list){  //打印出结果列表
        for (String str:list)
        {
            System.out.println(str);
        }
    }
    public static void main(String[] args) {
        // Scanner sc=new Scanner(System.in);
        // String path=sc.next();
        String path="./src";
        HashMap <String,Integer> importMap=new HashMap<>();
        List<File> fileList=new ArrayList<>();
        getPathFiles(path,fileList);
        for(File file:fileList)
        {  fileToMap(file,importMap);
        }
        List<String> res=Top10Import(importMap);
        print(res);
    }
}



