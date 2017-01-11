package com.ityrx.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class EffectiveLines {

	static int cntCode=0,cntNode=0,cntSpace=0;
	static boolean flagNode=false;
	
	public static void main(String[] args)
	{
		try {
			BufferedReader br = null;
			br = new BufferedReader(new FileReader("src/com/ityrx/Test/text.java"));
			String line=null;
			while((line = br.readLine())!=null)
			{
				pattern(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("代码行："+cntCode);

	}
	private static void pattern(String line){
		
		String regNodeBegin="\\s*/\\*.*";
		String regNodeEnd=".*\\*/\\s*";
		String regx="\\s*//.*";
		String regSpace="\\s*";
		//注释行
		if(line.matches(regNodeBegin) && line.matches(regNodeEnd)){
			++cntNode;
			return ;
		}
		if(line.matches(regNodeBegin)){//多行注释
			++cntNode;
			flagNode = true;
		}else if(line.matches(regNodeEnd)){
			++cntNode;
			flagNode = false;
		}else if(line.matches(regSpace)){//空行
			++cntSpace;
		}else if(line.matches(regx)){//单行注释
			++cntNode;
		}else if(flagNode){
			++cntNode;
		}else{
			++cntCode;
		}
	}
}
