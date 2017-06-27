package com.characterCounter.controller;


import com.characterCounter.service.CounterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Administrator on 2017/6/24.
 */
@Controller
@RequestMapping(value = "/home")
public class HomeController {

    //日志
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private CounterService counterService;

    @RequestMapping(value = "/index", method = {RequestMethod.GET})
    public String index() {
        return "home/index";
    }

    /**
     * 通过文件上传方式统计
     * @param file
     * @return
     */
    @RequestMapping(value = "/file_upload", method = {RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> uploadFile(@RequestParam(value = "file") MultipartFile file) {

        Map<String,Object> map = new HashMap<String,Object>();

        if(file.isEmpty()) {
            map.put("msg","请上传文件");
            map.put("status","failed");
        } else {
            String contentType = file.getContentType();
            if(!contentType.contains("text")) {
                map.put("msg","请上传文本文件");
                map.put("status","failed");
            } else {
                try {
                    Scanner scanner = new Scanner(file.getInputStream());
                    //根据文件内容计算
                    map.put("msg",counterService.getCountResult(scanner));
                    map.put("status","success");
                } catch (IOException e) {
                    logger.warn("failed get inputStream", e);
                    map.put("msg","获取文件输入流失败");
                    map.put("status","failed");
                }
            }
        }
        return map;
    }

    /**
     * 通过文本输入方式统计
     * @param text
     * @return
     */
    @RequestMapping(value = "/text_input", method = {RequestMethod.POST})
    @ResponseBody
    public Map<String,Object> inputText(@RequestParam(value = "text") String text) {

        Map<String,Object> map = new HashMap<String,Object>();

        map.put("msg",counterService.getCountResult(new Scanner(text)));
        map.put("status","success");
        return map;
    }
}
