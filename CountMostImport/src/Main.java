
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {
        // write your code here
        String basePath = "./src";
        Map<String, Integer> map = analysisFiles(basePath);
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        int i = 0;
        for (Map.Entry<String, Integer> entryList : list) {
            if (i < 10) {
                System.out.println((i+1)+".  "+entryList.getKey() + " | " + entryList.getValue());
            }else{
                break;
            }
            i++;
        }


    }
    //递归得到目录下的所有文件的Map<String,Integer>
    public static Map analysisFiles(String path) throws IOException {
        Map<String, Integer> map = new HashMap<>();
        File file = new File(path);
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                map.putAll(analysisFiles(files[i].getPath()));
            } else {
                map.putAll(getImport(files[i].getPath()));
            }
        }
        return map;
    }
    //得到文件中import的类的Map<String,Integer>
    public static Map getImport(String path) throws IOException {
        Map<String, Integer> map = new HashMap<>();
        File file = new File(path);
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(file));
        String tempStr;
        while ((tempStr = reader.readLine()) != null) {
            if (Pattern.matches("^(import).+;$", tempStr)) {
                tempStr = tempStr.replace("import", "");
                tempStr = tempStr.trim().replace(";", "");
                if (!map.containsKey(tempStr)) {
                    map.put(tempStr, 1);
                } else {
                    map.put(tempStr, map.get(tempStr) + 1);
                }
            }
        }
        return map;
    }
}
