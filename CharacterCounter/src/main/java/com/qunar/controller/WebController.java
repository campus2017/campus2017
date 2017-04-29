package com.qunar.controller;

import com.qunar.pojo.SpringContextUtil;
import com.qunar.pojo.Words;
import com.qunar.service.Counter;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Controller
public class WebController {


    @RequestMapping("/")
    public ModelAndView Index() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/upload")
    @ResponseBody
    public Map<String,String> Upload( @RequestParam MultipartFile upfile, HttpServletRequest request) throws IOException {
        Map<String,String> mss = new HashMap<>();
        if(upfile.isEmpty()){
            mss.put("status","failed");
            mss.put("res","no file upload");
        }else{
            String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
            FileUtils.copyInputStreamToFile(upfile.getInputStream(), new File(realPath, upfile.getOriginalFilename()));
            mss.put("status","success");
        }
        return mss;
    }

    @RequestMapping(value = "/counter")//,method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> CountWord(@RequestParam String type,@RequestParam String text,@RequestParam String filename, HttpServletRequest request) {
        Map mss = new HashMap<>();
        Counter counter = (Counter)SpringContextUtil.getBean("counter");
        if (type.equals("0")) {
            counter.clearRes();
            counter.ReadFile(filename, request);
        }else {
            counter.clearRes();
            counter.CounterWord(text);
        }
        final Map<Integer,Words> miw = counter.getTop3();
        Map<String,Words> msw = new HashMap<String,Words>(){
            {
                put("first",miw.get(1));
                put("second",miw.get(2));
                put("third",miw.get(3));
            }
        };
        mss.put("top",msw);
        mss.put("type",counter.getType());

        mss.put("status","success");
        return mss;
    }

}
