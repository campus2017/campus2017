package com.qunar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by fmz on 2017/7/6.
 */

@Controller
public class IndexController {

    @RequestMapping(path = {"/", "/index"}, method = {RequestMethod.GET, RequestMethod.POST})
    //@ResponseBody
    public String index(Model model) {
        model.addAttribute("v", "作业");
        return "index";
    }

    @RequestMapping(path = {"count"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String count(Model model, @RequestParam("content") String content) {
        model.addAttribute("v", "作业");
        model.addAttribute("content", content);
        return "index";
    }

}
