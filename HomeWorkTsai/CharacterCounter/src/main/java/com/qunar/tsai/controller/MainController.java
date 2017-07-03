package com.qunar.tsai.controller;

import com.qunar.tsai.model.CountBean;
import com.qunar.tsai.utils.CounterHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by joeTsai on 2017/6/25.
 */

@Controller
@RequestMapping("/count")
public class MainController {

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public String helloWorld() {
        return "index";
    }


    @RequestMapping("/count.do")
    public String countCharacter(String str, Model model) {
        if (str == null || str.length() == 0) { //防空保护
            return "index";
        }
        CountBean bean = new CountBean();
        CounterHelper.countCharacter(str, bean);
        setModelAttr(model, bean);
        return "index";
    }


    private void setModelAttr(Model model, CountBean bean) {
        model.addAttribute("enChar", bean.getEnChar());
        model.addAttribute("num", bean.getNum());
        model.addAttribute("chChar", bean.getChChar());
        model.addAttribute("punctuation", bean.getPunctuation());
        model.addAttribute("topChar1", bean.getTopChar1());
        model.addAttribute("topCount1", bean.getTopCount1());
        model.addAttribute("topChar2", bean.getTopChar2());
        model.addAttribute("topCount2", bean.getTopCount2());
        model.addAttribute("topChar3", bean.getTopChar3());
        model.addAttribute("topCount3", bean.getTopCount3());
    }

}

