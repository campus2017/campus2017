package com.qunar.fresh2017.controller;

import com.qunar.fresh2017.utils.ListFile;
import com.qunar.fresh2017.utils.modelUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * Created by 曹 on 2017.5.17.
 */
@Controller
public class UpLoadFileController {

    @RequestMapping(value="/returnStart")
    @ResponseBody
    public ModelAndView returnStart(){
        return new ModelAndView("start");
    }

    @RequestMapping(value="/uploadFile")
    public String startUpload() {
        return "UpLoad";
    }

    @RequestMapping(value="/springUploadFile", method= RequestMethod.POST)
    @ResponseBody
    public ModelAndView addUser(@RequestBody MultipartFile file) throws IOException {
        System.out.println(file);
        System.out.println("controller is in");
        ModelAndView mad = new ModelAndView();
        MultipartFile myfile = file;
        if(myfile.isEmpty()){
            mad.setViewName("error_fileupload");
            String message = "上传信息为空！";
            mad.addObject("message",message);
            return mad;
        }
        try {
            System.out.println(myfile.isEmpty());
            System.out.println("文件长度: " + myfile.getSize());
            System.out.println("文件类型: " + myfile.getContentType());
            System.out.println("文件名称: " + myfile.getName());
            System.out.println("文件原名: " + myfile.getOriginalFilename());

            String paths = "/home/cao/TestReceive/";

            String myDirect = modelUtils.saveFileToServer(myfile, "F://test");

            mad=new ModelAndView("redirect:/TongJi");

            mad.addObject("name",myfile.getOriginalFilename());


            System.out.println("controller is in");

//             ResModel resModel = new ResModel(res, lis);

            String nameText = myfile.getOriginalFilename();

            System.out.println(nameText);

            ListFile.AddFileName(nameText);

            return mad;

        }catch(Exception e){
            e.printStackTrace();
        }

        return mad;
    }

}

