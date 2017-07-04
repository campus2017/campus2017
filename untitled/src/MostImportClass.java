import java.io.*;
import java.util.*;

import static javax.swing.UIManager.getInt;

public class MostImportClass {
    String dirName;
    HashMap<String, Integer> importClassRecords;

    public MostImportClass(String dir) {
        this.dirName = dir;
        importClassRecords = new HashMap<String, Integer>();
        this.statisticsClazz(new File(this.dirName));
    }

    public int get(String clazzName) {
        Integer value = importClassRecords.get(clazzName);
        if (value == null) return 0;
        return value;
    }

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
                    String className = line.substring(6, line.length() - 1).trim();
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

    //对Map按值进行排序
    public Map<String, Integer> sortMapByValue(Map<String, Integer> oriMap) {
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        if (oriMap != null && !oriMap.isEmpty()) {
            List<Map.Entry<String, Integer>> entryList = new ArrayList<Map.Entry<String, Integer>>(oriMap.entrySet());
            Collections.sort(entryList,
                    new Comparator<Map.Entry<String, Integer>>() {
                        public int compare(Map.Entry<String, Integer> entry1,
                                           Map.Entry<String, Integer> entry2) {
                            int value1 = 0, value2 = 0;
                            try {
                                value1 = getInt(entry1.getValue());
                                value2 = getInt(entry2.getValue());
                            } catch (NumberFormatException e) {
                                value1 = 0;
                                value2 = 0;
                            }
                            return value2 - value1;
                        }
                    });
            Iterator<Map.Entry<String, Integer>> iter = entryList.iterator();
            Map.Entry<String, Integer> tmpEntry = null;
            while (iter.hasNext()) {
                tmpEntry = iter.next();
                sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
            }
        }
        return sortedMap;
    }

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

    public String getMostImportClazzName() {
        int max = Integer.MIN_VALUE;
        String maxClazzName = null;
        //排序
        Map sortedMap = sortMapByValue(importClassRecords);
        //取得最大以及前十
        int i = 0;
        for (Map.Entry item : this.importClassRecords.entrySet()) {
            if (i < 10) {
                if (i == 0) {
                    System.out.println("被import最多的类是：" + (String) item.getKey());
                    System.out.println("被import最多的类的次数是：" + (Integer) item.getValue());
                } else {
                    System.out.println("被import排名第：" + (i + 1) + "的是：" + (String) item.getKey() + "被import的次数是" + (Integer) item.getValue());
                }
                i++;
            }
        }

    }
}





