import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.*;


public class Main {
    String dirName;
    static Map<String, Integer> importClassRecords;
    HashMap<String, Integer> tongji = new HashMap<>();

    public static void main(String[] args) {
        Main main = new Main();
        System.out.println("请输入查询目录：");
        Scanner Scanner = new Scanner(System.in);
        String path = Scanner.nextLine();

        main.COUNT(path);
        //System.out.println(main.getMostImportClazzName());
        //输出前十个出现次数最多的
        ArrayList<Map.Entry<String, Integer>> entries = sortMap(importClassRecords);
        for (int i = 0; i < 10; i++) {
            System.out.println(entries.get(i).getKey());
        }
    }
    //统计所有结果到hashmap里
    public void COUNT(String dir) {
        this.dirName = dir;
        importClassRecords = new HashMap<String, Integer>();
        this.statisticsClazz(new File(this.dirName));
    }


    //根据import统计次数
    public void processFile(File file) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return;
        }
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("public") || line.startsWith("class")) {
                    break;
                }
                if (line.startsWith("import")) {
                    String className = line.substring(6, line.length() - 1)
                            .trim();
                    Integer value = importClassRecords.get(className);
                    if (value == null) {
                        importClassRecords.put(className, 1);
                    } else {
                        importClassRecords.put(className, value + 1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //递归文件目录统计文件
    public void statisticsClazz(File file) {
        if (!file.isDirectory()) {
            processFile(file);
        } else {
            File[] files = file.listFiles();
            for (File tmpFile : files) {
                statisticsClazz(tmpFile);
            }
        }

    }



    public static ArrayList<Map.Entry<String, Integer>> sortMap(Map map) {
        List<Map.Entry<String, Integer>> entries = new ArrayList<Map.Entry<String, Integer>>(
                map.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> obj1,
                               Map.Entry<String, Integer> obj2) {
                return obj2.getValue() - obj1.getValue();
            }
        });
        return (ArrayList<Map.Entry<String, Integer>>) entries;
    }
}
