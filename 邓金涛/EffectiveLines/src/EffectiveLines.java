/*
 * dengjintao
 一、统计一个Java文件的有效行数。（作业命名：EffectiveLines）
1、有效不包括空行
2、不考虑代码见有多行注释的情况*/
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class EffectiveLines {
	static int code_count=0;//Java文件有效行数
	static int zhushi_count=0;//单行注释行数（不考虑多行）
	static int space_count=0;//空格
	static boolean flag = false;//
	public static void main(String[] args) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("D:\\eclipse\\eclipse-jee-mars\\workspace\\EffectiveLines\\src\\EffectiveLines.java"));
			String line=null;
			while((line = br.readLine()) != null)
			{pattern(line);}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("有效代码行数： " + code_count);
	}

	private static void pattern(String line) {
		//匹配空行
		String space = "\\s*";
		//匹配单行注释的如 /*..... */或/**.... */
		String zhushi1 = "\\s*/\\*{1,}.*(\\*/).*";
		//匹配单行注释如//....
		String zhushi2="\\s*//.*";
		if(line.matches(zhushi1)){
			zhushi_count++;
		}else if(line.matches(zhushi2)){
			zhushi_count++;
		}else if(line.matches(space)){
			space_count++;
		}else {
			code_count++;
		}
		
	}
}
