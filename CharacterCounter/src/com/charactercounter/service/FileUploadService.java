package com.charactercounter.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author wangyuquan
 *
 */

public class FileUploadService {
	/**
	 * 
	 * @param fileName 要统计的文件
	 * @return 统计内容中各个字符的个数
	 */
	public static int[] readAndCount(String fileName) {
        BufferedReader reader = null;
        String str = "";
        
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "GBK"));           
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
            	str += tempString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        
        CharacterCounterService ccs = new CharacterCounterService();
        return ccs.count(str);
	}
	
	/**
	 * 
	 * @param fileName 要统计的文件
	 * @return 统计出现次数最多的3个字符及出现次数
	 */
	public static Map<Character, Integer> maxCharacterCounterByFile (String fileName) {
    	char tmp;
    	Map<Character, Integer> characterCounts = new HashMap<Character, Integer>();
        BufferedReader reader = null;
        
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "GBK"));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 统计每行中字符出现的次数
            	for (int i = 0; i < tempString.length(); i++) {
                    tmp = tempString.charAt(i);       
                    if (tmp != ' ') {
        				if (characterCounts.containsKey(tmp)) {
        					characterCounts.put(tmp, characterCounts.get(tmp) + 1);
        				} else {
        					characterCounts.put(tmp, 1);
        				}
        			}
                }          
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }   	
    	
        CharacterCounterService ccs = new CharacterCounterService();
        return ccs.maxCharacterCounter(characterCounts);
    }
	
}
