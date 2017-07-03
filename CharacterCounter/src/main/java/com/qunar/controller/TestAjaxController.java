package com.qunar.controller;

import com.qunar.model.InputText;
import com.qunar.model.Data;
import com.qunar.model.Most;
import com.qunar.model.Result;
import com.qunar.utils.Analyser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiazihao on 7/3/17.
 */
@Controller
public class TestAjaxController {

    @RequestMapping(value = "/TongJi", method = RequestMethod.GET)
    @ResponseBody
    public List<Result> transRes() {
        return null;

    }


    @RequestMapping(value = "/TestAjax", method = RequestMethod.POST)
    @ResponseBody
    public List<Result> ajaxT(@RequestBody InputText angular) {
        System.out.print(angular.getText());
        String text = angular.getText();
        text = text.replaceAll(" ", "");
        text = text.replaceAll("\n", "");
        Data data = Analyser.tongji(text);
        List<Most> mostList = Analyser.getMost(text);
        Result result = new Result(data, mostList);
        List<Result> resultList = new ArrayList<Result>();
        resultList.add(result);
        return resultList;
    }
}
