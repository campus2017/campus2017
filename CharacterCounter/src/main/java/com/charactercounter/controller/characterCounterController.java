package com.charactercounter.controller;

import com.charactercounter.service.characterCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * Created by yangyening on 2017/2/19.
 */
@Controller
@RequestMapping("/count")
public class characterCounterController {
    @Autowired
    characterCounterService charcounter;

    /* 功能：统计文本输入的英文字母、数字、中文汉字、中英文标点符号的个数，以及文字中出现最高的三个字符
     * 作者：Yung
     * 时间：2017/2/19.
    * */
    @RequestMapping(value = "/charactercount")
    @ResponseBody
    public HashMap<String ,HashMap<String,Integer>> countCharacter( @RequestParam("character") String character){
        HashMap<String,HashMap<String,Integer>> hashMapHashMap=new HashMap<String, HashMap<String, Integer>>();
        HashMap<String,Integer> hashMap=charcounter.countCharacter(character);
        HashMap<String,Integer> countMostHashMap=charcounter.countMostCharacter(character);
        hashMapHashMap.put("countCharacter",hashMap);
        hashMapHashMap.put("countMostCharacter",countMostHashMap);
        return hashMapHashMap;

    }

    /* 功能：统计上传文件的英文字母、数字、中文汉字、中英文标点符号的个数，以及文字中出现最高的三个字符
     * 作者：Yung
     * 时间：2017/2/19.
    * */
    @RequestMapping(value = "/uploadcharactercount",method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String ,HashMap<String,Integer>> countfileUploadCharacter( @RequestParam("fileUpload") MultipartFile multipartFile){
        HashMap<String,HashMap<String,Integer>> hashMapHashMap=new HashMap<String, HashMap<String, Integer>>();
        String character="";
        BufferedReader reader=null;
        try {
            reader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream()));
             StringBuffer stringBuffer=new StringBuffer();
             String line;
             while ((line = reader.readLine()) != null)
                 stringBuffer.append(line);
             character = stringBuffer.toString();
             HashMap<String,Integer> hashMap=charcounter.countCharacter(character);
             HashMap<String,Integer> countMostHashMap=charcounter.countMostCharacter(character);
             hashMapHashMap.put("countCharacter",hashMap);
             hashMapHashMap.put("countMostCharacter",countMostHashMap);
        } catch (UnsupportedEncodingException e) {
             e.printStackTrace();
        } catch (IOException e) {
             e.printStackTrace();
        }
        return hashMapHashMap;
    }

}
