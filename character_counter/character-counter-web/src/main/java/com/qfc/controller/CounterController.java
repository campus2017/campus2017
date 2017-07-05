package com.qfc.controller;

import com.qfc.model.CharacterResult;
import com.qfc.service.CountText;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.RequestDispatcher;

/**
 * Created by honglin.li on 2017/7/3.
 */
@Controller
@RequestMapping("/qfc")
public class CounterController {

    @RequestMapping(value = "count.do",
                    method = {RequestMethod.POST}
    )
    public String count(@RequestParam String filetext, @RequestParam String upload,  ModelMap model) {

        System.out.println(upload);

        if ("file".equals(upload)) {

        }
        else {
            CharacterResult characterResult = CountText.countText(filetext);
            System.out.println(characterResult);
            model.addAttribute("cr", characterResult);
            model.addAttribute("maxSize", characterResult.getMaxRateCharater().size());
        }

        return "result";
    }

}















