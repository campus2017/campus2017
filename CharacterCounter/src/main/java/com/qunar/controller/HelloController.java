package com.qunar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xiazihao on 7/2/17.
 */
@Controller
public class HelloController {
    @RequestMapping(value = "/start")
    public String start() {
        return "start";
    }


}
