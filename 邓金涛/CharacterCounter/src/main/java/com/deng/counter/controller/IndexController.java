package com.deng.counter.controller;

import com.deng.counter.bean.JsonResult;
import com.deng.counter.service.CountService;
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

/**
 * Created by deng on 9/7/2017.
 */

@Controller
@RequestMapping(path = "/")
public class IndexController {

    @Autowired
    CountService service;

    @RequestMapping(path = "upload",
            method = RequestMethod.POST)
    @ResponseBody
    public JsonResult upload(@RequestParam(value = "file") MultipartFile file) {
        JsonResult count = null;

        try {
            StringBuilder builder = new StringBuilder();
            BufferedReader bufferedInputStream = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line;
            while ((line = bufferedInputStream.readLine()) != null) {
                builder.append(line);
            }
            count = service.count(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return count;
    }


    @RequestMapping(path = "text", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult text(@RequestParam(value = "text") String text) {
        return service.count(text);
    }

}
