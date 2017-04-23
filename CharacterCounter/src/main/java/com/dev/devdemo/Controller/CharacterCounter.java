package com.dev.devdemo.Controller;

import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

/**
 * Created by Administrator on 2017/4/19.
 */
@Controller
@RequestMapping(value = "/charactercounter", method = RequestMethod.POST)
public class CharacterCounter {
    @RequestMapping(value = "/count", method = RequestMethod.POST)
    @ResponseBody
    public Map ReturnCountResult(@RequestParam("fileUpload") String result, ModelMap model)
    {
        Map  v1 = new HashMap();
        List<Map.Entry<Character,Integer>> list = new LinkedList<Map.Entry<Character, Integer>>();
        list = countstring(result,v1);
        if(list.size()>0) {
            v1.put("top1_key", list.get(0).getKey());
            v1.put("top1_value", list.get(0).getValue());
        }
        if(list.size()>1) {
            v1.put("top2_key", list.get(1).getKey());
            v1.put("top2_value", list.get(1).getValue());
        }
        if(list.size()>2) {
            v1.put("top3_key", list.get(2).getKey());
            v1.put("top3_value", list.get(2).getValue());
        }

        return v1;
    }

    @RequestMapping(value = "/fileup", method = RequestMethod.POST)
    @ResponseBody
    public Map  FileUp(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, ModelMap model) {

        String fileName = file.getOriginalFilename();
        byte[] result = new byte[0];
        try {
            InputStream inputStream = file.getInputStream();
            result= new byte[inputStream.available()];
            inputStream.read(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map  v1 = new HashMap();
        List<Map.Entry<Character,Integer>> list = new LinkedList<Map.Entry<Character, Integer>>();
        list = countstring(new String(result).toString(),v1);
        v1.put("filename",fileName);

        if(list.size()>0) {
            v1.put("top1_key", list.get(0).getKey());
            v1.put("top1_value", list.get(0).getValue());
        }
        if(list.size()>1) {
            v1.put("top2_key", list.get(1).getKey());
            v1.put("top2_value", list.get(1).getValue());
        }
        if(list.size()>2) {
            v1.put("top3_key", list.get(2).getKey());
            v1.put("top3_value", list.get(2).getValue());
        }
        return v1;
    }

    public List<Map.Entry<Character,Integer>> countstring(String str, Map<String,Integer>  v1)
    {
        Map<Character, Integer> v2 = new HashMap<Character, Integer>() ;
        /**中文字符 */
         int chCharacter = 0;

        /**英文字符 */
         int enCharacter = 0;

        /**空格 */
         int spaceCharacter = 0;

        /**数字 */
         int numberCharacter = 0;

        /**其他字符 */
         int otherCharacter = 0;
        for (int i = 0; i < str.length(); i++) {
            char tmp = str.charAt(i);
            if ((tmp >= 'A' && tmp <= 'Z') || (tmp >= 'a' && tmp <= 'z')) {
                enCharacter ++;
                if(!v2.containsKey(tmp))
                {
                    v2.put(tmp,1);
                }else {
                    v2.put(tmp,v2.get(tmp)+1);
                }
            } else if ((tmp >= '0') && (tmp <= '9')) {
                numberCharacter ++;
                if(!v2.containsKey(tmp))
                {
                    v2.put(tmp,1);
                }else {
                    v2.put(tmp,v2.get(tmp)+1);
                }
            } else if (tmp ==' ') {
                spaceCharacter ++;
            } else if (isChinese(tmp)) {
                chCharacter ++;
                if(!v2.containsKey(tmp))
                {
                    v2.put(tmp,1);
                }else {
                    v2.put(tmp,v2.get(tmp)+1);
                }
            } else {
                otherCharacter ++;
            }
        }
        v1.put("chCharacter",chCharacter);
        v1.put("enCharacter",enCharacter);
        v1.put("numberCharacter",numberCharacter);
        v1.put("otherCharacter",otherCharacter);
        List<Map.Entry<Character,Integer>> list = new ArrayList<Map.Entry<Character,Integer>>(v2.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
            public int compare(Map.Entry<Character, Integer> o1,
                               Map.Entry<Character, Integer> o2) {
                return (o2.getValue() - o1.getValue());
            }
        });
        return list;
    }

    private boolean isChinese(char ch) {
        //获取此字符的UniCodeBlock
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(ch);
        //  GENERAL_PUNCTUATION 判断中文的“号
        //  CJK_SYMBOLS_AND_PUNCTUATION 判断中文的。号
        //  HALFWIDTH_AND_FULLWIDTH_FORMS 判断中文的，号
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }
}
