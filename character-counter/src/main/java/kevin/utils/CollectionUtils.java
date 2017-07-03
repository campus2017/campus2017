package kevin.utils;

import java.util.Map;

/**
 * Created by Kevin on 2017/6/25.
 */
public class CollectionUtils {
    /**
     * 增加一个字符到map,并更新出现的数量
     * @param map
     * @param c
     */
    public static void addToMap(Map<Character, Integer> map, Character c) {
        if (map == null) return;
        Integer n = 0;
        if ((n = map.get(c)) != null) {
            map.put(c,n+1);
        }else {
            map.put(c,1);
        }

    }
}
