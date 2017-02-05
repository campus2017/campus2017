package com.qunar.control;

import com.qunar.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 * Created by zhuyin on 17-1-19.
 */

@Controller
public class FileUploadController {

    private FileUploadService fileUploadService;

    @Autowired
    public FileUploadController(FileUploadService fileUploadService){
        this.fileUploadService = fileUploadService;
    }

    @RequestMapping(value = "fileUpload")   //路径不是"/fileUpload"
    @ResponseBody
    public void upload(@RequestParam(required = false)MultipartFile file,PrintWriter out) {
        String result = null;
        try {
            if(!file.isEmpty()){
                result = fileUploadService.parse(file.getInputStream());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.write(result);
    }

    @RequestMapping(value = "textUpload")   //路径不是"/fileUpload"
    @ResponseBody
    public void upload(@RequestParam(required = false)String text ,PrintWriter out) {
        String result = null;
//        System.out.println("text: "+text);
        InputStream is = new ByteArrayInputStream(text.getBytes());
        result = fileUploadService.parse(is);
        out.write(result);
    }
}
