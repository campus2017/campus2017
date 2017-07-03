package com.qunar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * Created by xiazihao on 7/3/17.
 */
@Controller
public class UpLoadFileController {

    //    @RequestMapping(value="/returnStart")
//    @ResponseBody
//    public ModelAndView returnStart(){
//        return new ModelAndView("start");
//    }
//
    @RequestMapping(value = "/uploadFile")
    public String startUpload() {
        return "UpLoad";
    }

    @RequestMapping(value = "/springUploadFile", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView addUser(@RequestBody MultipartFile file) throws IOException {
        ModelAndView mad = new ModelAndView();
        MultipartFile myfile = file;
        if (myfile.isEmpty()) {
            mad.setViewName("error_fileupload");
            String message = "上传信息为空！";
            mad.addObject("message", message);
            return mad;
        }
        try {

            mad = new ModelAndView("redirect:/TongJi");

            mad.addObject("name", myfile.getOriginalFilename());


            String nameText = myfile.getOriginalFilename();


            return mad;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mad;
    }

}

