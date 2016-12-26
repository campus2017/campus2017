package com.qunar.dujia.controller;

/**
 * Created by tianyiren on 16-12-22.
 */
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StartController {
    @RequestMapping(value="/start")
    public String printStart() {
        return "start";
    }
}
