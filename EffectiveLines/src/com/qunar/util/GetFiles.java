package com.qunar.util;

import com.qunar.vo.FileCodeLineVo;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2017/5/3.
 * Description:接收输入的文件，判断是文件夹还是java文件，将其中所有的java文件放入list里，以此进行计算
 */
public class GetFiles {
	static int filecount = 0;//统计要查询的文件个数，有可能要查询的是文件夹

	File file;
	List<FileCodeLineVo> totalList = new ArrayList<FileCodeLineVo>();
	/*public void GetFiles(File file, List<FileCodeLineVo> totalList) throws FileNotFoundException {
		this.file = file;
		this.totalList = totalList;
		transfer(file,totalList);
	}*/
	public List<FileCodeLineVo> transfer(File file, List<FileCodeLineVo> totalList) throws FileNotFoundException {
		this.file = file;
		this.totalList = totalList;
		QueryFile(file,totalList);
		return totalList;
	}
	public static void QueryFile(File file,List<FileCodeLineVo> totalList) throws FileNotFoundException {
		if(file == null || !file.exists()){
			throw new FileNotFoundException(file + "文件不存在");
		}
		filecount ++;
		if (file.isDirectory()){
			File[] files = file.listFiles(new FileFilter() {
				@Override
				public boolean accept(File pathname) {
					return pathname.getName().endsWith(".java") || pathname.isDirectory();
				}
			});
			for(File query_file:files){
				QueryFile(query_file,totalList);
			}
		}else{
			totalList = new ValidcodelineCount().compute(file,totalList);
		}

	}
}
