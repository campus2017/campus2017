package com.qunar.fresh2017.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 曹 on 2017.5.17.
 */
@Controller
public class HelloController {
    @RequestMapping(value="/hello")
    public String print() {
        System.out.println("controller");
        return "success";
    }
}
