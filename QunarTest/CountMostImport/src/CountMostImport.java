import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
/**
 * Created by Administrator on 2016/12/12.
 */
public class CountMostImport {

    static Map map = new HashMap<String,Integer>();

    public static void getCountMostImport(int MaxLine) throws Exception{
        List<File> files;
        files = DirectoryTool.recurseDirs(new File("D:\\JAVA\\IO"),".*\\.java");
        if(files!=null){
            for(File filename:files){
                BufferedReader in = new BufferedReader(new FileReader(filename));
                String s;
                while((s=in.readLine())!=null)
                    pattern(s);
            }
            sort(MaxLine);
        }
    }

    public static void pattern(String s){
//        以import开始后面可以有若干个空格以任意字符一个或多个结尾
        String regx = "^import\\s+.*";
        if(s.matches(regx)){
            if(map.containsKey(s)){
                map.put(s,(Integer)map.get(s)+1);
            }else{
                map.put(s,1);
            }
        }
    }
    public static void sort(int MaxLine){
        List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(map.entrySet());
        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>() {
            //降序排序
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        int i=0;
        for(Map.Entry<String,Integer> mapping:list){
//            打印前10个
            if(i<MaxLine){
                System.out.println(mapping.getKey()+":"+mapping.getValue());
            }
            i++;
        }
    }
}
