package homework.CountMostImport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class CountMostImport {

    public static void main(String[] args) {
        String path = args[0];

        File file = new File(path);
        Map<String, Integer> map = new HashMap();
        traversalFile(file, map);

        List<Map.Entry<String, Integer>> entryList = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        //降序排序
        Collections.sort(entryList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                int v1 = o1.getValue();
                int v2 = o2.getValue();
                return v2 - v1;
            }
        });
        int count = entryList.size() > 10 ? 10 : entryList.size();
        for (int i = 0; i < count; i++) {
            System.out.println(entryList.get(i).getKey() + " : " + entryList.get(i).getValue());
        }
    }

    private static void traversalFile(File file, Map<String, Integer> map) {
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                System.out.println("空");
                return;
            } else {
                for (File file1 : files) {
                    if (file1.isDirectory()) {
                        traversalFile(file1, map);
                    } else {
                        top10(file1, map);
                    }
                }
            }
        } else {
            System.out.println("文件不存在");
        }
    }

    private static void top10(File file, Map<String, Integer> map) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                if (line.startsWith("import")) {
                    line = line.replace("import", "").replace(";", "");
                    map.put(line, map.get(line) == null ? 1 : map.get(line) + 1);
                }
            }
        } catch (Exception e) {
            System.out.println("文件读取错误");
            e.printStackTrace();
        }
    }
}
