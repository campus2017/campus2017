package com.qunar.controller;

import com.qunar.model.InputText;
import com.qunar.model.Data;
import com.qunar.model.Most;
import com.qunar.model.Result;
import com.qunar.utils.Analyser;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.portlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiazihao on 7/3/17.
 */
@Controller
public class StatisticsController {



    @RequestMapping(value = "/statistics", method = RequestMethod.POST)
    public String ajaxT(@RequestParam String text, Model model) {
         model.addAttribute("result", Analyser.analyse(text));
        return "start";
    }
}
