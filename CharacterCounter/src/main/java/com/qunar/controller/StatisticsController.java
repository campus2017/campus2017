package com.qunar.controller;

import com.qunar.model.InputText;
import com.qunar.model.Data;
import com.qunar.model.Most;
import com.qunar.model.Result;
import com.qunar.utils.Analyser;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiazihao on 7/3/17.
 */
@Controller
public class StatisticsController {

    @RequestMapping(value = "/upLoad", method = RequestMethod.GET)
    @ResponseBody
    public List<Result> transRes() {
        List<Result> resultList = new ArrayList<>();
        ModelAndView modelAndView = new ModelAndView("start");
        return resultList;

    }


    @RequestMapping(value = "/statistics", method = RequestMethod.POST)
    @ResponseBody
    public List<Result> ajaxT(@RequestBody InputText angular) {
        return Analyser.analyse(angular.getText());

    }
}
