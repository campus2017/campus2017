package com.qunar.homework.controller;

import com.google.common.collect.Maps;
import com.qunar.homework.service.HomeService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Date;
import java.util.Map;

/**
 * Created by deep on 2017/6/15.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    private static final String uploadDir = "/Users/deep/IdeaProjects/homework/src/main/webapp/WEB-INF/upload/";
    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    public HomeService homeService;

    @ResponseBody
    @RequestMapping("/uploadFile")
    public Map<String, String> uploadFile(@RequestParam("file") CommonsMultipartFile file) {
        File newFile = new File(uploadDir + DateFormatUtils.format(new Date(), "yyyyMMddHHmmss") + file.getOriginalFilename());
        try {
            file.transferTo(newFile);
            log.info(" 成功上传文件：{}",newFile.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, String> result = Maps.newHashMap();
        result.put("name", "lvshichang");
        result.put("age", "23");

        return result;
    }

    @ResponseBody
    @RequestMapping("/uploadWords")
    public Map<String, Integer> uploadWords(@RequestParam("words")String words) {

        Reader reader = new StringReader(words);

        Map<String, Integer> result = null;
        try {
            result = homeService.countWords(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
