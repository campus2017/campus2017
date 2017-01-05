package com.liu.ExchangeRate;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/*
 *@author:liudan
 *@description: 工具类，包含正则表达式、文件读取
 */
public class fuction_unit {

	public static String regexFind(String pattern, String text){
		Matcher matcher = Pattern.compile(pattern).matcher(text);
		if(matcher.find()&&matcher.groupCount()>0){
			return matcher.group(1);
		}else{
			return text ;
		}
	}

	public BufferedReader getResource(String url){
		URL curl = null ;
		BufferedReader br = null ;
		try {
			curl = new URL(url);
			br = new BufferedReader(new InputStreamReader(curl.openStream(),"utf-8"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return br ;
	}

	public static void Write(File file, String str, boolean append){
		BufferedWriter writer = null ;
        try {
			writer = new BufferedWriter(new FileWriter(file, append));
			writer.write(str);
			writer.newLine();
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void delete(File file) throws IOException{
		FileWriter fw =  new FileWriter(file);
		fw.write("");
		fw.close();
	}
	
	public static String ReadLocation(File file){
		BufferedReader br = null ;
        try {
			br = new BufferedReader(new FileReader(file));
			return br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	public static List<String> Read(File file,int location){
		BufferedReader br = null ;
		List<String> list = new ArrayList<String>();
		String line = null;
		int num = 0 ;
        try {
			br = new BufferedReader(new FileReader(file));
			while((line = br.readLine()) != null){
				num ++ ;
				if(num > location){
					list.add(line);
				}
			}
			return list ;
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
