package com.charactercounter.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author wangyuquan
 *
 */

public class CharacterCounterService{
    
    /**
     * 
     * @param str 要统计的文字
     * @return 返回一个存有各种字符数的整型数组
     */
    public int[] count(String str) {
    	int enCharacter = 0;	//英文字母
        int numberCharacter = 0;	//數字
        int spaceCharacter = 0; //空格
        int chCharacter = 0;	//中文漢字
        int punctuationCharacter = 0;	//中英文標點符號
//        String num = "";
        int num[] = new int[4];
    	
        if (null == str || str.equals("")) {
            System.out.println("字符串為空");
            return num;
        }
        
        char tmp;
        
        for (int i = 0; i < str.length(); i++) {
            tmp = str.charAt(i);
            //
            if ((tmp >= 'A' && tmp <= 'Z') || (tmp >= 'a' && tmp <= 'z')) {
                enCharacter ++;
            } else if ((tmp >= '0') && (tmp <= '9')) {
                numberCharacter ++;
            } else if (tmp == ' '){
				spaceCharacter++;
			} else if (tmp >= '\u4e00' && tmp <= '\u9fa5') {
                chCharacter ++;
            } else {
            	punctuationCharacter++;
            }
            
        }
        num[0] = enCharacter;
        num[1] = numberCharacter;
        num[2] = chCharacter;
        num[3] = punctuationCharacter;
                        
        return num;
    }
    
    /**
     * 
     * @param str 要统计的文字
     * @return 存有每个字符出现次数的Map类型的容器
     */
    public Map<Character, Integer> characterCounter (String str) {
    	char tmp;
    	Map<Character, Integer> characterCounts = new HashMap<Character, Integer>();
    	
    	for (int i = 0; i < str.length(); i++) {
            tmp = str.charAt(i);       
            if (tmp != ' ') {
				if (characterCounts.containsKey(tmp)) {
					characterCounts.put(tmp, characterCounts.get(tmp) + 1);
				} else {
					characterCounts.put(tmp, 1);
				}
			}
        }
    	
    	return characterCounts;
    }
    
    /**
     * 
     * @param map 要统计的字符，这些字符存储在Map类型的容器里
     * @return 统计容器中出现次数最多的3个字符
     */
    public Map<Character, Integer> maxCharacterCounter (Map<Character, Integer> map) {
    	int max = 0, secondMax = 0, thirdMax = 0;
    	char maxChar = ' ', secondMaxChar = ' ', thirdMaxChar = ' ';
    	Map<Character, Integer> result = new HashMap<Character, Integer>();
    	
    	Set<Character> keys = map.keySet();
		Iterator<Character> iterator = keys.iterator();
		
		//遍历容器
		while (iterator.hasNext()) {
			char currChar = iterator.next();
			int currCount = map.get(currChar);
			if (currCount >= max) {
				thirdMax = secondMax;
				thirdMaxChar = secondMaxChar;
				secondMax = max;
				secondMaxChar = maxChar;
				max = currCount;
				maxChar = currChar;
			} else if (currCount < max && currCount >= secondMax) {
				thirdMax = secondMax;
				thirdMaxChar = secondMaxChar;
				secondMax = currCount;
				secondMaxChar = currChar;
			} else if (currCount < secondMax && currCount >= thirdMax) {
				if (currCount > thirdMax || currChar < thirdMaxChar) {
					thirdMax = currCount;
					thirdMaxChar = currChar;
				}
			}
		}
		
		result.put(maxChar, max);
		result.put(secondMaxChar, secondMax);
		result.put(thirdMaxChar, thirdMax);
		
		return result;
    }
}
