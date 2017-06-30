package com.largework.controller;

/**
 * Created by liudan on 2017/6/13.
 */

import com.largework.model.Count;
import com.largework.model.UploadFile;
import com.largework.service.ICharacterCountService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;

// 注解标注此类为springmvc的controller，url映射为"/home"
@Controller


public class HomeController {

    @Autowired
    ICharacterCountService chaCountService;

    //添加一个日志器
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    //映射一个action
    @RequestMapping(value="/calculator")
    public  String index(HttpServletRequest request, HttpSession session, Model model) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        String text=request.getParameter("textcal");
        System.out.println(text);
        Count count=chaCountService.characterCount(text);
        model.addAttribute("count",count);
        model.addAttribute("text",text);
        return "work";
    }
    @RequestMapping(value="/uploadCalculator")
    public  void uploadCalculator(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) throws IOException {
        request.setCharacterEncoding("utf-8");
        UploadFile file=chaCountService.uploadFile(request);
        String text=file.getText();
        System.out.println("text： "+text);
        Count count=chaCountService.characterCount(text);
        String returnText=chaCountService.jsonReturn(count);
        System.out.println(returnText);
        model.addAttribute("count",count);
        response.getWriter().write(returnText);
    }
    @RequestMapping(value="/home")
    public  String work(){
        //输出日志文件
        //logger.info("the first jsp pages");
        //返回一个index.jsp这个视图
        return "work";
    }
}