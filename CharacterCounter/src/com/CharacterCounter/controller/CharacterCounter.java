package com.CharacterCounter.controller;

import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class CharacterCounter {
	static String ch = "";
	static String[] a = new String[3];
	static int[] a1 = new int[3];

	@RequestMapping("/CharacterCounter")
	public String hello() {
		return "CharacterCounter";
	}

	@RequestMapping(value = "/CharacterCounter1", method = RequestMethod.POST)
	
	public String login(String text1,@RequestParam("upload") MultipartFile upload, Model model) throws IOException {
		//System.out.println("文件上传：");
		
		if(!upload.isEmpty()){
			//System.out.println(upload.getName());
	        byte[] bs= upload.getBytes() ;
	        String aaa=new String(bs);
	        //System.out.println(new String(bs));
		if (aaa!=null) {
			String dest = "";
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(aaa);
			dest = m.replaceAll("");
			text1=dest;
			//System.out.println("文件内容为：" +text1);
		}
		}
		ch="";
		a=new String[3];
		a1=new int[3];
		int[] arr1 = new int[4];
		arr1 = count(text1);
		for (int i = 0; i < 4; i++) {
			//System.out.println("返回数组为：" + arr1[i]);
		}
		model.addAttribute("chinese", arr1[0]);
		model.addAttribute("english", arr1[1]);
		model.addAttribute("number", arr1[2]);
		model.addAttribute("others", arr1[3]);
		if (!ch.equals("")) {
			countToZH(ch, 3);
			ArrayList<String> list = new ArrayList<String>();
			if ((a1[0] == a1[1]) && (a1[2] == a1[1])) {
				list.add(a[0]);
				list.add(a[1]);
				list.add(a[2]);
				Collections.sort(list, new Comparator<String>() {
					public int compare(String o1, String o2) {
						Comparator<Object> com = Collator
								.getInstance(java.util.Locale.CHINA);
						return com.compare(o1, o2);

					}
				});
				model.addAttribute("chinese1", list.get(0));
				model.addAttribute("chinese1Number", a1[0]);
				model.addAttribute("chinese2", list.get(1));
				model.addAttribute("chinese2Number", a1[0]);
				model.addAttribute("chinese3", list.get(2));
				model.addAttribute("chinese3Number", a1[0]);
			} else if (a1[0] == a1[1]) {
				list.add(a[0]);
				list.add(a[1]);
				Collections.sort(list, new Comparator<String>() {
					public int compare(String o1, String o2) {
						Comparator<Object> com = Collator
								.getInstance(java.util.Locale.CHINA);
						return com.compare(o1, o2);

					}
				});
				model.addAttribute("chinese1", list.get(0));
				model.addAttribute("chinese1Number", a1[0]);
				model.addAttribute("chinese2", list.get(1));
				model.addAttribute("chinese2Number", a1[0]);
				model.addAttribute("chinese3", a[2]);
				model.addAttribute("chinese3Number", a1[2]);
			} else if ((a1[2] == a1[1])) {
				list.add(a[1]);
				list.add(a[2]);
				Collections.sort(list, new Comparator<String>() {
					public int compare(String o1, String o2) {
						Comparator<Object> com = Collator
								.getInstance(java.util.Locale.CHINA);
						return com.compare(o1, o2);

					}
				});
				model.addAttribute("chinese1", a[0]);
				model.addAttribute("chinese1Number", a1[0]);
				model.addAttribute("chinese2", list.get(0));
				model.addAttribute("chinese2Number", a1[1]);
				model.addAttribute("chinese3", list.get(1));
				model.addAttribute("chinese3Number", a1[1]);
			} else {
				model.addAttribute("chinese1", a[0]);
				model.addAttribute("chinese1Number", a1[0]);
				model.addAttribute("chinese2", a[1]);
				model.addAttribute("chinese2Number", a1[1]);
				model.addAttribute("chinese3", a[2]);
				model.addAttribute("chinese3Number", a1[2]);
			}

		}
		return "CharacterCounter";
	}

	public static int[] count(String str) {
		int[] arr = new int[4];
		String E1 = "[\u4e00-\u9fa5]";// 中文
		String E2 = "[a-zA-Z]";// 英文
		String E3 = "[0-9]";// 数字

		int chineseCount = 0;
		int englishCount = 0;
		int numberCount = 0;

		String temp;
		for (int i = 0; i < str.length(); i++) {
			temp = String.valueOf(str.charAt(i));
			if (temp.matches(E1)) {
				chineseCount++;
				ch += temp;
			}
			if (temp.matches(E2)) {
				englishCount++;
			}
			if (temp.matches(E3)) {
				numberCount++;
			}
		}
		arr[0] = chineseCount;
		arr[1] = englishCount;
		arr[2] = numberCount;
		arr[3] = str.length() - arr[0] - arr[1] - arr[2];
		//System.out.println(chineseCount + "---" + englishCount + "---"
		//		+ numberCount + "---" + arr[3]);
		return arr;
	}

	public static void countToZH(String str, int index) {
		// 去掉中间包含的空格、中文逗号、中文句号
		// str = str.replace(" ", "").replace("，", "").replace("。", "");
		// 定义返回数组
		//String[] re_str = new String[index];
		// 文本转换为字符数组
		char[] chs = str.toCharArray();
		// 定义ArrayList对象存储汉字
		ArrayList<String> array = new ArrayList<String>();
		for (char ch : chs) {
			array.add(String.valueOf(ch));
		}

		// 定义Map集合存储汉字，键为汉字不重复，值为统计的数量
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		// 遍历字符数组，获取到每一个字符
		for (String tstr : array) {
			// 用每一个字符作为键，在TreeMap中查找
			Integer val = map.get(tstr);
			if (val == null) {
				// 返回null，则不存在，存储1
				map.put(tstr, 1);
			} else {
				// 返回非null，则把值加1，重新存储
				val++;
				map.put(tstr, val);
			}
		}

		// key value拼接后存在TreeSet中会自动排序，将value与key拼接key在前边
		TreeSet<String> sortSet = new TreeSet<String>();
		// 获取键值对的Set集合
		Set<Map.Entry<String, Integer>> sme = map.entrySet();
		// 遍历拼接
		for (Map.Entry<String, Integer> me : sme) {
			String s = me.getValue().toString() + me.getKey();
			sortSet.add(s);
		}

		// 获取后出现次数最多的index个单词，带有出现次数
		int o = sortSet.size();
//		System.out.println(o);
		// 记数
		int c = 0;
		for (int i = o - index; i < sortSet.size();) {
			String te = sortSet.last();
			sortSet.remove(te);
//			String temp = (o - sortSet.size()) + "："
//					+ te.replaceAll("[^\\d]", "") + "  "
//					+ te.replaceAll("[\\d+]", "");
//			re_str[c] = temp;
			a1[c] = Integer.parseInt(te.replaceAll("[^\\d]", ""));
			a[c] = te.replaceAll("[\\d+]", "");
			c++;
		}
//		for (int i = 0; i < index; i++) {
//			(re_str[i]);
//		}
	}
}
