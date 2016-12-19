package com.qunar.flight.zechuan_guo.charactercount.service;


import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by zechuan.guo on 2016/12/12.
 */
@Service
public class TestService implements ITestService {
    @Override
    public Map countLength(String text) {
        Map map = new HashMap<String,Integer>();
        text = " "+text+" ";
        System.out.println("开始");
        System.out.println(text);
        //计算字母数量
        String regex = "[a-zA-Z]";
        int count = text.split(regex).length-1;
        map.put("letter",count);
        //计算汉字数量
        regex = "[\u4e00-\u9fa5]";
        count = text.split(regex).length-1;
        map.put("Chinese_characters", count);
        //计算数字数量
        regex = "[\\d]";
        count = text.split(regex).length-1;
        map.put("number",count);
        //计算中英文标点符号
        regex = "[,<.>/?;:'\"`~，《。》【】？/、——；：’“·]";
        count = text.split(regex).length-1;
        map.put("symbol",count);

        System.out.println("Map如下");
        System.out.println(map);
        text = text.replace(" ","");
        Map map2 = new HashMap<Character,Integer>();
        for (int i = 0; i < text.length(); i++) {
            Character c = text.charAt(i);
            if(map2.get(c)==null){
                map2.put(c,1);
            }
            else
                map2.put(c,(Integer)map2.get(c)+1);
        }
        for (int i = 0; i < 3; i++) {
            int max = 0;
            Character c = null;
            for(Object o : map2.keySet()){
                Integer temp = (Integer)map2.get(o);
                if(temp>max){
                    max = temp;
                    c = (Character)o;
                }
                if(temp==max){
                    if((Character)o<c)
                        c = (Character)o;
                }
            }
            map.put("Seq"+i,c+"   数量："+max);
            map2.remove(c);
        }

        return map;
    }

    @Override
    public Map countLengthByFile(Scanner scanner) {
        StringBuffer buffer = new StringBuffer();
        while (scanner.hasNextLine()){
            buffer.append(scanner.nextLine());
        }
        Map map = countLength(buffer.toString().replace(" ",""));
        return map;
    }
}
