package com.QunarTask;

import java.io.*;
import java.util.*;

public class Main {

    private Map<String, Integer> map = new HashMap<String, Integer>();

    public static void main(String[] args) {
        Main main = new Main();
        System.out.println("请输入要统计的java源文件目录路径：");
        Scanner in = new Scanner(System.in);
        String path = in.nextLine();
        //String path = "E://Workspace//CountMostImport//src//com//QunarTask";
        main.getFile(path);
        String result= main.getMostImportTopTen();
        System.out.println("被import最多的类为:"+result);
    }

    private void getFile(String filePath) {
        File file = new File(filePath);
        DepthSearch(file);
    }

    //递归对文件夹及文件进行处理
    private String DepthSearch(File f) {
        if (f.isDirectory()) {
            File[] files = f.listFiles();
            if (files != null) {
                for (File file : files) {
                    DepthSearch(file);
                }
            }
        } else {
            try {
                findNumberOfImport(f);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void findNumberOfImport(File f) throws Exception {
        InputStream in = new FileInputStream(f);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String str = "";
        while ((str = reader.readLine()) != null) {
            if(str.startsWith("package")||"".equals(str)){
                continue;
            }
            if (str.startsWith("import")) {
                str = str.replaceAll("\\s*", "");
                str = str.replaceAll(";", "");
                str = str.substring(6);
                Integer value = map.get(str);
                if(value==null){
                    map.put(str, 1);
                }else{
                    map.put(str, value+1);
                }
            } else {
                break;
            }
        }
        reader.close();
    }

    //得到被import最多的十个类，并且返回被import最多的类
    private String getMostImportTopTen(){
        map=sortMapByValue(map);
        Iterator<String> iter = map.keySet().iterator();
        String result = "";
        int topNum=10;
        while(iter.hasNext()){
            String  key = iter.next();
            if(topNum>0) {
                if(topNum==10) {
                    result=key;
                    System.out.println(key + "-->" + map.get(key));
                    topNum--;
                }
                else{
                    System.out.println(key + "-->" + map.get(key));
                    topNum--;
                }
            }
        }
        return result;
    }

    //以Map的值为基准进行排序
    public Map<String, Integer> sortMapByValue(Map<String, Integer> oriMap) {
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        if (oriMap != null && !oriMap.isEmpty()) {
            List<Map.Entry<String, Integer>> entryList = new ArrayList<Map.Entry<String, Integer>>(oriMap.entrySet());
            Collections.sort(entryList, new Comparator<Map.Entry<String, Integer>>() {
                        public int compare(Map.Entry<String, Integer> entry1,
                                           Map.Entry<String, Integer> entry2) {
                            int value1 = 0, value2 = 0;
                            try {
                                value1 = entry1.getValue();
                                value2 = entry2.getValue();
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

}
