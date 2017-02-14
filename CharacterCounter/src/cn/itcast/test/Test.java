package cn.itcast.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import cn.itcast.po.MyEntry;

public class Test {
	public static void main(String[] args) {
		String str = "aabbc";


		int englishLet = 0;
		int number = 0;
		int chineseChar = 0;
		int punctuation = 0;
		// TODO Auto-generated method stub
		char[] charArray = str.toCharArray();
		Map<Character, Integer> map = new TreeMap<Character, Integer>();
		for (int i = 0; i < str.length(); i++) {
			char temp = charArray[i];
			if (map.containsKey(temp))
				map.put(temp, map.get(temp) + 1);
			else
				map.put(temp, 1);

			if (temp >= 'a' && temp <= 'z' || temp >= 'A' && temp <= 'Z')
				englishLet++;
			else if (temp >= '0' && temp <= '9')
				number++;
			else if (Character.toString(charArray[i]).matches(
					"[\\u4E00-\\u9FA5]+")) {
				chineseChar++;
			} else
				punctuation++;
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

		@SuppressWarnings("unused")
		Map<String, Integer> result = new HashMap<String, Integer>();

		List<Map.Entry<String, Integer>> resultList = new ArrayList<Map.Entry<String, Integer>>();

		resultList.add(new MyEntry<String, Integer>("englishLet", englishLet));
		resultList.add(new MyEntry<String, Integer>("number", number));
		resultList
				.add(new MyEntry<String, Integer>("chineseChar", chineseChar));
		resultList
				.add(new MyEntry<String, Integer>("punctuation", punctuation));

		for (int i = 0; i < 3 && i < list.size(); i++) {
			resultList.add(new MyEntry<String, Integer>(Character.toString(list
					.get(i).getKey()), list.get(i).getValue()));
		}
	
		System.out.println(resultList);
	}
}
