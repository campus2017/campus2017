package com.test.dev.controller;



import com.test.dev.service.CharacterCounterService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;

/**
 * Created by Vsur-Pc on 2017/7/4.
 */
@Controller
public class CharacterCounterController {
    @Autowired
    protected CharacterCounterService characterCounterService;
    @RequestMapping(value = "/characterCounter")
    public @ResponseBody
    JSONObject characterCounterOfFile(HttpServletRequest request, @RequestParam("file") MultipartFile file, ModelMap model){
        JSONObject json = new JSONObject();
        if(file == null){
           json.put("error","Error");
        }
        try {
            json = characterCounterService.characterCounterOfInputStream(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
    @RequestMapping(value = "/characterCounterOfString")
    public @ResponseBody JSONObject characterCounterOfString(String str){
        JSONObject json = new JSONObject();
      //  System.out.println(str);
        if(str == null){
            json.put("error","Error");
            return json;
        }
        try {
            json = characterCounterService.characterCounterOfInputStream(new ByteArrayInputStream(str.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
}
