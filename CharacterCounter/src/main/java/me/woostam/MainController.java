package me.woostam;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by woo on 6/27.
 */
@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private CharacterCounterService service;
    @RequestMapping("charactercounter1")
    public String page() {
        return "charactercounter";
    }

    @RequestMapping(value = "textcounter",method = RequestMethod.POST)
    public ModelAndView textcounter(String text){
        ModelAndView mv = new ModelAndView("charactercounter");
        TextInfo textInfo = service.Count(text);
        List<Word> list = service.getTopN(text,3);
        mv.addObject("top",list);
        mv.addObject("textInfo",textInfo);
        return mv;
    }


    @RequestMapping(value = "filecounter",method = RequestMethod.POST)
    public ModelAndView filecounter(@RequestParam("file")MultipartFile file){
        ModelAndView mv = new ModelAndView("charactercounter");
        StringBuilder sb = new StringBuilder();
        try {
            InputStream inputStream = file.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ( (line = reader.readLine())!=null){
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String text = sb.toString();
        TextInfo textInfo = service.Count(text);
        List<Word> list = service.getTopN(text,3);
        mv.addObject("textInfo",textInfo);
        mv.addObject("top",list);
        return mv;
    }
}