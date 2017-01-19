package com.dj.imports;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 * @author DJ
 * 统计某个文件中引用最多的十个类
 * 1.需要获取到到文件目录
 * 2.统计每个文件中import的数量
 * 3.对获取到的import进行排序
 */

public class StatisticsImport {

	public static List<File> list=new ArrayList<File>();
	public static Map<String,Integer> map=new HashMap<String,Integer>();
	
	public static void main(String[] args) {
		
		System.out.println("请输入指定的路径:");
		Scanner scanner=new Scanner(System.in);
		String path=scanner.nextLine();//获取到指定的路径
		list=getFiles(path,list);//从指定的路径中获取所有的文件列表
		map=getSumImport(list,map);//将统计所有文件中的import的数量
		map=getSortResult(map);//对获取到的键值对进行排序
		printMapResult(map);//对键值对的结果打印输出
	}

	public static void printMapResult(Map<String, Integer> map) {
		
		Iterator iterator=map.entrySet().iterator();
		int count=1;
		while(iterator.hasNext()){
			Map.Entry<String,Integer> entry=(Map.Entry<String, Integer>) iterator.next();
			System.out.println(entry.getKey()+":"+entry.getValue());
			if(++count>10)
				break;//最多限制数量10个		
		}		
	}

	public static Map<String, Integer> getSortResult(Map<String, Integer> map) {
		//将map转换成集合的形式进行排序
		ArrayList<Map.Entry<String,Integer>> map_list=new ArrayList<Map.Entry<String,Integer>>(map.entrySet());
		Collections.sort(map_list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> arg0, Entry<String, Integer> arg1) {
				return arg1.getValue()-arg0.getValue();//按照从大到小的顺序排序
			}
		});
		
		//将集合转换成map的形式进行返回
		Map<String,Integer> m=new LinkedHashMap<String,Integer>();//该map是按照添加的先后顺序进行排序
		for(Map.Entry<String,Integer> maps:map_list){
			m.put(maps.getKey(),maps.getValue());			
		}		
		return m;//以键值对的形式返回
	}


	public static Map<String, Integer> getSumImport(List<File> list, Map<String, Integer> map) {
        BufferedReader  breader = null;
		for(File file:list){
			try {
				breader=new BufferedReader(new FileReader(file));
				String line=null;
				while((line=breader.readLine())!=null){
					if(line.startsWith("public")||line.startsWith("protected"))
						break;//此时说明已经跳过import需要结束程序
					if(line.startsWith("import")){
						String key=line.trim().substring(6,line.length()-1);//需要去掉import后面的空格和最后的分号
						if(map.containsKey(key)){//判断是否包含该引用类
							map.put(key, map.get(key)+1);
						}else{
							map.put(key, 1);
						}
					}
				}
				
			}catch (FileNotFoundException e) {				
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					breader.close();
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		}				
		return map;
	}

	public static List<File> getFiles(String path,List<File> list) {
		File file=new File(path);
		if(file.exists()){
			File[] files=file.listFiles();//获取到目录列表
			for(File f:files){
				if(f.isDirectory()){//判断仍然是一个目录
					getFiles(f.getAbsolutePath(),list);//获取到该目录的绝对路径，继续递归遍历
				}else{
					list.add(f);//判断是文件将其添加到文件集合中
				}
			}			
		}else{
			System.out.println("该路径下文件不存在");
		}				
		return list;
	}
		
}
