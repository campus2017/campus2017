package com.springapp.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.io.*;
import java.util.List;

@Controller
@RequestMapping("/")
public class CharCounterController {
    @Autowired
    private CharCounterService service;
    @RequestMapping("charCounter.html")
    public String page() {
        return "CharCounter";
    }

	@RequestMapping("textCounter.qunar")
	public ModelAndView textCounter(String text) {
        ModelAndView mv = new ModelAndView("CharCounter");
        Word word = service.Count(text);
        List<Word> list = service.getTop3(text);
        mv.addObject("word",word);
        mv.addObject("top",list);
        return mv;
	}


	@RequestMapping("fileCounter.qunar")
	public ModelAndView fileCounter(@RequestParam("file")MultipartFile file) {
        ModelAndView mv = new ModelAndView("CharCounter");
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
        Word word = service.Count(text);
        List<Word> list = service.getTop3(text);
        mv.addObject("word",word);
        mv.addObject("top",list);

        return mv;
	}
}