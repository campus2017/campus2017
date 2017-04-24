import java.io.*;
import java.util.*;

/**
 * Created by muhongfen on 17/4/24.
 * 根据制定项目目录下，统计被import最多的类，前十个是什么
 */
public class Main {
    public static void main(String[] args) throws IOException {
        List<File> fileList = getFiles("EffectiveLines/src",new ArrayList<File>());
        Map<String,Integer> map = getImportNum(fileList);
        Map<String,Integer> mapSorted =sortByvalue(map);
        printMap(mapSorted,10);
    }

    //获取路径下所有文件
    private static List<File> getFiles(String filepath,List<File> fileList){
        File file = new File(filepath);
        if(file.exists())
        {
            File[] files = file.listFiles();
            if(files.length>0) {
                for (File f : files){
                    if(f.isDirectory())
                        getFiles(f.getAbsolutePath(),fileList);
                    else fileList.add(f);
                }
            }
        }
        return fileList;
    }
    //获取import类及其数量
    private static Map<String,Integer> getImportNum(List<File> fileList) throws IOException {
        Map<String,Integer> map= new HashMap<String,Integer>();
        BufferedReader buffer = null;
        for(File f:fileList){
            try {
                buffer = new BufferedReader(new FileReader(f));
                String line ="";
                while((line = buffer.readLine())!=null){
                    if(line.startsWith("import")){
                        line = line.trim();
                        line = line.substring(6);
                        if(map.get(line)==null){
                            map.put(line,1);
                        }
                        else
                            map.put(line,map.get(line)+1);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
            return map;

    }
    //按照value对map进行排序
    private static Map<String,Integer> sortByvalue(Map<String,Integer> map){
        ArrayList<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {

            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });
        Map sortedMap = new LinkedHashMap();
        for(int i = 0;i<list.size();i++){
            sortedMap.put(list.get(i).getKey(),list.get(i).getValue());
        }
        return sortedMap;
    }
    //输出map
    private static void printMap(Map<String,Integer> map,int num){
        Iterator<Map.Entry<String,Integer>> iterator = map.entrySet().iterator();
        int i =0;
        while(iterator.hasNext()&&i<num){
            Map.Entry<String,Integer> entry = iterator.next();
            System.out.println("class="+entry.getKey()+"出现次数："+entry.getValue());
            i++;
        }

    }


}
