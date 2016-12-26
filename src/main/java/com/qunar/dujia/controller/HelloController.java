package com.qunar.dujia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by tianyiren on 16-12-22.
 */
@Controller
public class HelloController {
    @RequestMapping(value="/hello")
    public String print() {
        System.out.println("controller");
        return "success";
    }
}
