package com.qunar.main;

import com.qunar.util.GetFiles;
import com.qunar.vo.FileCodeLineVo;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by DELL on 2017/5/3.
 * Description:统计一个java文件的有效行数，输入文件路径进行查询，本案例将文件放在工程目录下的FileFolder文件夹下
 * 在输入文件目录时，直接输入FileFolder/test.java即可进行查询
 */
public class Main {

	public static void main(String[] args) throws FileNotFoundException{

		List<FileCodeLineVo> list = new ArrayList<>();
		System.out.println("请输入要统计有效行数的java文件（比如FileFolder/test.java,或是FileFolder文件夹）：");
		Scanner scanner = new Scanner(System.in);
		String filepath = scanner.nextLine();//获取文件路径

		Logger logger = Logger.getLogger(Main.class);//打印日志
		logger.info("程序开始运行");

		File file = new File(filepath);
		List<FileCodeLineVo> totalList = new ArrayList<FileCodeLineVo>();
		totalList= new GetFiles().transfer(file,totalList);

		System.out.println("统计结果：");
		for(FileCodeLineVo fclv:totalList){
			System.out.println("统计的文件是："+fclv.getFilename());
			System.out.println("空行数  ："+fclv.getBlankline());
			System.out.println("注释行数："+fclv.getAnnotationline());
			System.out.println("有效行数："+fclv.getValidcodeline());
			System.out.println("总行数  ："+fclv.getTotalline());
		}
		logger.info("程序运行结束");
	}
}
