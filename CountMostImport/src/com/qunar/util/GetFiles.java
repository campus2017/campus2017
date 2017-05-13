package com.qunar.util;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;

/**
 * Created by DELL on 2017/5/3.
 * Description:接收输入的文件，判断是文件夹还是java文件，将其中所有的java文件放入list里，以此进行计算
 */
public class GetFiles {
	static int filecount = 0;//统计要查询的文件个数，有可能要查询的是文件夹

	File file;
	Multiset<String> set = HashMultiset.create();
	public Multiset<String>  transfer(File file, Multiset<String> set) throws FileNotFoundException {
		this.file = file;
		this.set = set;
		QueryFile(file,set);
		return set;
	}
	public static void QueryFile(File file, Multiset<String> set) throws FileNotFoundException {
		if(file == null || !file.exists()){
			throw new FileNotFoundException(file + "文件不存在");
		}
		filecount ++;
		if (file.isDirectory()){
			File[] files = file.listFiles(new FileFilter() {
				@Override
				public boolean accept(File pathname) {
					return pathname.getName().endsWith(".java") || pathname.getName().endsWith(".jsp")||pathname.isDirectory();
				}
			});
			for(File query_file:files){
				QueryFile(query_file,set);
			}
		}else{
			set = new IMportClassCount().compute(file,set);
		}

	}
}
