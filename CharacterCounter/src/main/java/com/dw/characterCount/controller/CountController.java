package com.dw.characterCount.controller;

import com.dw.characterCount.model.Result;
import com.dw.characterCount.service.CountService;
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
 * Created by DW on 2016/12/27.
 */
@Controller
@RequestMapping("/count")
public class CountController {
    @Autowired
    private CountService countService;

    //处理上传的文件
    @RequestMapping(value = "/upload.do",method = RequestMethod.POST)
    @ResponseBody
    public Result countUploadFile(@RequestParam(value="file")MultipartFile multifile){
        //存放读取到的文件内容
        StringBuilder content = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(multifile.getInputStream()));
            String s;
            while((s=br.readLine())!=null){
                content.append(s.trim());
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return countService.countText(content.toString());
    }
    //处理输入的文本
    @RequestMapping(value = "/input.do",method = RequestMethod.POST)
    @ResponseBody
    public Result countTextInput(@RequestParam(value="text")String text){

        return countService.countText(text);
    }
}
