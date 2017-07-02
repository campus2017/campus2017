import java.util.*;

/**
 * Created by Administrator on 2016/12/20.
 */
class GetSortedMap{
    public  ArrayList<Map.Entry<String,Integer>> getSortedMap(HashMap<String, Integer> records){
        ArrayList<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(records.entrySet());
        Collections.sort(list,new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o2.getValue() - o1.getValue());
            }
        });
        return list;
    }
}
