package com.lrz.work.service;

import com.lrz.work.pojo.TongjiResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TongjiServiceImpl implements TongjiService {

	public TongjiResult tongji(String content) {
		if ("".equals(content)) {
			return new TongjiResult().setCharCount(0l).setChineseCount(0).setEnglishCount(0).setError(200)
					.setNumberCount(0).setTop3(new Character[] { ' ', ' ', ' ' }).setValue3(new long[] { 0, 0, 0 });
		}

		TongjiResult tResult = new TongjiResult();
		HashMap<Character, Long> map = new HashMap<Character, Long>();
		for (int i = 0; i < content.length(); i++) {
			if (!map.containsKey(content.charAt(i))) {
				map.put(content.charAt(i), 1l);
			} else {
				map.put(content.charAt(i), map.get(content.charAt(i)) + 1);
			}
		}

		Character[] tops = top3(map);
		tResult.setTop3(tops).setValue3(new long[] { map.get(tops[0]), map.get(tops[1]), map.get(tops[2]) });

		HashMap<String, Long> map2 = new HashMap<String, Long>();
		map2.put("english", 0l);
		map2.put("chinese", 0l);
		map2.put("number", 0l);
		map2.put("char", 0l);

		for (char c : map.keySet()) {
			String[] reg = { "[a-zA-Z]", "[\\pN]", "[\\u4e00-\\u9fa5]", "[\\pP]" };
			String s = Character.toString(c);
			for (int i = 0; i < reg.length; i++) {
				Pattern pattern = Pattern.compile(reg[i]);
				Matcher matcher = pattern.matcher(s);
				if (matcher.matches()) {
					switch (i) {
					case 0:
						map2.put("english", map2.get("english") + map.get(c));
						break;
					case 1:
						map2.put("number", map2.get("number") + map.get(c));
						break;
					case 2:
						map2.put("chinese", map2.get("chinese") + map.get(c));
						break;
					case 3:
						map2.put("char", map2.get("char") + map.get(c));
						break;

					}
				}
			}
		}
		tResult.setEnglishCount(map2.get("english")).setNumberCount(map2.get("number"))
				.setChineseCount(map2.get("chinese")).setCharCount(map2.get("char")).setError(200);
		return tResult;
	}

	private Character[] top3(final HashMap<Character, Long> map) {
		Character tops[] = new Character[3];
		int i = -1;

		for (char c : map.keySet()) {
			i++;
			if (i < 3) {
				tops[i] = c;
			} else {
				Arrays.sort(tops, new Comparator<Character>() {

					public int compare(Character o1, Character o2) {
						// TODO Auto-generated method stub
						return (int) (map.get(o2) - map.get(o1));
					}
				});

				System.out.println(tops[0] + "," + tops[1] + "," + tops[2]);
				if (map.get(c) > map.get(tops[2])) {
					tops[2] = c;
				}
			}
		}

		return tops;

	}

	public TongjiResult tongji(MultipartFile file) throws Exception {
		// TODO Auto-generated method stub
		InputStream stream = file.getInputStream();
		InputStreamReader reader = new InputStreamReader(stream);
		BufferedReader buffer = new BufferedReader(reader);
		String temp = "";
		String content = "";
		while ((temp = buffer.readLine()) != null) {
			content += temp;
		}

		return tongji(content);
	}

}
