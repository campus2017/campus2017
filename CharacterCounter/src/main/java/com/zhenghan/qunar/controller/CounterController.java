package com.zhenghan.qunar.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.zhenghan.qunar.Exception.notSupportTypeCountException;
import com.zhenghan.qunar.po.CharacterStream;
import com.zhenghan.qunar.po.CharacterStreams;
import com.zhenghan.qunar.po.StringCharacterStream;
import com.zhenghan.qunar.service.CountCharacter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: 郑含
 * Date: 2016/12/27
 * Time: 17:21
 */
@Controller
@RequestMapping("/")
public class CounterController {
    private Logger logger = LoggerFactory.getLogger(CounterController.class);
    @Autowired
    private CountCharacter countCharacter;
    @RequestMapping("/textcount")
    @ResponseBody
    public Map textCounter(@RequestBody Map map){
        Map result = Maps.newHashMap();
        CharacterStream stream = CharacterStreams.commons(map.get("stream").toString());
        List<Map.Entry<String,Integer>> sortedList =countCharacter.sort(stream,4);
        Map<String,Integer> classifyMap = countCharacter.classify(stream);
        result.put("classify",classifyMap);
        result.put("rateSort",sortedList);
        return result;
    }
    @RequestMapping("/filecount")
    @ResponseBody
    public Map fileCount(@RequestPart("up_file")  MultipartFile file){
        Map result = Maps.newHashMap();
        CharacterStream stream = null;
        try {
            String fileExtension = Files.getFileExtension(file.getOriginalFilename());
            stream = CharacterStreams.getByTypeName(file.getInputStream(),fileExtension);
            List<Map.Entry<String,Integer>> sortedList =countCharacter.sort(stream,4);
            Map<String,Integer> classifyMap = countCharacter.classify(stream);
            result.put("classify",classifyMap);
            result.put("rateSort",sortedList);
            return result;
        } catch (IOException e) {
            logger.error(e.getMessage());
        } catch (notSupportTypeCountException e) {
            result.put("error","上传失败:文件类型不能接受");
            logger.error(e.getMessage());
            return result;
        } finally {
            if(stream != null)
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        result.put("error","上传失败:错误原因未知");
        return  result;
    }
    public CountCharacter getCountCharacter() {
        return countCharacter;
    }

    public void setCountCharacter(CountCharacter countCharacter) {
        this.countCharacter = countCharacter;
    }

}
