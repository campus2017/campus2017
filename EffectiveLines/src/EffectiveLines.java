package qunar.yaozhu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * 统计一个Java文件的有效行数。（作业命名：EffectiveLines）
 *1、有效不包括空行
 *2、不考虑代码见有多行注释的情况
 */
public class EffectiveLines {
	
	public static void main(String[] args) {
		int lineNum = 0;
		System.out.print("Pleast input the file path:");
		Scanner scanner = new Scanner(System.in);
		String filePath = scanner.nextLine();
		scanner.close();
		File file = new File(filePath);
		if(!file.exists())
			System.out.println("File is not exit!");
		else if (!file.getName().endsWith(".java"))
			System.out.println("The file is not java file!");
		else{
			lineNum = new EffectiveLines().linesOfFile(filePath);
			System.out.println("The effective lines of file" + file.getName() + "is:" + lineNum);
		}	
	}
	public int linesOfFile(String filePath){
		int lineNum = 0;
		String line = "";
		try {
			BufferedReader breader = new BufferedReader(new FileReader(filePath));
			while((line = breader.readLine()) != null){
				if (line.startsWith("/*")&&line.endsWith("*/"))
					continue;
				else if(line.trim().equals(""))
					continue;
				else if(line.startsWith("/*")&&!line.endsWith("*/")){
					while(!line.endsWith("*/")){
						line = breader.readLine();
					}
				} else{
					lineNum++;
				}		
			}	
			breader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return lineNum;
	}
}
