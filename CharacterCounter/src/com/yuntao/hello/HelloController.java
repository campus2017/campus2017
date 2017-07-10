package com.yuntao.hello;

import character.count.CountContent;
import character.count.ResultOfCount;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HelloController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String printHello(ModelMap model) {
        return "hello";
    }

    @RequestMapping(value = "/countForText", method = RequestMethod.POST)
    public String countForText(@RequestParam(value="text",required=false) String text, Model model)

    {
        //String text = request.getParameter("text");
       CountContent counter;
        counter = new CountContent();
        ResultOfCount result = counter.getResultOfText(text);
        model.addAttribute("result",result);
        return "hello";
    }



}
