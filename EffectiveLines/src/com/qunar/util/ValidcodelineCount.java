package com.qunar.util;

import com.qunar.vo.FileCodeLineVo;

import java.io.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by DELL on 2017/5/3.
 * Description:接收GetFiles中文件，依次进行统计
 */
public class ValidcodelineCount {
	public  List<FileCodeLineVo> compute(File file, List<FileCodeLineVo> totalList) throws FileNotFoundException {
		int blankLineNum = 0;
		int annotionLineNum = 0;
		int validLineNum = 0;
		int totalLineNum = 0;
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		}catch (FileNotFoundException e){
			throw new FileNotFoundException(file + " 文件不存在！" + e);
		}
		Pattern blankLinePattern = Pattern.compile("^\\s*$"); //匹配空行
		String regex1 = "//.+?\\n";// 匹配行注释
		String regex2 = "/\\*.+?\\*/";// 匹配块注释

		Pattern annotionPattern = Pattern.compile("(/\\*.*?(\\n)+?.*?\\*/)|(//)",Pattern.DOTALL);//匹配多行注释
		Pattern annotionPattern3 = Pattern.compile("(/\\*).*?\\*/",Pattern.DOTALL);//匹配多行注释
		Pattern annotionPatternForValid = Pattern.compile("(/\\*.+?\\*/)|(//.*)",Pattern.DOTALL);//对于有效代码行后出现的注释，用这个区匹配注释
		Pattern newlineCharcaterPattern = Pattern.compile("\\n");
		String line = null;
		StringBuffer stringBuffer = new StringBuffer();
		try {
			while((line = bufferedReader.readLine())!= null){
				stringBuffer.append(line+"\n");
				if(blankLinePattern.matcher(line).find()){ //匹配空行
					blankLineNum ++; //空行数加1
				}
				if(annotionPatternForValid.matcher(line).find()){ //匹配单行注释
					annotionLineNum ++; //注释行数加1
				}


				/**
				 * 如果将一个行中的注释都用""来替换，在去掉前后的空格后，如果不等于""，那么这一行就是有效行。
				 * 这一行在注释时也会被统计到。
				 */
				int flag = 0;
				Matcher matcher = annotionPatternForValid.matcher(line);
				while(matcher.find()){
					line = line.replace(matcher.group(),""); //将所有注释信息用""替换
					flag = 1; //匹配到含有注释的行
				}
				if(flag == 1){
					if(!line.trim().equals("")){ //表示此行是含有注释信息的有效行
						validLineNum ++; //含有注释的有效行数加1
					}
				}
				totalLineNum ++;  //总行数加1
			}

			/**
			 * 匹配多行注释
			 */
			Matcher annotionMatcher = annotionPattern3.matcher(stringBuffer.toString());
			while(annotionMatcher.find()){
				if(annotionMatcher.group().contains("\n")){ //如果这个字符串中含有\n，就表示它是多行注释
					//System.out.println("第几个:"+annotionMatcher.group());
					Matcher newLineMatcher = newlineCharcaterPattern.matcher(annotionMatcher.group());
					while(newLineMatcher.find()){
						annotionLineNum ++;
					}
					annotionLineNum ++;
				}
				/**
				 * 在上一步获取所有的注释行时，在注释中最后pipei到的字符是注释结束符，
				 * 并没有匹配到换行符，所应该给匹配到的所有匹配组在增加一个换行符
				 */

			}
			/**
			 * 计算有效行
			 * 去掉所有含有注释的行和空行，就剩下不含注释信息的有效行，再加上之前统计的含有注释的有效行，就是总有效行
			 */
			validLineNum = validLineNum + totalLineNum - blankLineNum - annotionLineNum;


			FileCodeLineVo fileCodeLineVo = new FileCodeLineVo();
			fileCodeLineVo.setFilename(file.getName());
			fileCodeLineVo.setAnnotationline(annotionLineNum);
			fileCodeLineVo.setBlankline(blankLineNum);
			fileCodeLineVo.setValidcodeline(validLineNum);
			fileCodeLineVo.setTotalline(totalLineNum);


			totalList.add(fileCodeLineVo);


		}catch (IOException e){
			throw  new  RuntimeException("读取文件失败！"+e);
		}finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				throw new RuntimeException("关闭文件输入流失败！");

			}
		}
		return totalList;
	}
}
