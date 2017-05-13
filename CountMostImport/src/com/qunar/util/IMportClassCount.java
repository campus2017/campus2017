package com.qunar.util;

import com.google.common.collect.Multiset;
import java.io.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by DELL on 2017/5/3.
 * Description:接收GetFiles中文件，依次进行统计
 */
public class IMportClassCount {
	public Multiset<String> compute(File file,Multiset<String> set) throws FileNotFoundException {

		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		}catch (FileNotFoundException e){
			throw new FileNotFoundException(file + " 文件不存在！" + e);
		}

		//System.out.println(file.getName());
		//System.out.println(file.get);
		Pattern importPattern = Pattern.compile("((?<=import)(\\s)+(.+)(\\..+)+(?=;))|((?<=import).*?=.*?\".*?(?=\"))",Pattern.DOTALL);//匹配含有import的行
		Pattern deletePattern = Pattern.compile("=.*?\"");
		String line = null;
		try {
			while((line = bufferedReader.readLine())!= null){
				Matcher matcher = importPattern.matcher(line);
				if(matcher.find()){
					String str = matcher.group();
					Matcher deleteMatcher = deletePattern.matcher(str);
					if(deleteMatcher.find()){ //找到jsp页面里的import了，上一步只去掉了import和最后的"，此时再除去="
						str = str.replace(deleteMatcher.group(),"");
					}
					int flag = 0;
					if(str.contains(",")){
						String[] strs = str.split(",");
						flag = 1;
						for(String s:strs){ //此时对应的是jsp里的import，且用,分开的
							//System.out.println(file.getName()+"文件里："+s);
							set.add(s.trim());
						}
					}
					if(flag == 0){
						//System.out.println(file.getName()+"文件里："+str);
						set.add(str.trim());
					}

				}
			}
		}catch (IOException e){
			throw  new  RuntimeException("读取文件失败！"+e);
		}finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				throw new RuntimeException("关闭文件输入流失败！");

			}
		}
		return set;
	}
}
