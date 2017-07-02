package com.renhao.controller;

import com.renhao.model.CountResult;
import com.renhao.service.CharacterCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


@Controller
public class CharacterCounterController {

    private CharacterCounterService characterCounterService;

    @Autowired
    public void setCharacterCounterService(CharacterCounterService characterCounterService) {
        this.characterCounterService = characterCounterService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/fileSend", method = RequestMethod.POST)
    public String processFile(@RequestParam(value="file") MultipartFile file, Model model) {
        System.out.println("success!");
        CountResult result = characterCounterService.getResultOfFile(file);
        model.addAttribute("result",result);
        return "index";
    }

    @RequestMapping(value = "/textSend")
    public String processText(HttpServletRequest request, Model model) {
        String text = request.getParameter("text");
        CountResult result = characterCounterService.getResultOfText(text);
        model.addAttribute("result",result);
        return "index";
    }
}
