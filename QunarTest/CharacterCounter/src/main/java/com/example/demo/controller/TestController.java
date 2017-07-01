package com.example.demo.controller;

import com.example.demo.service.CountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Wangweiyi on 2017/6/29 0029.
 */

@Controller
public class TestController {

    CountService countService;

    @RequestMapping("/index")
    public String index(Map<String, Object> map){
        map.put("name", "Wangweiyi");
        return "index2";
    }

//    @RequestMapping(value="/fileUpload",method = RequestMethod.POST)
//    @ResponseBody
//    public String fileUpload(Map<String, Object> map, @RequestParam("textFile") MultipartFile file){
//        String str = null;
//            try {
//                str = new String(file.getBytes());
//                System.out.print(str);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        Map<String, String> result = CountService.getResult(str);
//        map.put("EngChar", result.get("EngChar"));
//        map.put("DigitChar", result.get("DigitChar"));
//        map.put("ChiChar", result.get("ChiChar"));
//        map.put("PunChar", result.get("PunChar"));
//
//        map.put("Char1", result.get("Char1"));
//        map.put("Char2", result.get("Char2"));
//        map.put("Char3", result.get("Char3"));
//        map.put("Char1Num", result.get("Char1Num"));
//        map.put("Char2Num", result.get("Char2Num"));
//        map.put("Char3Num", result.get("Char3Num"));
//        //map.put("name", str);
//        //map.put("name", "Wangweiyi2");
//        return "index2";
//    }
    //文件上传，ModelAndView向前台传递View和参数
    @RequestMapping(value="/fileUpload",method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView fileUpload(@RequestParam("textFile") MultipartFile file){
        String str = null;
        try {
            str = new String(file.getBytes(),"gb2312");
            System.out.print(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ModelAndView mav = new ModelAndView("index2");

        Map<String, String> result = CountService.getResult(str);
        mav.addObject("EngChar", result.get("EngChar"));
        mav.addObject("DigitChar", result.get("DigitChar"));
        mav.addObject("ChiChar", result.get("ChiChar"));
        mav.addObject("PunChar", result.get("PunChar"));
        mav.addObject("Char1", result.get("Char1"));
        mav.addObject("Char2", result.get("Char2"));
        mav.addObject("Char3", result.get("Char3"));
        mav.addObject("Char1Num", result.get("Char1Num"));
        mav.addObject("Char2Num", result.get("Char2Num"));
        mav.addObject("Char3Num", result.get("Char3Num"));
        return mav;
    }

    //文本框内容上传
    @RequestMapping(value="/stringUpload",method = RequestMethod.POST)
    public String stringUpload(Map<String, Object> map, @RequestParam("textArea") String str){
        Map<String, String> result = CountService.getResult(str);
        map.put("EngChar", result.get("EngChar"));
        map.put("DigitChar", result.get("DigitChar"));
        map.put("ChiChar", result.get("ChiChar"));
        map.put("PunChar", result.get("PunChar"));

        map.put("Char1", result.get("Char1"));
        map.put("Char2", result.get("Char2"));
        map.put("Char3", result.get("Char3"));
        map.put("Char1Num", result.get("Char1Num"));
        map.put("Char2Num", result.get("Char2Num"));
        map.put("Char3Num", result.get("Char3Num"));
        //map.put("name", str);

        return "index2";
    }


}
