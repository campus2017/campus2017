package utils;

import java.util.*;

/**
 * Created by dayong.gao on 2016/12/8.
 */
public class MapUtil {
    /**
     * 对Map的Value进行排序，结果以List<Map.Entry>形式返回
     *
     * @param datamap 需要排序的Map
     * @return 返回排序后的数据
     */
    public static List<Map.Entry<String, Integer>> valuesort(Map<String, Integer> datamap) {
        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>();
        list.addAll(datamap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry obj1, Map.Entry obj2) {//从高往低排序
                if (Integer.parseInt(obj1.getValue().toString()) < Integer.parseInt(obj2.getValue().toString()))
                    return 1;
                if (Integer.parseInt(obj1.getValue().toString()) == Integer.parseInt(obj2.getValue().toString())) {
                    return obj1.getKey().toString().compareTo(obj2.getKey().toString()) ;
                }
                else
                    return -1;
            }
        });
        return list;
    }
}
