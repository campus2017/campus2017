package com.hepeng.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;
@Controller
public class CharacterController {
    @RequestMapping(value = "/input", method = RequestMethod.GET)
    public ModelAndView inputString() {
        return new ModelAndView("input", "command", new InputString());
    }
    @RequestMapping(value = "/counter", method = RequestMethod.POST)
    public String addInputString(@ModelAttribute("SpringWeb")InputString inputString,
                             ModelMap model) {

          Counter counterObject  = new Counter(inputString.getInputString());
        counterObject.numberProcess();

      //  model.addAttribute("inputString", inputString.getInputString());
        model.addAttribute("alphbetCnt", counterObject.getAlphbetCnt());
        model.addAttribute("numCnt", counterObject.getNumCnt());
        model.addAttribute("chineseCnt", counterObject.getChineseCnt());
        model.addAttribute("splitCnt", counterObject.getSplitCnt());

        String result = counterObject.countmostProcess();

        String resultSplit[] = result.split(";");

        model.addAttribute("mostOneName", resultSplit[1].split("=")[0]);
        model.addAttribute("mostOneValue", resultSplit[1].split("=")[1]);
        model.addAttribute("mostTwoName", resultSplit[2].split("=")[0]);
        model.addAttribute("mostTwoValue", resultSplit[2].split("=")[1]);
        model.addAttribute("mostThreeName", resultSplit[3].split("=")[0]);
        model.addAttribute("mostThreeValue", resultSplit[3].split("=")[1]);


        model.addAttribute("inputString", inputString.getInputString());

        return "result";
    }
}

