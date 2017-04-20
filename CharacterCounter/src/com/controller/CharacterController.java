package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tang_yi on 2017/2/17.
 */
@Controller
public class CharacterController {

    @RequestMapping(value = "/charactercounter", method = RequestMethod.GET)
    public void characterGetHandler() {
        return;
    }

    @RequestMapping(value = "/charactercounter", method = RequestMethod.POST )
    public ModelMap characterPostHandler(@RequestParam("file") MultipartFile file, @RequestParam("text") String text, ModelMap model){
        int englishCount = 0;
        int digitCount = 0;
        int chineseCount = 0;
        int puncCount = 0;
        //获取要统计的内容
        StringBuffer content=new StringBuffer();
        BufferedReader br = null;
        if (!file.isEmpty()) {
            try {
                br=new BufferedReader(new InputStreamReader(file.getInputStream(), "GBK"));
                String line = "";
                while ((line=br.readLine())!=null){
                    content.append(line);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else if(text != null && text.length() > 0){
            content.append(text);
        }

        //开始统计
        TreeMap<Character, Integer> map = new TreeMap<Character, Integer>();
        for (int i = 0; i < content.length(); i++) {
            Character c = content.charAt(i);
            if (c >= 'A' && c <= 'z') {
                englishCount++;
            } else if (c >= '0' && c <= '9') {
                digitCount++;
            } else if ((c >= 0x4e00) && (c <= 0x9fbb)) {
                chineseCount++;
            }

            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                int value = map.get(c);
                map.put(c, value + 1);
            }
        }
        puncCount = countPunctuation(content.toString());
        List<Map.Entry<Character, Integer>> sortlist = sortTreeMap(map);

        //写入model
        model.addAttribute("englishCount", englishCount);
        model.addAttribute("digitCount", digitCount);
        model.addAttribute("chineseCount", chineseCount);
        model.addAttribute("puncCount", puncCount);
        model.addAttribute("ranking", sortlist);
        return model;

    }

    public List<Map.Entry<Character ,Integer>> sortTreeMap(TreeMap<Character, Integer> map){
        List<Map.Entry<Character, Integer>> list = new ArrayList<Map.Entry<Character, Integer>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        return list;
    }

    public static int countPunctuation(String str) {
        int count = 0;
        Pattern p = Pattern.compile("[\\pP‘’“”]");
        Matcher m = p.matcher(str);
        while(m.find()){
            count++;
        }
        return count;
    }

}
