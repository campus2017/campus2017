package com.qunar.controller;

import com.qunar.model.Result;
import com.qunar.utils.Analyser;
import com.sun.org.apache.bcel.internal.generic.ANEWARRAY;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiazihao on 7/3/17.
 */
@Controller
public class UpLoadFileController {


    @RequestMapping(value = "/upLoadFile", method = RequestMethod.POST)
    public String upload(@RequestBody MultipartFile file, ModelMap model) throws IOException {
        MultipartFile myfile = file;
        if (myfile.isEmpty()) {
            return "start";
        }
        try {
            InputStream inputStream = myfile.getInputStream();
            byte[] buffer = myfile.getBytes();
            int byteSum = 0;
            int byteRead = 0;
            while ((byteRead = inputStream.read(buffer)) != -1) {
            }
            String text = new String(myfile.getBytes());
            List<Result> resultList = Analyser.analyse(text);
            model.addAttribute("result", resultList.get(0).getData());
            model.addAttribute("mostList", resultList.get(0).getMostList());
            return "start";

        } catch (Exception e) {
            e.printStackTrace();
        }
//        modelAndView.setViewName("start");
//        return modelAndView;
        return "start";
    }

}

