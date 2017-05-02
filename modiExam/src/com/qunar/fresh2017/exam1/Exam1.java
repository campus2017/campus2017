package com.qunar.fresh2017.exam1;

import java.io.*;
import java.util.*;

public class Exam1 {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        //相对路径方式不可取，因为发布的时候可能会找不到路径
        //File file=new File("src/gen/unorderedmsg.txt");
        InputStream in=Exam1.class.getResourceAsStream("/gen/unorderedmsg.txt");
        BufferedWriter bw=null;
        try {
            //BufferedReader br=new BufferedReader(new FileReader(file));
            BufferedReader br=new BufferedReader(new InputStreamReader(in));
            String base=Exam1.class.getResource("/").getFile();
            File count=new File(base+"count.txt");
            File output=new File(base+"orderedmsg.txt");
            Map<String,String> map=new TreeMap<String,String>();
            Map<String,Integer> map2=new TreeMap<String,Integer>();
            String line=null;
            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                String[] s=line.split("    ");
                map.put(s[1],line);
                if (map2.containsKey(s[0])) {
                    int temp=map2.get(s[0]);
                    map2.put(s[0],temp+1);
                } else {
                    map2.put(s[0],1);
                }
            }
            sortMapByKey(map);
            if (!output.exists()) {
                output.createNewFile();
            }
            bw=new BufferedWriter(new FileWriter(output));
            //清空orderedmsg中的内容
            bw.write("");
            //把排序好的内容写入到文件中
            for (Map.Entry<String, String> entry : map.entrySet()) {
                bw.append(entry.getValue()+"\n");
            }
            if (!count.exists()) {
                count.createNewFile();
            }
            bw.close();
            bw=new BufferedWriter(new FileWriter(count));
            bw.write("");
            for (Map.Entry<String, Integer> entry : map2.entrySet()) {
                bw.append(entry.getKey()+"    "+entry.getValue()+"\n");
            }
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用 Map按key进行排序
     * @param map
     * @return
     */
    public static Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        Map<String, String> sortMap = new TreeMap<String, String>(
                new MapKeyComparator());

        sortMap.putAll(map);

        return sortMap;
    }
}


class MapKeyComparator implements Comparator<String>{

    @Override
    public int compare(String str1, String str2) {

        return str1.compareTo(str2);
    }
}
