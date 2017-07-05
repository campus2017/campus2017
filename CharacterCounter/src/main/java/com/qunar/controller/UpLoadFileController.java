package com.qunar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by xiazihao on 7/3/17.
 */
@Controller
public class UpLoadFileController {


    @RequestMapping(value = "/upLoadFile", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView addUser(@RequestBody MultipartFile file) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        MultipartFile myfile = file;
        if (myfile.isEmpty()) {
            modelAndView.setViewName("start");
            return modelAndView;
        }
        try {

            modelAndView = new ModelAndView("redirect:/upLoad");
            modelAndView.addObject("name", myfile.getOriginalFilename());

            return modelAndView;

        } catch (Exception e) {
            e.printStackTrace();
        }
        modelAndView.setViewName("start");
        return modelAndView;
    }

}

