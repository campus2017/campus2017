package CountMostImport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class CountMostImport {
    /**
     * 三、根据指定项目目录下（可以认为是 java 源文件目录）中，统计被 import 最多的类，前十个是什么。（作
     * 业命名为：CountMostImport）
     */
    public static void main(String[] args) {
        String path = "E:\\MyProject\\GitProject\\ll\\src\\main\\java";
        File file = new File(path);

        Map<String, Integer> map = new HashMap<>();

        calTop10(file, map);

        List<Map.Entry> arrayList = new ArrayList(map.entrySet());
        Collections.sort(arrayList, new Comparator() {
            public int compare(Object o1, Object o2) {
                Map.Entry obj1 = (Map.Entry) o1;
                Map.Entry obj2 = (Map.Entry) o2;
                return ((Integer) obj2.getValue()).compareTo((Integer) obj1.getValue());
            }
        });
        int c = 10 < arrayList.size()?arrayList.size():10;
        for (int i = 0; i < c; i++) {
            System.out.println(arrayList.get(i).getKey() + ":" + arrayList.get(i).getValue());
        }

    }


    private static void calTop10(File file, Map<String, Integer> map) {
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                System.out.println("空文件夹");
                return;
            } else {
                for (File f : files) {
                    if (f.isDirectory()) {
                        calTop10(f, map);
                    } else {
                        cal(f, map);
                    }
                }
            }
        } else {
            System.out.println("文件不存在");
        }
    }

    private static void cal(File file, Map<String, Integer> map) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().startsWith("import")) {
                    String key = line.replace("import", "").replace(";", "").trim();
                    Integer count = map.get(key);
                    if (count != null) {
                        map.put(key, ++count);
                    } else {
                        map.put(key, 1);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("读取文件出错");
            e.printStackTrace();
        }
    }
}
