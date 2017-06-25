package util.counter;

import java.util.*;

/**
 * Created by Administrator on 2016/11/23.
 */
public class ImportCounter {

    /**
     *
     * @param allImport 包含所有.java文件中的所有被引用的类，可重复
     * @return 存储 按照引用类次数从多到少排序的键值对 的List
     */
    public List<Map.Entry<String, Integer>> countImport(List<String> allImport){

        Map<String,Integer> map = new TreeMap<>();

        if (allImport.size() == 0){
            return null;
        }else{          //获得一个映射，此映射的key为被引用的类的名称，value为这个类被引用的总次数。
            for (int i = 0; i < allImport.size(); i++) {
                String imp = allImport.get(i);
                if (map.containsKey(imp)){
                    int value = map.get(imp) + 1;
                    map.put(imp,value);
                }else {
                    map.put(imp,1);
                }
            }

            List<Map.Entry<String, Integer>> list = sortMapByValue(map);

            return list;
        }
    }

    /**
     *
     * @param oriMap key为引用类，value为被引用次数的映射
     * @return 存储 按照引用类次数从多到少排序的键值对 的List
     */
    private List<Map.Entry<String, Integer>> sortMapByValue(Map<String, Integer> oriMap) {
        if (oriMap == null || oriMap.isEmpty()) {
            return null;
        }
        //将原始映射的键值对集合变为一个有序List
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(oriMap.entrySet());

        Collections.sort(entryList, new MapValueComparator());

        return entryList;
    }

    /**
     * 实现compare方法
     */
    private class MapValueComparator implements Comparator<Map.Entry<String, Integer>>{
        @Override
        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
            int x = o1.getValue();
            int y = o2.getValue();
            return (x < y) ? 1 : ((x == y) ? 0 : -1);
        }
    }
}
