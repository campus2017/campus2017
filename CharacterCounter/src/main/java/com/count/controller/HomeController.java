package com.count.controller;

import com.count.dao.Count;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenli on 2017/6/11.
 */
//注解标注此类为springmvc的controller，url映射为“/home”
@Controller
//@RequestMapping("/home")
public class HomeController {

    @RequestMapping("/receiveData")
    public String receiveData(String words, Model model){//filetypes为被选中radio的值

        Count count = new Count(words);
        HashMap<String, Integer> result = count.getCount();
        model.addAttribute("result", result);

        List<Map.Entry<Character, Integer>> maxThirdWords = count.getMaxThirdWords();
        model.addAttribute("maxThirdWords", maxThirdWords);
        //model.addAttribute("filetypes", filetypes);
        model.addAttribute("words", words);
        return "index";
    }
}
