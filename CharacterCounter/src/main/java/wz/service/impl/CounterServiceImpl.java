package wz.service.impl;

import org.springframework.stereotype.Service;
import wz.service.CounterService;

import java.util.*;

/**
 * CounterServiceImpl
 *
 * @author wz
 * @date 16/12/23 16:03
 */
@Service
public class CounterServiceImpl implements CounterService {


    public Map<String, Object> parseText(String text) {

        text = " " + text + " ";

        //language=RegExp
        String regex = "[\u4e00-\u9fa5]";// 中文
        int chCount = text.split(regex).length - 1;

        regex = "[a-zA-Z]";// 英文
        int enCount = text.split(regex).length - 1;

        regex = "[0-9]";// 数字
        int numCount = text.split(regex).length - 1;

        regex = "[\\pP]";//标点符号
        int punctCount = text.split(regex).length - 1;

        HashMap<String, Object> countMap = new HashMap<String, Object>();
        countMap.put("chCount", chCount);
        countMap.put("enCount", enCount);
        countMap.put("numCount", numCount);
        countMap.put("punctCount", punctCount);
        return countMap;
    }

    public Map<String, Object> topKCharacter(String text, int k) {
        Integer count;
        HashMap<String, Integer> countMap = new HashMap<String, Integer>();
//        Character charTemp;
        String stringTemp;

        for (int i = 0; i < text.length(); i++) {
            stringTemp = Character.toString(text.charAt(i));
            if (stringTemp.equals("\n"))
                stringTemp = "\\n";
            else if (stringTemp.equals("\t"))
                stringTemp = "\\t";
            else if (stringTemp.equals("\r"))
                stringTemp = "\\r";
            else if (stringTemp.equals(" "))
                stringTemp = "空格";

            if ((count = countMap.get(stringTemp)) != null)
                countMap.put(stringTemp, count + 1);
            else
                countMap.put(stringTemp, 1);
        }
        List<Map.Entry<String, Integer>> list = new ArrayList(countMap.entrySet());
        String maxChar;
        int max, tmp;
        for (int i = 0; i < k && i<list.size(); i++) {
            maxChar = list.get(0).getKey();
            max = list.get(0).getValue();
            tmp = 0;
            for (int j = 1; j < list.size() - i; j++) {
                if (list.get(j).getValue() > max || (list.get(j).getValue() == max && list.get(j).getKey().compareTo(maxChar) < 0)) {
                    maxChar = list.get(j).getKey();
                    max = list.get(j).getValue();
                    tmp = j;
                }
            }
            swap(list, tmp, list.size() - i - 1);
        }
        list = list.size() >= 3 ? list.subList(list.size() - k, list.size()) : list;
        Collections.reverse(list);
        HashMap<String, Object> res = new HashMap<String, Object>();
        for (int i = 0; i < list.size(); i++) {
            res.put("top" + (i + 1) + "Char", list.get(i).getKey());
            res.put("top" + (i + 1) + "Count", list.get(i).getValue());
        }
        return res;
    }

    private void swap(List list, int i, int j) {
        Object tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }

}
