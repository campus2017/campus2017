package com.utils;

import java.util.*;

/**
 * Created by Lee on 2017/6/28.
 */
public class Sort {
//    public static <K, V extends Comparable<? super V>> Map<K, V>
public static List<Object>
    sortByValue( Map<Character, Integer> map )
    {
        List<Map.Entry<Character, Integer>> list =
                new LinkedList<Map.Entry<Character, Integer>>( map.entrySet() );
        Collections.sort( list, new Comparator<Map.Entry<Character, Integer>>()
        {
            public int compare( Map.Entry<Character, Integer>o1, Map.Entry<Character, Integer> o2 )
            {
                return (o1.getValue()).compareTo( o2.getValue() );
            }
        } );

        Map<Character, Integer> result = new LinkedHashMap<Character, Integer>();
        List<Object> rlist = new ArrayList<Object>();
        for (Map.Entry<Character, Integer>entry : list)
        {
//            result.put( entry.getKey(), entry.getValue() );
            rlist.add(entry.getKey());
            rlist.add(entry.getValue().toString());
        }
        List<Object> rsult = new ArrayList<Object>();
        for(int i=rlist.size()-1;i>rlist.size()-7;i--){
            rsult.add(rlist.get(i));
        }
        return rsult;
    }
}
