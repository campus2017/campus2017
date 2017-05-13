package com.qunar.main;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.qunar.util.GetFiles;
import com.qunar.vo.ImportClassVo;
import com.qunar.util.ImportClassTopK;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Created by DELL on 2017/5/4.
 * Description:程序主入口
 * 该类统计指定目录下，被import最多的类
 * 1、.java文件中import类
 * 2、.jsp文件中import类
 */
public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("请输入要统计目录（比如FileFolder）：");
		Scanner scanner = new Scanner(System.in);
		String filepath = scanner.nextLine();//获取文件路径

		Logger logger = Logger.getLogger(Main.class);//打印日志
		logger.info("程序开始运行");

		Multiset<String> set = HashMultiset.create();
		File file = new File(filepath);
		set= new GetFiles().transfer(file,set);
		//System.out.println("统计结果：");
		List<ImportClassVo> listVo = new ArrayList<ImportClassVo>();
		ImportClassVo importClassVo = null;
		for(String s:set.elementSet()){
			importClassVo = new ImportClassVo();
			importClassVo.setClassName(s);
			importClassVo.setNum(set.count(s));
			listVo.add(importClassVo);
		}
		ImportClassVo[] importClassVoArr = listVo.toArray(new ImportClassVo[listVo.size()]);
		List<ImportClassVo> listTopK = new ArrayList<ImportClassVo>();

		listTopK = new ImportClassTopK().topK(importClassVoArr,10);
		System.out.println("出现次数最多的前10个类是：");
		for(ImportClassVo vo:listTopK){
			System.out.println(vo.getClassName()+"                 出现次数："+vo.getNum());
		}

		logger.info("程序运行结束");
	}

}
