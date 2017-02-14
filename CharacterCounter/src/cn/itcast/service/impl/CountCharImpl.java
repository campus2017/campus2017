package cn.itcast.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import cn.itcast.service.CountChar;
import org.springframework.stereotype.Service;

@Service

public class CountCharImpl implements CountChar {
    static boolean isPunctuation(char ch){  
        if(isCjkPunc(ch)) return true;  
        if(isEnPunc(ch)) return true;  
          
        if(0x2018 <= ch && ch <= 0x201F) return true;     
        if(ch == 0xFF01 || ch == 0xFF02) return true;  
        if(ch == 0xFF07 || ch == 0xFF0C) return true;         
        if(ch == 0xFF1A || ch == 0xFF1B) return true;  
        if(ch == 0xFF1F || ch == 0xFF61) return true;   
        if(ch == 0xFF0E) return true;  
        if(ch == 0xFF65) return true;   

        return false;  
      }  
	  static boolean isEnPunc(char ch){  
	      if (0x21 <= ch && ch <= 0x22) return true;  
	    if (ch == 0x27 || ch == 0x2C) return true;  
	    if (ch == 0x2E || ch == 0x3A) return true;  
	    if (ch == 0x3B || ch == 0x3F) return true;  
	
	    return false;  
	  }  
	  static boolean isCjkPunc(char ch){  
	        if (0x3001 <= ch && ch <= 0x3003) return true;  
	        if (0x301D <= ch && ch <= 0x301F) return true;  
	
	        return false;  
	  } 
	  static Map<Character, Integer> addToMap(Map<Character, Integer> map, char ch){  
			if (map.containsKey(ch))
				map.put(ch, map.get(ch) + 1);
			else
				map.put(ch, 1);
			return map;
	  } 
	
	public Map<String, Integer> count(String str) {
		
		int englishLet = 0;
		int number = 0;
		int chineseChar = 0;
		int punctuation = 0;
		// TODO Auto-generated method stub
		char[] charArray = str.toCharArray();
		Map<Character, Integer> map = new TreeMap<Character, Integer>();
		for (int i = 0; i < str.length(); i++) {
			char temp = charArray[i];
			if (temp >= 'a' && temp <= 'z' || temp >= 'A' && temp <= 'Z'){
				englishLet++;
				addToMap(map, temp);
			}else if (temp >= '0' && temp <= '9'){
				number++;
				addToMap(map, temp);
			}else if (Character.toString(charArray[i]).matches(
					"[\\u4E00-\\u9FA5]+")) {
				chineseChar++;
				addToMap(map, temp);
			}else if (isPunctuation(temp)){
				punctuation++;
				addToMap(map, temp);
			}
		}

		List<Map.Entry<Character, Integer>> list = new ArrayList<Map.Entry<Character, Integer>>(
				map.entrySet());
		Comparator<Map.Entry<Character, Integer>> c1 = new Comparator<Map.Entry<Character, Integer>>() {
			public int compare(Entry<Character, Integer> o1,
					Entry<Character, Integer> o2) {
				// TODO Auto-generated method stub
				return o2.getValue() - o1.getValue();
			}
		};

		Comparator<Map.Entry<Character, Integer>> c2 = new Comparator<Map.Entry<Character, Integer>>() {
			public int compare(Entry<Character, Integer> o1,
					Entry<Character, Integer> o2) {
				// TODO Auto-generated method stub
				return o1.getKey() - o2.getKey();
			}
		};
		Collections.sort(list, c2);
		Collections.sort(list, c1);

		Map<String, Integer> result = new HashMap<String, Integer>();

		result.put("englishLet", englishLet);
		result.put("number", number);
		result.put("chineseChar", chineseChar);
		result.put("punctuation", punctuation);
		for (int i = 0; i < 3 && i < list.size(); i++) {
			result.put(Character.toString(list.get(i).getKey()), list.get(i)
					.getValue());
		}
		return result;
	}

}
