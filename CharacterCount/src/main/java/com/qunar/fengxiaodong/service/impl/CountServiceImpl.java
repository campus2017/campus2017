package com.qunar.fengxiaodong.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.qunar.fengxiaodong.model.MostNum;
import com.qunar.fengxiaodong.model.Result;
import com.qunar.fengxiaodong.model.Statistics;
import com.qunar.fengxiaodong.service.CountService;


@Service("countService")
public class CountServiceImpl implements CountService {

	public Result statisticCharacterCountByString(String text) {
    	Result res = new Result();
    	HashMap<String, Integer> countMap = new HashMap<String, Integer>();
		Statistics st = StatisticsCountAndPutMap(text, countMap);
		ArrayList<MostNum> mostNumList = getMostNUmList(countMap);
		res.setLisMostNum(mostNumList);
		res.setSt(st);
		
        return res;
	
	}

	public Result statisticCharacterCountByFile(MultipartFile file) {
    	StringBuilder sb = new StringBuilder(); 
    	Result res = new Result();
		try
		{
			InputStream in = file.getInputStream();
	    	InputStreamReader isr= new InputStreamReader(in);
        	BufferedReader br= new BufferedReader(isr);
        String line = null;
        while((line = br.readLine())!=null )
        {
        	sb.append(line);
        }
       
        }
        catch(IOException e)
        {
        	e.printStackTrace();
        }
		
		String text = sb.toString();
    	HashMap<String, Integer> countMap = new HashMap<String, Integer>();
		Statistics st = StatisticsCountAndPutMap(text, countMap);
		ArrayList<MostNum> mostNumList = getMostNUmList(countMap);
		res.setLisMostNum(mostNumList);
		res.setSt(st);
		
        return res;
        
    }

	 //判断数字
    public  boolean isNumeric(String str) {
        String regEx = "[0-9]";
        return isRegEx(str, regEx);
    }
    //判断是否为汉字
    public  boolean isChinaChar(String str) {
        String regEx = "[\\u4e00-\\u9fa5]";
        return isRegEx(str,regEx);
    }
    //判断是否是英文
    public  boolean isEnglishChar(String str) {
        String regEx = "[a-zA-Z]";
        return isRegEx(str,regEx);
    }
	
    public  boolean isRegEx(String str,String regEx) {
        Pattern pattern = Pattern.compile(regEx);
        Matcher isNum = pattern.matcher(str);
        return isNum.matches() ? true : false;
    }
    
    public  Statistics StatisticsCountAndPutMap(String text, HashMap<String, Integer> map)
    {
    	 int english = 0;
         int chinese = 0;
         int symbol = 0;
         int number = 0;
         String line=null;
         char chars[] = text.toCharArray();
         
       	for (char ch : chars) {
          String s = String.valueOf(ch);
          if (isNumeric(s)) {
             number++;
             putmap(map, s);
             }
          if (isChinaChar(s)) {
             chinese++;
             putmap(map, s);
             }
          if (isEnglishChar(s)) {
             english++;
             putmap(map, s);
             }
           
         }
         symbol = chars.length - number - english - chinese;
    
         Statistics st = new Statistics();
         st.setChinese(chinese);
         st.setEnglish(english);
         st.setNumber(number);
         st.setSymbol(symbol);
    	
    	return st;
    }
    
    private  void putmap(HashMap<String, Integer> map, String key) {
		
    	if(map.containsKey(key))
    	{
    		map.put(key, map.get(key)+1);
    	}
    	else
    	{
    		map.put(key, 1);
    	}
	}
    public  ArrayList<MostNum> getMostNUmList(HashMap<String, Integer> CountMap)
    {
    ArrayList<MostNum> result = new ArrayList<MostNum>();
    List<Map.Entry<String, Integer>> list=new ArrayList<Map.Entry<String, Integer>>();
    list.addAll(CountMap.entrySet());
    Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
            return o2.getValue() - o1.getValue();
        }
    });
    for (int i=0; i<3; i++)
    {
        Map.Entry entry = list.get(i);
        System.out.println(entry.getKey() + "   :    " + entry.getValue()+"次");
        MostNum mn = new MostNum();
        mn.setName((String) entry.getKey());
        mn.setNum((Integer) entry.getValue());
        result.add(mn);
    }
    
	return result;
    }
}
