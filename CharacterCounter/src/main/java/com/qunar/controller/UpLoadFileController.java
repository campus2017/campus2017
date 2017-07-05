package com.qunar.controller;

import com.qunar.model.Result;
import com.qunar.utils.Analyser;
import com.sun.org.apache.bcel.internal.generic.ANEWARRAY;
import org.springframework.stereotype.Controller;
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
    @ResponseBody
    public List<Result> addUser(@RequestBody MultipartFile file) throws IOException {
        MultipartFile myfile = file;
        if (myfile.isEmpty()) {
            return new ArrayList<>();
        }
        try {
            ModelAndView modelAndView = new ModelAndView("start");
            InputStream inputStream = myfile.getInputStream();
            byte[] buffer = myfile.getBytes();
            int byteSum = 0;
            int byteRead = 0;
            while ((byteRead = inputStream.read(buffer)) != -1) {
            }
            String text = new String(myfile.getBytes());
//            while (myfile.) {
//                text += myfile.getBytes();
//            }
            return Analyser.analyse(text);
//            return modelAndView;

        } catch (Exception e) {
            e.printStackTrace();
        }
//        modelAndView.setViewName("start");
//        return modelAndView;
        return new ArrayList<>();
    }

}

