package com.qunar.campus2017.characterCounter.controller;

import com.qunar.campus2017.characterCounter.service.CountChar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * Created by chunming.xcm on 2017/2/24.
 */
@Controller
@RequestMapping("/count")
public class Count {
    @Autowired
    private CountChar countChar;

    @RequestMapping(value = "/fileCount", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Integer> textCount(
            @RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String str = null;
        try {
            str = new String(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return countChar.count(str);
    }

    @RequestMapping(value = "/stringCount", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Integer> testCount(
            @RequestParam("string") String str, HttpServletRequest request) {
        return countChar.count(str);
    }
}
