package com.qunar.fresh2017.controller;

/**
 * Created by 曹 on 2017.5.17.
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StartController {
    @RequestMapping(value="/start")
    public String printStart() {
        return "start";
    }
}
