package com.qunar.character.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CharacterCounter {
    @RequestMapping("/home")
    public String home(Model model){
        model.addAttribute("text","spring mvc");
        return "home";
    }
}
